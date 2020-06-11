package tech.chengw.www.service;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import tech.chengw.www.entity.DataChangeRecord;
import tech.chengw.www.entity.po.Task;
import tech.chengw.www.dao.TaskMapper;
import tech.chengw.www.entity.vo.InsertResultVO;
import tech.chengw.www.entity.vo.SyncTaskVO;
import tech.chengw.www.mapstruct.TaskMapStructMapper;
import tech.chengw.www.service.iService.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

import static tech.chengw.www.util.TokenUtil.getUserId;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2020-05-11
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private RecordService recordService;

    /**
     * 同步所有任务
     * @param syncBodys
     */
    public List<InsertResultVO> syncTasks(List<SyncTaskVO> syncBodys) {
        ArrayList<InsertResultVO> result = new ArrayList<>();
        syncBodys.stream()
                .forEach(syncTaskVO -> {
                    //同步
                    InsertResultVO insertResultVO = syncTask(syncTaskVO);
                    if(insertResultVO != null) {
                        result.add(insertResultVO);
                    }
                });
        return result;
    }

    /**
     * 同步单个任务
     * @param syncTaskVO
     */
    private InsertResultVO syncTask(SyncTaskVO syncTaskVO) {
        //增同步
        if(syncTaskVO.isInsertSync()){
            return insertSync(syncTaskVO);
        }
        //删同步
        if(syncTaskVO.isDeleteSync()){
            deleteSync(syncTaskVO);
        }
        //改同步
        if(syncTaskVO.isUpdateSync()){
            updateSync(syncTaskVO);
        }
        return null;
    }

    /**
     * 同步修改数据
     * @param syncTaskVO
     */
    private void updateSync(SyncTaskVO syncTaskVO) {
        taskMapper.updateById(TaskMapStructMapper.INSTANCE.taskVO2PO(syncTaskVO));
    }

    /**
     * 同步删除数据
     * @param syncTaskVO
     */
    private void deleteSync(SyncTaskVO syncTaskVO) {
        taskMapper.deleteById(syncTaskVO.getId());
    }

    /**
     * 同步新增数据
     * @param syncTaskVO
     * @return
     */
    private InsertResultVO insertSync(SyncTaskVO syncTaskVO) {
        Task task = TaskMapStructMapper.INSTANCE.taskVO2PO(syncTaskVO)
                .setUserId(getUserId())
                .setGmtCreateTime(new Date());
        taskMapper.insert(task);
        return new InsertResultVO()
                .setSeverId(task.getId())
                .setClientId(syncTaskVO.getClientId());
    }


    public List<SyncTaskVO> getSync(Date lastSyncDate) {
        return getSync(lastSyncDate,getUserId());
    }

    public List<SyncTaskVO> getSync(Date lastSyncDate,Integer userId) {

        List<SyncTaskVO> result = new ArrayList<>();

        //变动记录
        List<DataChangeRecord> records = recordService.getRecord(userId, lastSyncDate);

        //去重，对于同一表同一行的变动进行去重
        Map<Integer, DataChangeRecord> map = new HashMap(records.size());
        records.stream()
                .forEach(dataChangeRecord -> {
                    map.put(dataChangeRecord.getId(),dataChangeRecord);
                });
        map.forEach((k,dataChangeRecord)->{
            //删——只取服务端id即可
            if(Objects.equals(dataChangeRecord.getType(), CanalEntry.EventType.DELETE.getNumber())) {
                result.add(new SyncTaskVO()
                        .setId(dataChangeRecord.getId())
                        .setSyncType(dataChangeRecord.getType()));
                return;
            }
            //增/改——填充数据
            if(Objects.equals(dataChangeRecord.getType(),CanalEntry.EventType.INSERT.getNumber())
            || Objects.equals(dataChangeRecord.getType(),CanalEntry.EventType.UPDATE.getNumber())) {
                Optional.ofNullable(taskMapper.selectById(dataChangeRecord.getId()))
                        .map(task -> TaskMapStructMapper.INSTANCE.taskPO2VO(task)
                                .setId(dataChangeRecord.getId())
                                .setSyncType(dataChangeRecord.getType()))
                        .ifPresent(taskVO -> result.add(taskVO));
                return;
            }
            //其他不管
        });
        return result;
    }
}

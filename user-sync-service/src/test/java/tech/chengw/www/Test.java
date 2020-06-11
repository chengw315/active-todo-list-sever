package tech.chengw.www;

import com.alibaba.otter.canal.protocol.CanalEntry;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.entity.po.Task;
import tech.chengw.www.entity.vo.SyncTaskVO;
import tech.chengw.www.mapstruct.TaskMapStructMapper;
import tech.chengw.www.service.TaskServiceImpl;
import tech.chengw.www.service.iService.TaskService;

import java.util.ArrayList;
import java.util.Date;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Slf4j
public class Test {

    @Autowired
    private TaskServiceImpl taskService;

    /**
     * 测试同步
     */
    @org.junit.Test
    public void testSync() {
        ArrayList<SyncTaskVO> syncTaskVOS = new ArrayList<>();
        syncTaskVOS.add(new SyncTaskVO()
                .setClientId(5)
                .setSyncType(CanalEntry.EventType.INSERT_VALUE)
                .setTaskName("新任务")
                .setTaskState(0)
                .setTaskDescription("单元测试同步添加的任务")
                .setTaskPriority(0)
                .setTaskDate(new Date()));
        syncTaskVOS.add(new SyncTaskVO()
                .setId(2)
                .setSyncType(CanalEntry.EventType.UPDATE_VALUE)
                .setTaskName("更新的任务")
                .setTaskState(0)
                .setTaskDescription("单元测试同步更新的任务")
                .setTaskPriority(0)
                .setTaskDate(new Date()));
        syncTaskVOS.add(new SyncTaskVO()
                .setId(2)
                .setSyncType(CanalEntry.EventType.DELETE_VALUE));
        assert !taskService.syncTasks(syncTaskVOS).isEmpty();
    }

    /**
     * 测试获取同步
     */
    @org.junit.Test
    public void testGetSync() {
        TaskMapStructMapper.INSTANCE.taskPO2VO(new Task());
        assert !taskService.getSync(new Date("Thu Jun 04 10:55:19 CST 2020"),97845234).isEmpty();
    }
}

package tech.chengw.www.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.chengw.www.entity.vo.InsertResultVO;
import tech.chengw.www.entity.vo.Message;
import tech.chengw.www.entity.vo.MessageType;
import tech.chengw.www.entity.vo.SyncTaskVO;
import tech.chengw.www.service.TaskServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * description 同步task表
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/11
 **/
@RestController
public class TaskSyncController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/task/sync")
    @ApiOperation("同步服务器task表")
    public Message<List<InsertResultVO>> syncTask(@ApiParam("同步数据") @RequestBody List<SyncTaskVO> syncBodys) {
        return new Message(MessageType.SUCCESS,taskService.syncTasks(syncBodys));
    }

    @GetMapping("/task/getSync")
    @ApiOperation("获取数据库task表同步")
    public Message<List<SyncTaskVO>> getSync(@ApiParam("上次同步时间")Date lastSyncDate) {
        return new Message<>(MessageType.SUCCESS, taskService.getSync(lastSyncDate));
    }
}

package tech.chengw.www.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.chengw.www.entity.vo.Message;
import tech.chengw.www.entity.vo.MessageType;
import tech.chengw.www.entity.vo.SyncTaskVO;
import tech.chengw.www.service.TaskServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/11
 **/
@RestController
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/tasks")
    @ApiOperation("获取服务器task列表")
    public Message tasks() {
        return new Message(MessageType.SUCCESS,taskService.getBaseMapper().selectAll());
    }

}

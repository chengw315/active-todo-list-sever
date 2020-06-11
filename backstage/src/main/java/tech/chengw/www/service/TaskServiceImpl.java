package tech.chengw.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.chengw.www.entity.po.Task;
import tech.chengw.www.dao.TaskMapper;
import tech.chengw.www.entity.vo.SyncTaskVO;
import tech.chengw.www.mapstruct.TaskMapStructMapper;
import tech.chengw.www.service.iService.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

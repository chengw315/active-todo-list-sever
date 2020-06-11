package tech.chengw.www;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.entity.po.Task;
import tech.chengw.www.service.TaskServiceImpl;

import java.util.List;
import java.util.Objects;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/9
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Slf4j
public class Test {

    @Autowired
    private TaskServiceImpl taskService;

    @org.junit.Test
    public void test() {
        List<Task> tasks = taskService.getBaseMapper().selectAll();
        assert Objects.nonNull(tasks);
    }
}

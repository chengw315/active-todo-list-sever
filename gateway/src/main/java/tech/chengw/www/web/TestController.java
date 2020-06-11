package tech.chengw.www.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.chengw.www.entity.vo.Message;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2020-04-17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/testConnection")
    public Message service1List() {
        return Message.SUCCESS;
    }

}


package tech.chengw.www;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.entity.dto.LoginDTO;
import tech.chengw.www.entity.dto.SignUpDTO;
import tech.chengw.www.entity.vo.Message;
import tech.chengw.www.entity.vo.UserVO;
import tech.chengw.www.service.impl.UserServiceImpl;

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
    private UserServiceImpl userService;

    /**
     * 测试注册
     */
    @org.junit.Test
    public void testSignUp() {
        String token1 = userService.signUp(new SignUpDTO()
                .setUserName("chengweiju")
                .setUserPassword("123456"));
        assert Objects.nonNull(token1);
    }

    /**
     * 测试登录
     */
    @org.junit.Test
    public void testLogin() {
        String token2 = userService.login(new LoginDTO()
                .setUserName("chengweij")
                .setUserPassword("123456"));
        assert Objects.nonNull(token2);
    }
}

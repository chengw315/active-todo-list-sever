package tech.chengw.www.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.chengw.www.entity.vo.Message;
import tech.chengw.www.entity.vo.MessageType;
import tech.chengw.www.entity.vo.UserVO;
import tech.chengw.www.mapstruct.UserMapStruct;
import tech.chengw.www.service.impl.UserServiceImpl;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/19
 **/
@Api("用户相关API")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation("用户注册")
    @PostMapping("/user/signUp")
    public Message<String> signUp(UserVO userVO) {
        String token = userService.signUp(UserMapStruct.INSTANCE.userVO2SignUpDTO(userVO));
        if(token != null) {
            return new Message(MessageType.SUCCESS,token);
        }
        return new Message(MessageType.ERROR);
    }

    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public Message<String> login(UserVO userVO) {
        String token = userService.login(UserMapStruct.INSTANCE.userVO2LoginDTO(userVO));
        if(token != null) {
            return new Message(MessageType.SUCCESS,token);
        }
        return new Message(MessageType.ERROR);
    }
}

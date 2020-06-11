package tech.chengw.www.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import tech.chengw.www.entity.dto.LoginDTO;
import tech.chengw.www.entity.dto.SignUpDTO;
import tech.chengw.www.entity.po.User;
import tech.chengw.www.dao.UserMapper;
import tech.chengw.www.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2020-05-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     * @param dto
     * @return 是否注册成功
     */
    public String signUp(SignUpDTO dto) {
        if(Objects.nonNull(userMapper.selectByUserName(dto.getUserName()))) {
            return null;
        }
        User user = new User()
                .setUserName(dto.getUserName())
                .setPassword(BCrypt.hashpw(dto.getUserPassword(), BCrypt.gensalt()))
                .setGmtCreateTime(new Date());
        if(userMapper.insertUser(user.getUserName(),user.getPassword()) > 0) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("userId", user.getId());
            map.put("userName", dto.getUserName());
            return Jwts.builder()
                    .setClaims(map)
                    .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 24 * 60 * 1000))
                    .signWith(SignatureAlgorithm.HS512, "456842")
                    .compact();
        }
        return null;
    }

    /**
     * 登录
     * @param dto
     * @return 登陆成功返回token，失败返回null
     */
    public String login(LoginDTO dto) {
        User user = userMapper.selectByUserName(dto.getUserName());
        if(BCrypt.checkpw(dto.getUserPassword(),user.getPassword())) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("userId",user.getId());
            map.put("userName",user.getUserName());
            return Jwts.builder()
                    .setClaims(map)
                    .setExpiration(new Date(System.currentTimeMillis()+30 * 60 * 24 * 60 * 1000))
                    .signWith(SignatureAlgorithm.HS512,"456842")
                    .compact();
        }
        return null;
    }
}

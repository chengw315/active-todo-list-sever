package tech.chengw.www.dao;

import org.apache.ibatis.annotations.Param;
import tech.chengw.www.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2020-05-19
 */
public interface UserMapper extends BaseMapper<User> {

    int insertUser(@Param("userName") String userName, @Param("password") String password);

    User selectByUserName(String userName);
}

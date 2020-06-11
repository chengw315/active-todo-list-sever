package tech.chengw.www.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.chengw.www.entity.dto.SignUpDTO;
import tech.chengw.www.entity.dto.LoginDTO;
import tech.chengw.www.entity.vo.UserVO;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/19
 **/
@Mapper
public interface UserMapStruct {

    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    SignUpDTO userVO2SignUpDTO(UserVO userVO);

    LoginDTO userVO2LoginDTO(UserVO userVO);
}

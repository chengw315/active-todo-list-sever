package tech.chengw.www.mapstruct;

import javax.annotation.Generated;
import tech.chengw.www.entity.dto.LoginDTO;
import tech.chengw.www.entity.dto.SignUpDTO;
import tech.chengw.www.entity.vo.UserVO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-11T13:49:41+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class UserMapStructImpl implements UserMapStruct {

    @Override
    public SignUpDTO userVO2SignUpDTO(UserVO userVO) {
        if ( userVO == null ) {
            return null;
        }

        SignUpDTO signUpDTO = new SignUpDTO();

        signUpDTO.setUserName( userVO.getUserName() );
        signUpDTO.setUserPassword( userVO.getUserPassword() );

        return signUpDTO;
    }

    @Override
    public LoginDTO userVO2LoginDTO(UserVO userVO) {
        if ( userVO == null ) {
            return null;
        }

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUserName( userVO.getUserName() );
        loginDTO.setUserPassword( userVO.getUserPassword() );

        return loginDTO;
    }
}

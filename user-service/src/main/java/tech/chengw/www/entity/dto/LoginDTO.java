package tech.chengw.www.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/19
 **/
@Data
@Accessors(chain = true)
@ApiModel("登录用DTO")
public class LoginDTO {
    @ApiModelProperty(value = "用户名", required = true, example = "15033593913")
    private String userName;
    @ApiModelProperty(value = "密码", required = true, example = "chengweij")
    private String userPassword;
}

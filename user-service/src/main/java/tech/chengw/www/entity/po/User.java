package tech.chengw.www.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    @TableId("id")
    private Integer id;

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("gmt_create_time")
    private Date gmtCreateTime;

    @TableField("gmt_modify_time")
    private Date gmtModifyTime;


}

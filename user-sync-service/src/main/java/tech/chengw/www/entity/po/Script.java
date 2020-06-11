package tech.chengw.www.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("script")
public class Script implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 脚本编号，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 脚本所属用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 脚本名称
     */
    @TableField("script_name")
    private String scriptName;

    /**
     * 脚本类型，目前1为打开文件的脚本；2为打开网页的脚本；3为用户自定义脚本
     */
    @TableField("script_type")
    private Integer scriptType;

    @TableField("gmt_create_time")
    private Date gmtCreateTime;

    @TableField("gmt_modify_time")
    private Date gmtModifyTime;


}

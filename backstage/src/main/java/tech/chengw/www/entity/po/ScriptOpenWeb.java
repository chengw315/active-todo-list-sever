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
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("script_open_web")
public class ScriptOpenWeb implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("id")
    private Integer id;

    /**
     * 脚本编号
     */
    @TableField("script_id")
    private Integer scriptId;

    /**
     * 要打开的网页的url
     */
    @TableField("url")
    private String url;

    @TableField("gmt_create_time")
    private Date gmtCreateTime;

    @TableField("gmt_modify_time")
    private Date gmtModifyTime;


}

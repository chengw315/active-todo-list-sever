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
@TableName("script_open_file")
public class ScriptOpenFile implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 脚本编号
     */
    @TableField("script_id")
    private Integer scriptId;

    /**
     * 要打开的文件的路径
     */
    @TableField("path")
    private String path;

    @TableField("gmt_create_time")
    private Date gmtCreateTime;

    @TableField("gmt_modify_time")
    private Date gmtModifyTime;


}

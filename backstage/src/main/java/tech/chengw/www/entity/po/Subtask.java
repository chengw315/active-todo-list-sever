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
@TableName("subtask")
public class Subtask implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 子任务编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属用户的编号
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 根任务的任务编号
     */
    @TableField("root_id")
    private Integer rootId;

    /**
     * 父任务编号（父任务为subtask时才生效） 
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 绑定的脚本编号
     */
    @TableField("script_id")
    private Integer scriptId;

    /**
     * 子任务名
     */
    @TableField("subtask_name")
    private String subtaskName;

    /**
     * 子任务状态，1为已完成，0为未完成
     */
    @TableField("subtask_state")
    private Boolean subtaskState;

    @TableField("gmt_create_time")
    private Date gmtCreateTime;

    @TableField("gmt_modify_time")
    private Date gmtModifyTime;


}

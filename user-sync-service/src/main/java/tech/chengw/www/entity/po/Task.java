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
@TableName("task")
public class Task implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 任务编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务所属用户
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 任务名
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 任务优先级
     */
    @TableField("task_priority")
    private Integer taskPriority;

    /**
     * 任务状态，1为已完成,0为未完成
     */
    @TableField("task_state")
    private Integer taskState;

    /**
     * 任务描述
     */
    @TableField("task_description")
    private String taskDescription;

    /**
     * 任务日期
     */
    @TableField("task_date")
    private Date taskDate;

    @TableField("gmt_create_time")
    private Date gmtCreateTime;

    @TableField("gmt_modify_time")
    private Date gmtModifyTime;


}

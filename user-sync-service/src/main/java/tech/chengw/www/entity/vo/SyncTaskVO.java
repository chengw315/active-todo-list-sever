package tech.chengw.www.entity.vo;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/11
 **/
@Data
@Accessors(chain = true)
@ApiModel("同步任务所使用的参数集")
public class SyncTaskVO {

    @ApiModelProperty(value = "同步类型，1为增，2为改，3为删",required = true,example = "3")
    private Integer syncType;
    @ApiModelProperty(value = "服务器端这条数据的id，如果是新增数据，无需此参数",example = "2")
    private Integer id;
    @ApiModelProperty(value = "客户端端这条数据的id，主要用于新增数据，无需此参数",example = "2")
    private Integer clientId;
    @ApiModelProperty(value = "任务名，删-无需此参数",example = "中期报告")
    private String taskName;
    @ApiModelProperty(value = "任务优先级，删-无需",example = "3")
    private Integer taskPriority;
    @ApiModelProperty(value = "任务状态，1为已完成,0为未完成,删-无需",example = "0")
    private Integer taskState;
    @ApiModelProperty(value = "任务描述，删-无需",example = "中期报告b la b la b la")
    private String taskDescription;
    @ApiModelProperty(value = "任务日期，删-无需",example = "2020-05-11")
    private Date taskDate;

    /**
     * 是否是指定类型同步
     * @param type
     * @return
     */
    private boolean isSyncType(int type) {
        if(syncType == null) {
            return false;
        }
        return syncType.equals(type);
    }

    public boolean isInsertSync() {
        return isSyncType(CanalEntry.EventType.INSERT_VALUE);
    }

    public boolean isDeleteSync() {
        return isSyncType(CanalEntry.EventType.DELETE_VALUE);
    }

    public boolean isUpdateSync() {
        return isSyncType(CanalEntry.EventType.UPDATE_VALUE);
    }
}

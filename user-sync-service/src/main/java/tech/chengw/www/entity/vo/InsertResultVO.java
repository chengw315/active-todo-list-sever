package tech.chengw.www.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/10
 **/
@Data
@Accessors(chain = true)
@ApiModel("同步任务返回的插入结果")
public class InsertResultVO {
    @ApiModelProperty(value = "服务器端这条数据的id，如果是新增数据，无需此参数",example = "2")
    private Integer severId;
    @ApiModelProperty(value = "客户端端这条数据的id，主要用于新增数据，无需此参数",example = "2")
    private Integer clientId;
}

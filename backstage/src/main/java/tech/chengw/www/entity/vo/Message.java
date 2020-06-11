package tech.chengw.www.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据返回对象
 * @author chengwj
 * @date 2020/4/17
 * @version 1.0
 **/
@ApiModel("统一返回对象")
public class Message<T> {

    @ApiModelProperty("返回码")
    private String code;
    @ApiModelProperty("结果信息")
    private String msg;
    @ApiModelProperty("数据")
    private T data;

    private Message() {
    }

    public Message(MessageType messageType) {
        this.code = messageType.getCode();
        this.msg = messageType.getMsg();
        this.data = null;
    }

    public Message(MessageType messageType,T data) {
        this.code = messageType.getCode();
        this.msg = messageType.getMsg();
        this.data = data;
    }

    public static final Message SUCCESS = new Message(MessageType.SUCCESS,null);
    public static final Message ERROR = new Message(MessageType.ERROR, null);

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}

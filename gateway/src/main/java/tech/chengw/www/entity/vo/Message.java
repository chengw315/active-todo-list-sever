package tech.chengw.www.entity.vo;

/**
 * 数据返回对象
 * @author chengwj
 * @date 2020/4/17
 * @version 1.0
 **/
public class Message {

    private String code;
    private String msg;
    private Object data;

    private Message() {
    }

    public Message(MessageType messageType,Object data) {
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

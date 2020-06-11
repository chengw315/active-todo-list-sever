package tech.chengw.www.entity.vo;

/**
 * 数据返回对象
 * @author chengwj
 * @date 2020/4/17
 * @version 1.0
 **/
public class Message<T> {

    private String code;
    private String msg;
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

    public T getData() {
        return data;
    }
}

package tech.chengw.www.entity.vo;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import tech.chengw.www.UserServiceApplication;

/**
 * 数据返回对象的类型。如果想要增加类型，需要通过添加静态MessageType对象实现
 * @author chengwj
 * @date 2020/4/17
 * @version 1.0
 **/
public class MessageType {

    //国际化
    private static MessageSource messageSource = UserServiceApplication.getBean(MessageSource.class);

    private String code;
    private String msg;
    private String msg_English;

    private MessageType() {}
    protected MessageType(String code, String msg, String msg_English) {
        this.code = code;
        this.msg = msg;
        this.msg_English = msg_English;
    }

    public static final MessageType SUCCESS = new MessageType("8001","操作成功","MSG_SUCCESS");
    public static final MessageType ERROR = new MessageType("4001","操作失败","MSG_ERROR");


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return messageSource.getMessage(msg_English,null,msg, LocaleContextHolder.getLocale());
    }
}

package tech.chengw.www.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author chengw
 */
public class TokenUtil {
    static Logger logger = LoggerFactory.getLogger(TokenUtil.class);


    public static Map<String, Object> getUser() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");
        if (token == null) {
            logger.error("token为空");
            return null;
        }

        try {
            String userToken = token.split("\\.")[1];
            return  (Map) JSON.parse(new String(Base64.decodeBase64(userToken)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

    }

    public static Integer getUserId() {
        return Optional.ofNullable(getUser())
                .map(user -> (Integer)user.get("userId"))
                .orElse(97845234);
    }

}


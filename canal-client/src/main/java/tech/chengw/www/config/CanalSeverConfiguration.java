package tech.chengw.www.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/20
 **/
@Data
@ConfigurationProperties(prefix = "canal")
public class CanalSeverConfiguration {
    private String ip;
    private int port;
    private String destination;
    private String userName;
    private String password;
}

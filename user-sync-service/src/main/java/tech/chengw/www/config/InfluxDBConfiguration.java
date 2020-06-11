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
@ConfigurationProperties(prefix = "influx")
public class InfluxDBConfiguration {
    private String url;
    private String user;
    private String password;
    private String database;
}

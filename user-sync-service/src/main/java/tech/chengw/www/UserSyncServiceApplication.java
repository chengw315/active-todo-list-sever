package tech.chengw.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import tech.chengw.www.config.InfluxDBConfiguration;

/**
 * @author chengw
 */
@MapperScan("tech.chengw.www.dao")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(InfluxDBConfiguration.class)
public class UserSyncServiceApplication extends SpringBootServletInitializer implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(UserSyncServiceApplication.class,args);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }
}

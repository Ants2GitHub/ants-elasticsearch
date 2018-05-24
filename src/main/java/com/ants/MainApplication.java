package com.ants;

import com.ants.utils.ConfigLogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
  * @description SpringBoot 启动的主入口
  * @author ants·ht
  * @date 2018/5/24 11:29
*/
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.ants"})
@EnableScheduling
@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer implements ApplicationListener<ApplicationReadyEvent> {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        ConfigLogUtil.printLog();
    }
}

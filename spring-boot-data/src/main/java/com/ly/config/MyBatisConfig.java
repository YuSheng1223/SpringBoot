package com.ly.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-data
 * @description: 自定义MyBatis规则
 * @author: Mr.L
 * @create: 2019-02-06 20:49
 **/
@Configuration
public class MyBatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                //开启实体类与数据库字段之间的驼峰命名与非驼峰方式的映射
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}

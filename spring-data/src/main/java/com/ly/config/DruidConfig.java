package com.ly.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring-data
 * @description: druid配置
 * @author: Mr.L
 * @create: 2019-02-05 22:17
 **/
@Configuration
public class DruidConfig {

    // 将配置文件中已spring.datasource开头的配置进行绑定
    //将dataDource加载到容器中
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    //配置durid监控
    //1.首先配置一个管理后台的servlet  处理进入后台的请求
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //拦截/druid/*下的请求
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams  = new HashMap<String, String>();
        initParams.put("loginUsername", "admin");//后台管理登录账户名称
        initParams.put("loginPassword", "123456");//后台管理登录账户密码
        initParams.put("allow", "");// 默认允许所有
        initParams.put("deny", "192.168.1.103");//拒绝哪个访问


        bean.setInitParameters(initParams);
        return  bean;
    }


    //2. 配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String,String> initParams  = new HashMap<String, String>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");//哪些请求不拦截
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return  filterRegistrationBean;
    }
}

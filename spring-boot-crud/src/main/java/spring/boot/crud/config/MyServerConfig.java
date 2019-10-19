package spring.boot.crud.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.boot.crud.Filter.MyFilter;
import spring.boot.crud.Listener.MyListener;
import spring.boot.crud.servlet.MyServlet;

import java.util.Arrays;

/**
 * @program: sprng-boot-crud
 * @description:
 * @author: Mr.L
 * @create: 2019-01-27 16:34
 **/
@Configuration
public class MyServerConfig {
    //注册三大组件
    //servlet注册
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        return  servletRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myFilter"));
        return filterRegistrationBean;

    }
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MyListener());
        return  servletListenerRegistrationBean;
    }
}

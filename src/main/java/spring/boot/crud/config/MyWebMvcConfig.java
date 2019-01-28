package spring.boot.crud.config;

import org.jboss.logging.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import spring.boot.crud.componment.MyInterceptor;
import spring.boot.crud.componment.MyLocalResolver;

/**
 * @program: sprng-boot-crud
 * @description: 使用WebMvcConfigurerAdapter来自定义mvc配置 2.0以后 有所更改
 * @author: Mr.L
 * @create: 2019-01-05 15:35
 **/
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //浏览器发送/webMvc 请求来到success
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/login.html").setViewName("login");
//        registry.addViewController("/index.html").setViewName("login");
//        registry.addViewController("/main.html").setViewName("lineChart");
      //  registry.addViewController("/").setViewName("login");
      //  registry.addViewController("/login").setViewName("login");
    }
    /**
    * @Description:  将自定义的区域信息添加到组件中  webMvcConfigurerAdapter 在spring5.0中被弃用
    * @Param: []
    * @return: org.springframework.web.servlet.LocaleResolver
    * @Author: Mr.L
    * @Date: 2019/1/6 0006
    */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter(){

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
               // super.addViewControllers(registry);
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                System.out.println("---------------------");
                registry.addViewController("/main.html").setViewName("lineChart");
                registry.addViewController("/index.html").setViewName("login");
            }

            /**
            * @Description:  MyInterceptor 是自定义的拦截器  但是要添加到容器中 提供了WebMvcConfigurerAdapter 这个类
             * 让我们定义一些mvc的功能
            * @Param: []
            * @return: WebMvcConfigurerAdapter
            * @Author: Mr.L
            * @Date: 2019/1/8 0008
            */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               // super.addInterceptors(registry);
              //  registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html","/","/user/login");
            }
        };
        return webMvcConfigurerAdapter;
    }

    //*注册拦截器
/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // super.addInterceptors(registry);
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("login.html","/","/user/login");
    }*/
}

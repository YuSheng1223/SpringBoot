package spring.boot.crud.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @program: sprng-boot-crud
 * @description: 自定义监听器
 * @author: Mr.L
 * @create: 2019-01-27 16:47
 **/
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("~~~~~~~~~~~~~~~~web开始初始化~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("~~~~~~~~~~~~~~~~web开始销毁~~~~~~~~~~~~~~");
    }
}

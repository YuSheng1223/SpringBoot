package spring.boot.crud.Filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: sprng-boot-crud
 * @description: 自定义拦截器
 * @author: Mr.L
 * @create: 2019-01-27 16:40
 **/
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---------------自定义过滤器初始化-----------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("-----------------------自定义过滤器执行--------------------------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("-----------------------自定义过滤器销毁-------------------------");
    }
}

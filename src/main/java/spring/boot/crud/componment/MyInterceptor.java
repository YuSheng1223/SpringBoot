package spring.boot.crud.componment;

import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: sprng-boot-crud
 * @description: 登录拦截器
 * @author: Mr.L
 * @create: 2019-01-06 17:46
 **/
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object user = httpServletRequest.getSession().getAttribute("username");
        if (user == null){
            //未登录
            httpServletRequest.setAttribute("msg", "没有权限，请先登录");
            System.out.println("--------------没有权限-------------------");
            httpServletRequest.getRequestDispatcher("/login.html").forward(httpServletRequest, httpServletResponse);
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

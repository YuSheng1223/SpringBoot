package spring.boot.crud.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.boot.crud.exception.UserNotExistException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: sprng-boot-crud
 * @description: 自定义异常处理器
 * @author: Mr.L
 * @create: 2019-01-23 23:27
 **/

//表示为异常处理器
@ControllerAdvice
public class MyExceptionHandler {


    //这种异常处理的方式  会使得浏览器访问或者客户端访问返回的都是json数据
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handler(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code", "user not exist!");
//        map.put("message", e.getMessage());
//        return map;
//    }


    @ExceptionHandler(UserNotExistException.class)
    public String handler(Exception e, HttpServletRequest request){
//        Integer statusCode = (Integer) request
//                .getAttribute("javax.servlet.error.status_code");
        //设置错误状态码  否则不会进入错误处理流程
        request.setAttribute("javax.servlet.error.status_code", 400);
        Map<String,Object> map = new HashMap<>();
        map.put("code", "user not exist!");
        map.put("message", e.getMessage());

        request.setAttribute("ext", map);
        return "forward:/error";
    }
}

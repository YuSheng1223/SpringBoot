package com.xs.aop;

import com.xs.bean.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @ClassName ControllerAdviceAspect
 * @Description 全局异常处理
 * @Author
 * @Date 2019-10-23 下午 5:18
 * @Version V1.0
 */
@Slf4j
@Order(1)
@RestControllerAdvice
public class ControllerAdviceAspect {


    /****
     * 处理controller抛出的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest request, Exception e) {


        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setData("");
        responseMessage.setStatus("600");
        responseMessage.setMessage(((UndeclaredThrowableException)e).getUndeclaredThrowable().getMessage());

        return responseMessage;
    }


}

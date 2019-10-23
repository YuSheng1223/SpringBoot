package com.xs.aop;

import com.alibaba.fastjson.JSONObject;
import com.xs.annotations.ForbidRepeatCommit;
import com.xs.bean.RequestMessage;
import com.xs.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @ClassName RepeatCommitAspect
 * @Description 校验重复提交切面  使用token机制
 * @Author
 * @Date 2019-10-23 下午 2:53
 * @Version V1.0
 */
@Aspect
@Slf4j
@Component
public class RepeatCommitAspect {


    @Autowired
    TokenService tokenService;

    /***
     * controller包下所有的类所有方法 排除前台主动获取token类
     */
    @Pointcut("execution(public * com.xs.controller..*(..))  && !execution(public * com.xs.controller.TokenController.*(..)) ")
    public void verifyRequestToken() {

    }

    /***
     * 重复提交token校验
     * @param joinPoint
     * @throws Exception
     */
    @Before("verifyRequestToken()")
    public void execVerify(JoinPoint joinPoint) throws Exception {

        //类
        Object target = joinPoint.getTarget();
        //方法名称
        String methodName = joinPoint.getSignature().getName();

        if(StringUtils.isEmpty(methodName)){

            log.error(" 验证表单重复提交切入异常，被切入的类 "+target.toString()+" 中不存在方法 ");

            throw new Exception("验证表单重复提交切入异常");
        }


        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        //通过方法名称和参数列表定位方法
        Method method = target.getClass().getMethod(methodName,parameterTypes);

        //方法上包含禁止重复提交注解
        if(method.isAnnotationPresent(ForbidRepeatCommit.class)){

            Object param = joinPoint.getArgs()[0];
            //包装接口请求参数
            RequestMessage requestMessage = JSONObject.parseObject(param.toString(), RequestMessage.class);

            //校验token  判断是否为重复提交
             tokenService.checkTokenByToken(requestMessage);

        }

    }
}

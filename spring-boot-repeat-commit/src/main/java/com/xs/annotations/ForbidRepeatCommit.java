package com.xs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ForbidRepeatCommit
 * @Description 禁止重复提交注解
 * @Author
 * @Date 2019-10-23 上午 11:12
 * @Version V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ForbidRepeatCommit {

    String value() default "";

}

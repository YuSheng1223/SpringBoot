package com.xs.controller;

import com.xs.annotations.ForbidRepeatCommit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author
 * @Date 2019-10-23 下午 2:49
 * @Version V1.0
 */
@RestController
@Slf4j
public class HelloController {


    @ForbidRepeatCommit
    @RequestMapping("/hello")
    public Object method01(String  data){

        log.info(" 接口请求参数   ：  {} ",data);

        return "11111";
    }
}

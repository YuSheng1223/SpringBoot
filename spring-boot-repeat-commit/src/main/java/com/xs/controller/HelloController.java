package com.xs.controller;

import com.xs.annotations.ForbidRepeatCommit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName HelloController
 * @Description
 * @Author
 * @Date 2019-10-23 下午 2:49
 * @Version V1.0
 */
@Controller
@Slf4j
public class HelloController {

    @ResponseBody
    @ForbidRepeatCommit
    @RequestMapping("/hello")
    public Object method01(String  data){


        return "11111";
    }


  //  @ResponseBody
    @RequestMapping("/page")
    public String ftlIndex() {
        log.info(" 跳转到页面 ");
        return "user/index";
    }
}

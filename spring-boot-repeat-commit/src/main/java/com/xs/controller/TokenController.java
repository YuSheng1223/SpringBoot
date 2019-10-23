package com.xs.controller;

import com.xs.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName TokenController
 * @Description
 * @Author
 * @Date 2019-10-23 下午 2:17
 * @Version V1.0
 */
@Slf4j
@Controller
public class TokenController {


    @Autowired
    TokenService tokenService;



    @RequestMapping("/getToken")
    @ResponseBody
    public Object getToken(){

        String token = tokenService.getToken();

        return token;
    }

}

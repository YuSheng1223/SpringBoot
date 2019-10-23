package com.xs.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RequestParam
 * @Description
 * @Author
 * @Date 2019-10-23 下午 3:49
 * @Version V1.0
 */
@Data
public class RequestMessage implements Serializable {


    /***
     * 重复提交token
     */
    private String token;
    /***
     * json数据
     */
    private String data;
    /***
     * 加密报文
     */
    private String encryptMessage;

}

package com.xs.bean;

import lombok.Data;

/**
 * @ClassName ResponseMessage
 * @Description 统一响应参数
 * @Author
 * @Date 2019-10-23 下午 5:18
 * @Version V1.0
 */
@Data
public class ResponseMessage {

    /****
     * 响应结果
     */
    private String data;
    /****
     * message
     */
    private String message;
    /***
     * 加密结果
     */
    private String encryptMessage;
    /****
     * 状态码
     */
    private String status;



}

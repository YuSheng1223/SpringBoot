package com.xs.service;

import com.xs.bean.RequestMessage;

/**
 * @ClassName TokenService
 * @Description token接口
 * @Author
 * @Date 2019-10-23 下午 2:20
 * @Version V1.0
 */
public interface TokenService {

    /****
     * 获取唯一token
     * @return
     */
    String getToken();

    /***
     * token校验 通过token机制
     * @param requestMessage
     * @return
     * @throws Exception
     */
    void checkTokenByToken(RequestMessage requestMessage) throws  Exception;

    /***
     * token校验  通过sessionId+url机制
     * @param key
     * @return
     * @throws Exception
     */
    void checkTokenBySessionAndUrl(String key) throws  Exception;


}

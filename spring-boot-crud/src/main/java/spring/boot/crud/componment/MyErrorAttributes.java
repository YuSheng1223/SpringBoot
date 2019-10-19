package spring.boot.crud.componment;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @program: sprng-boot-crud
 * @description: 自定义错误信息返回
 * @author: Mr.L
 * @create: 2019-01-27 15:37
 **/
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    /**
    * @Description:返回值的map就是页面和json能获取的所有字段
    * @Author: Mr.L
    * @Date: 2019/1/27 0027
    */
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company", "LIUSHENG");

        Map<String,Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
        map.put("ext", ext);
        return map;
    }
}

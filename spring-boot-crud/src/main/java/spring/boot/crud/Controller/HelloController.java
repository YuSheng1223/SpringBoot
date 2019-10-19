package spring.boot.crud.Controller;

import org.jboss.logging.Param;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import  org.springframework.stereotype.Service;
import spring.boot.crud.exception.UserNotExistException;

import java.util.Map;

/**
 * @program: sprng-boot-crud
 * @description:
 * @author: Mr.L
 * @create: 2018-12-16 14:32
 **/
@Controller
//扫描当前包及子包  但是只有为@Service标注的类才会扫描
//@ComponentScan(includeFilters={@ComponentScan.Filter(classes = {Service.class})})
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(@RequestParam("user")String user){
        if("ly".equals(user)){
            throw new UserNotExistException();
        }
        return "hello";
    }
    /**
    * @Description:  使用thymeleaf  会直接调到success.html
     *     并且携带参数
    * @Param:
    * @return:
    * @Author: Mr.L
    * @Date: 2018/12/22 0022
    */
    @ResponseBody
    @RequestMapping("/success")
    public Object success(Map<String,Object>  map){
        map.put("name", "Li");
        map.put("class", "mydiv");
        map.put("id", "div01");
        return map;
    }

}

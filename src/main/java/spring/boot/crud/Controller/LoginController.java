package spring.boot.crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: sprng-boot-crud
 * @description: 登录
 * @author: Mr.L
 * @create: 2019-01-06 16:44
 **/
@Controller
public class LoginController {



    @PostMapping(value = "/user/login")
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username, @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        System.out.println("方法进去Controller");
        if(!StringUtils.isEmpty(username)  && "123456".equals(password)){
            session.setAttribute("username", username);
            //防止表单重复提交 可以使用重定向到主页
            return "redirect:/main.html";
        }else {
            map.put("msg", "用户名密码错误！");
            return "login";
        }

    }
}

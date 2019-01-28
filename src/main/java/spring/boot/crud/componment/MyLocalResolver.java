package spring.boot.crud.componment;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @program: sprng-boot-crud
 * @description: 国际化区域信息  区域信息解析器 要实现 LocaleResolver 接口
 * @author: Mr.L
 * @create: 2019-01-06 16:02
 **/
public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l = httpServletRequest.getParameter("l");

        Locale  locale = Locale.getDefault();
        // 如果参数中没有带信息  则使用默认的 如果有 就将语言和国家代码传入 构造一个新的Local
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1] );
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}

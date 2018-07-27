package cn.ep.spring.boot.ch04.s04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.*;
import java.util.Locale;

@RestController
public class CustomController {

    @Autowired
    private LocaleMessageSourceService service;

    @GetMapping("/")
    public String hello() {
        return service.getMessage("welcome");
    }

    /**
     * http://localhost:8080/changeSessionLanauage
     * http://localhost:8080/changeSessionLanauage?lang=en
     * http://localhost:8080/changeSessionLanauage?lang=zh
     */
    @RequestMapping("/changeSessionLanauage")
    public String changeSessionLanauage(HttpServletRequest request, HttpServletResponse response, String lang) {
        System.out.println(lang);
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if ("zh".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        } else if ("en".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        } else {
            localeResolver.setLocale(request, response, Locale.ROOT);
        }
        return hello();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        //设置默认区域,
        slr.setDefaultLocale(Locale.getDefault());
        return slr;
    }

}

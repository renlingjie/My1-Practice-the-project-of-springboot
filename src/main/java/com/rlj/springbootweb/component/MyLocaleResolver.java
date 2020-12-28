package com.rlj.springbootweb.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String XXX = httpServletRequest.getParameter("XXX");
        Locale locale = Locale.getDefault();//初始赋系统默认值
        if (!StringUtils.isEmpty(XXX)){
            //我们的两个请求参数都是xx_YY，所以要分割，然后得到一个有xx、YY的数组
            String[] split = XXX.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}

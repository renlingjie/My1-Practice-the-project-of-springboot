package com.rlj.springbootweb.config;

import com.rlj.springbootweb.component.LoginHandlerInterceptor;
import com.rlj.springbootweb.component.MyLocaleResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //配置视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main").setViewName("dashboard");
    }
    //配置国际化Locale(区域信息对象)/LocaleResolver(获取区域信息对象)
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //扩展我们手写的拦截器
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //"/**"表示拦截任意请求，然后不用拦截的请求/资源我们再通过excludePathPatterns排除不能拦截的请求
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/dashboard","/asserts/**","/webjars/**");
    }*/

}

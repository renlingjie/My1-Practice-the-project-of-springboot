package com.rlj.springbootweb.config;

import com.rlj.springbootweb.filter.MyFilter;
import com.rlj.springbootweb.listener.MyListener;
import com.rlj.springbootweb.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServerConfig {
    //进行Servlet容器的配置
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory>
    webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                //在这里面通过factory.setXXX来进行配置
                factory.setPort(8082);
            }
        };
    }

    //注册三大组件之servlet程序
    @Bean//千万要记得加这个
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean servletRegistrationBean =
                //这个类通过有参构造，里面传入请求拦截路径，以及拦截到该路径应该执行的Servlet程序，得到
                //这个对象，作为返回值，通过@Bean添加到容器中生效
                new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return servletRegistrationBean;
    }

    //注册三大组件之Filter
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    //注册三大组件之Listener
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean =
                new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }
}

package com.rlj.springbootweb.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
@Component
//DefaultErrorAttributes导包有两个同名，应该导入的包是：
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
public class OtherClientErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String,Object> map = super.getErrorAttributes(webRequest,options);
        //往其他客户端的json里添加一些自制的数据
        map.put("company","atguigu");
        return map;
    }
}

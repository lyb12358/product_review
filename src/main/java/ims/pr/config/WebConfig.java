package ims.pr.config;

import ims.pr.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author lyb
 * @create 2019-04-10 13:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler     是指你想在url请求的路径
        //addResourceLocations   是图片存放的真实路径
        if (System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0) {
            registry.addResourceHandler("/image/**").addResourceLocations("file:/Users/lyb/Desktop/image/");
        } else if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
            registry.addResourceHandler("/image/**").addResourceLocations("file:G:/image/");
        } else {
            registry.addResourceHandler("/image/**").addResourceLocations("file:/Users/lyb/Desktop/image/");
        }
    }
}
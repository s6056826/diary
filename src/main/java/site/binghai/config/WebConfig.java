package site.binghai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.binghai.inters.UserInterceptor;

/**
 * Created by IceSea on 2018/5/13.
 * GitHub: https://github.com/IceSeaOnly
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public UserInterceptor userInter(){
        return new UserInterceptor();
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login").setViewName("/html/user/login");
        registry.addViewController("add").setViewName("/html/jie/add");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInter()).addPathPatterns("/user/**")
                .excludePathPatterns("/user/comment/list*")
                .excludePathPatterns("/user/diary/list*");
    }

}

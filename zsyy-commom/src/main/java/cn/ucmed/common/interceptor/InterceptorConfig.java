package cn.ucmed.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    //排除后台等相关的路径，其他的全部拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/**/*.css").excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.png").excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.gif").excludePathPatterns("/**/*.gif")
                .excludePathPatterns("/show/**").excludePathPatterns("/api/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/FontIcoList.html")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/doc.html/**")
                .excludePathPatterns("/wechat/autoLogin")
                .excludePathPatterns("/pc/autoLogin")
                .excludePathPatterns("/userCenter/autoLogin")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/wx/captcha.jpg")
                .excludePathPatterns("/provide/wx/**")
                .excludePathPatterns("/patientVisit/detail")
                .excludePathPatterns("/functionSwitch/**");
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

}

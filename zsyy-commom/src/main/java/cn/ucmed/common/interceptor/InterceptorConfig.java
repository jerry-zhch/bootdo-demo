package cn.ucmed.common.interceptor;

import cn.ucmed.common.config.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private CommonProperties commonProperties;

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
                .excludePathPatterns("/*.js.map").excludePathPatterns("/echarts/pie")

                .excludePathPatterns("/open/**")

                .excludePathPatterns("/**/*.png").excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.gif").excludePathPatterns("/**/*.gif")
                .excludePathPatterns("/show/**").excludePathPatterns("/api/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/FontIcoList.html")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/doc.html/**","/swagger-ui.html")
                .excludePathPatterns("/wechat/autoLogin")
                .excludePathPatterns("/pc/autoLogin")
                .excludePathPatterns("/userCenter/autoLogin")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/wx/captcha.jpg")
                .excludePathPatterns("/provide/wx/**")
                .excludePathPatterns("/patientVisit/detail")
                .excludePathPatterns("/functionSwitch/**");
    }

    //地址映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**").addResourceLocations("file:///"+commonProperties.getProfile());
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

}

package cn.ucmed.common.utils;

import cn.ucmed.common.api.Api;
import cn.ucmed.common.exception.BusinessException;
import cn.ucmed.common.utils.result.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring的上下文和其中的bean
 */
@Component
public class SpringBean implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBean.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    public static void assertApplicationContext() {
        if (SpringBean.applicationContext == null) {
            throw new BusinessException(-1,"applicaitonContext属性为null,请检查是否注入了SpringContextHolder!");
        }
    }

    /**
     * 获取API结果
     */
    public static Result exec(String apiName, JSONObject params){
        Api api = getBean(apiName);
        return api.exec(params);
    }
}

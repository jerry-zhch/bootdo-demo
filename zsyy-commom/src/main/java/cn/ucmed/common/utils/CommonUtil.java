package cn.ucmed.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 公共方法
 */
@Slf4j
@Component
public class CommonUtil {

    public static final String ACCEPT =
            "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5";

    /**
     * 放入头信息
     */
    public HttpHeaders headers(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return headers;
    }

    /**
     * 放入头信息
     */
    public HttpHeaders headers(String mediaType) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(mediaType);
        headers.setContentType(type);
        headers.add("Accept",ACCEPT);
        return headers;
    }

    /**
     * 枚举转json
     */
    public static JSONArray enumToJson(Object[] enumArr) {
        JSONArray jsonArr = new JSONArray();
        for (Object e : enumArr) {
            Class clasz = e.getClass();
            JSONObject json = new JSONObject();
            Field[] fieldArr = clasz.getDeclaredFields();
            for (Field field : fieldArr) {
                String filedName = field.getName();
                if (filedName.startsWith("ENUM")) {
                    continue;
                }
                try {
                    String methodName = "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
                    Method m = clasz.getMethod(methodName);
                    Object value = m.invoke(e);
                    json.put(filedName, value);
                } catch (Exception ex) {
                    log.error("enumToJson ex error==>"+ex);
                }
            }
            jsonArr.add(json);
        }
        return jsonArr;
    }

    /**
     * 生成一定长度的数字验证码
     */
    public static String getRamdomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) { //生成length位数字验证码
            int j = (int) (Math.random()*10);
            sb.append(j);
        }
        return sb.toString();
    }

}

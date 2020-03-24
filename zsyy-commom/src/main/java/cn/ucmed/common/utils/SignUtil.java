package cn.ucmed.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 签名方法
 */
public class SignUtil {

    private static final char a[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };
    public static String getSign(String req, String key) {
        Map<String, Object> map = (Map<String, Object>) JSON.parse(req);
        Map<String, String> data = removeSign(map);

        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        return Objects.requireNonNull(MD5Utils.MD5(sb.toString())).toUpperCase();
    }

    public static Map<String, String> removeSign(Map<String, Object> map) {
        Map<String, String> data = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if ("sign".equals(entry.getKey())) {
                continue;
            }
            String value = entry.getValue().toString();
            if (org.springframework.util.StringUtils.isEmpty(value)) {
                continue;
            }
            data.put(entry.getKey(), value);
        }
        return data;
    }

}

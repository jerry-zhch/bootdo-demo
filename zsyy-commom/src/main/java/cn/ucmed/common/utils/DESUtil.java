package cn.ucmed.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;

/**
 * des 加密
 */
public class DESUtil {

    public static final String DESUtilKEY = "389aefb7eab644fb947d906c03de16f6";

    /**
     * 加密为16进制表示
     */
    public static String encryptHex(String content) {
        return getDES().encryptHex(content);
    }

    /**
     * 解密为字符串
     */
    public static String decryptStr(String encryptHex) {
        if(encryptHex == null) {
            return null;
        }
        return getDES().decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
    }

    public static DES getDES() {
        byte[] key = DESUtilKEY.getBytes();
        // 构建`
        return SecureUtil.des(key);
    }
}

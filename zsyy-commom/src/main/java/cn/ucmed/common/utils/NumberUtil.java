package cn.ucmed.common.utils;

import cn.ucmed.common.exception.BusinessException;
import cn.ucmed.common.exception.PayException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class NumberUtil {

    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();

    /**
     * 保留两位小数
     */
    public static String retainedDecimal(String money) {
        return retainedDecimal(money,"#0.00");
    }

    public static String retainedDecimal(String money,String dfStr) {
        String str = money;
        try {
            DecimalFormat df = new DecimalFormat(dfStr);
            str = df.format(Double.parseDouble(money));
        } catch (Exception e) {
            log.error("retainedDecimal error==>"+e);
        }
        return str;
    }

    /**
     * 随机2位数
     */
    public static String random() {
        return frontCompWithZore(new Random().nextInt(99), 2);
    }

    /**
     * 0 指前面补充零 formatLength 字符总长度为 formatLength d 代表为正数。
     */
    public static String frontCompWithZore(int sourceDate,int formatLength) {
        return String.format("%0" + formatLength + "d", sourceDate);
    }

    /**
     * 暂定支付订单自增小于10000
     */
    public static String decimalFormatTo4(int i) throws BusinessException {
        if(i > 9999) {
            throw new BusinessException(1, "this number is OutOfBoundsException");
        }
        return frontCompWithZore(i, 4);
    }

    /**
     * 转为正整数
     */
    public static Long transferToLong(String totalMoney) {
        if(!isNumeric(totalMoney)) {
            throw new PayException(-1, "money is not a number");
        }
        try {
            return Long.valueOf(totalMoney);
        } catch (Exception e) {

        }
        throw new PayException(-1, "money transfer exception");
    }

    /**
     * 判断是否正整数
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[1-9][0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 元转分，确保price保留两位有效数字
     */
    public static String changeY2F(String price) {
        return new BigDecimal(price).movePointRight(2).toString();
    }

    /**
     * 获取随机字符串 Nonce Str
     */
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }


    /**
     * 单位分转元
     */
    public static Double formatAmount(Long amount) {
        return BigDecimal.valueOf(amount, 2).doubleValue();
    }

    /**
     * 单位分转元
     */
    public static String formatAmountLongToString(Long amount) {
        return BigDecimal.valueOf(amount, 2).toString();
    }

    /**
     * 单位分转元
     */
    public static Double formatAmount(String amount) {
        return new BigDecimal(amount).movePointLeft(2).doubleValue();
    }

    /**
     * 单位分转元
     */
    public static String formatAmountToString(String amount) {
        return new BigDecimal(amount).movePointLeft(2).toString();
    }

    /**
     * String转Long，单位元转分
     */
    public static Long formatAmountToLong(String amount) {
        return new BigDecimal(amount).movePointRight(2).longValue();
    }

}

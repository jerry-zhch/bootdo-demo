package cn.ucmed.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhch
 * @since 2020/3/24 18:24
 */
@Slf4j
public class DateUtil {
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date StringToDate4(String date) {
        try {
            return sdf2.parse(date);
        } catch (ParseException var2) {
            log.debug("StringToDate4 error==>"+var2);
            return new Date();
        }
    }

}

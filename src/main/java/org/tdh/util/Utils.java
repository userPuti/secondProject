package org.tdh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Puti
 * @date 2022/7/15 15:28
 */
public class Utils {
    /**
     * 获取一个32位的uuid
     *
     * @return 32位的uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取一个日期字符串
     *
     * @param pattern 格式化
     * @return 时间字符串
     */
    public static String getFormattedDate(String pattern) {
        SimpleDateFormat smf = new SimpleDateFormat(pattern);
        return smf.format(new Date());
    }
}

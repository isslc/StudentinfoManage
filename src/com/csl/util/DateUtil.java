package com.csl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 陈思龙
 * @date 2018/12/6 9:34
 * 将日期转换成字符串
 */
public class DateUtil {
    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);//解析日期的类
        if (date != null) {

            result = sdf.format(date);
        }
        return result;
    }


    public static Date formatString(String str, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }
}

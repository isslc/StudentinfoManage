package com.csl.util;

/**
 * @author 陈思龙
 * @date 2018/12/5 11:28
 * <p>
 * 判断字符串是否相等是否为空
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        if ("".equals(str) || str == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        if (!"".equals(str) && str != null) {
            return true;
        } else {
            return false;
        }
    }
}
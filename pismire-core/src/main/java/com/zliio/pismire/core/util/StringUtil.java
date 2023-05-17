package com.zliio.pismire.core.util;

/**
 * 字符串工具类
 *
 * @author ZliIO
 */
public class StringUtil {

    public static final String EMPTY = "";

    public static boolean isEmpty(String str) {
        return null != str && str.trim().length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(String str) {
        return null == str;
    }

    public static boolean isNotNull(String str) {
        return null != str;
    }
}

package com.zliio.pismire.core.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 对象工具类，包括判空、克隆、序列化等操作
 *
 * @author ZliIO
 */
public class ObjectUtil {

    /**
     * 计算对象长度，如果是字符串调用其length函数，集合类调用其size函数，数组调用其length属性，其他可遍历对象遍历计算长度<br>
     * 支持的类型包括：
     * <ul>
     * <li>CharSequence</li>
     * <li>Map</li>
     * <li>Iterator</li>
     * <li>Enumeration</li>
     * <li>Array</li>
     * </ul>
     *
     * @param obj 被计算长度的对象
     * @return 长度
     */
    public static int length(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).size();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).size();
        }

        int count;
        if (obj instanceof Iterator) {
            final Iterator<?> iter = (Iterator<?>) obj;
            count = 0;
            while (iter.hasNext()) {
                count++;
                iter.next();
            }
            return count;
        }
        if (obj instanceof Enumeration) {
            final Enumeration<?> enumeration = (Enumeration<?>) obj;
            count = 0;
            while (enumeration.hasMoreElements()) {
                count++;
                enumeration.nextElement();
            }
            return count;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }
        return -1;
    }

    /**
     * 检查对象是否为null<br>
     * 判断标准为：
     *
     * <pre>
     * 1. == null
     * 2. equals(null)
     * </pre>
     *
     * @param obj 对象
     * @return 是否为null
     */
    public static boolean isNull(Object obj) {
        //noinspection ConstantConditions
        return null == obj || obj.equals(null);
    }

    /**
     * 检查对象是否不为null
     * <pre>
     * 1. != null
     * 2. not equals(null)
     * </pre>
     *
     * @param obj 对象
     * @return 是否为非null
     */
    public static boolean isNotNull(Object obj) {
        //noinspection ConstantConditions
        return null != obj && !obj.equals(null);
    }

    /**
     * 如果给定对象为{@code null}返回默认值
     *
     * <pre>
     * ObjectUtil.defaultIfNull(null, null)      = null
     * ObjectUtil.defaultIfNull(null, "")        = ""
     * ObjectUtil.defaultIfNull(null, "zz")      = "zz"
     * ObjectUtil.defaultIfNull("abc", *)        = "abc"
     * ObjectUtil.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
     * </pre>
     *
     * @param <T>          对象类型
     * @param object       被检查对象，可能为{@code null}
     * @param defaultValue 被检查对象为{@code null}返回的默认值，可以为{@code null}
     * @return 被检查对象为{@code null}返回默认值，否则返回原值
     *
     */
    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return isNull(object) ? defaultValue : object;
    }

    /**
     * 如果被检查对象为 {@code null}， 返回默认值（由 defaultValueSupplier 提供）；否则直接返回
     *
     * @param source               被检查对象
     * @param defaultValueSupplier 默认值提供者
     * @param <T>                  对象类型
     * @return 被检查对象为{@code null}返回默认值，否则返回自定义handle处理后的返回值
     * @throws NullPointerException {@code defaultValueSupplier == null} 时，抛出
     
     */
    public static <T> T defaultIfNull(T source, Supplier<? extends T> defaultValueSupplier) {
        if (isNull(source)) {
            return defaultValueSupplier.get();
        }
        return source;
    }

    /**
     * 如果被检查对象为 {@code null}， 返回默认值（由 defaultValueSupplier 提供）；否则直接返回
     *
     * @param source               被检查对象
     * @param defaultValueSupplier 默认值提供者
     * @param <T>                  对象类型
     * @return 被检查对象为{@code null}返回默认值，否则返回自定义handle处理后的返回值
     * @throws NullPointerException {@code defaultValueSupplier == null} 时，抛出
     
     */
    public static <T> T defaultIfNull(T source, Function<T, ? extends T> defaultValueSupplier) {
        if (isNull(source)) {
            return defaultValueSupplier.apply(null);
        }
        return source;
    }

    /**
     * 如果给定对象为{@code null} 返回默认值, 如果不为null 返回自定义handle处理后的返回值
     *
     * @param source       Object 类型对象
     * @param handle       非空时自定义的处理方法
     * @param defaultValue 默认为空的返回值
     * @param <T>          被检查对象为{@code null}返回默认值，否则返回自定义handle处理后的返回值
     * @return 处理后的返回值
     *
     * @deprecated 当str为{@code null}时，handle使用了str相关的方法引用会导致空指针问题
     */
    @Deprecated
    public static <T> T defaultIfNull(Object source, Supplier<? extends T> handle, final T defaultValue) {
        if (isNotNull(source)) {
            return handle.get();
        }
        return defaultValue;
    }

    /**
     * 如果给定对象为{@code null} 返回默认值, 如果不为null 返回自定义handle处理后的返回值
     *
     * @param <T>          被检查对象为{@code null}返回默认值，否则返回自定义handle处理后的返回值
     * @param <R>          被检查的对象类型
     * @param source       Object 类型对象
     * @param handle       非空时自定义的处理方法
     * @param defaultValue 默认为空的返回值
     * @return 处理后的返回值
     *
     */
    public static <T, R> T defaultIfNull(R source, Function<R, ? extends T> handle, final T defaultValue) {
        if (isNotNull(source)) {
            return handle.apply(source);
        }
        return defaultValue;
    }
}

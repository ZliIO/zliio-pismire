package com.zliio.pismire.core.date;

import java.util.Calendar;

/**
 * 月份枚举<br>
 * 与Calendar中的月份int值对应
 *
 * @author ZliIO
 * @see Calendar#JANUARY
 * @see Calendar#FEBRUARY
 * @see Calendar#MARCH
 * @see Calendar#APRIL
 * @see Calendar#MAY
 * @see Calendar#JUNE
 * @see Calendar#JULY
 * @see Calendar#AUGUST
 * @see Calendar#SEPTEMBER
 * @see Calendar#OCTOBER
 * @see Calendar#NOVEMBER
 * @see Calendar#DECEMBER
 * @see Calendar#UNDECIMBER
 */
public enum Month {
	/**
	 * 一月
	 */
	JANUARY(Calendar.JANUARY),
	/**
	 * 二月
	 */
	FEBRUARY(Calendar.FEBRUARY),
	/**
	 * 三月
	 */
	MARCH(Calendar.MARCH),
	/**
	 * 四月
	 */
	APRIL(Calendar.APRIL),
	/**
	 * 五月
	 */
	MAY(Calendar.MAY),
	/**
	 * 六月
	 */
	JUNE(Calendar.JUNE),
	/**
	 * 七月
	 */
	JULY(Calendar.JULY),
	/**
	 * 八月
	 */
	AUGUST(Calendar.AUGUST),
	/**
	 * 九月
	 */
	SEPTEMBER(Calendar.SEPTEMBER),
	/**
	 * 十月
	 */
	OCTOBER(Calendar.OCTOBER),
	/**
	 * 十一月
	 */
	NOVEMBER(Calendar.NOVEMBER),
	/**
	 * 十二月
	 */
	DECEMBER(Calendar.DECEMBER),
	/**
	 * 十三月，仅用于农历
	 */
	UNDECIMBER(Calendar.UNDECIMBER);

	private static final Month[] ENUMS = Month.values();

	/**
	 * 对应值，见{@link Calendar}
	 */
	private final int value;

	/**
	 * 构造
	 *
	 * @param value 对应值，见{@link Calendar}
	 */
	Month(int value) {
		this.value = value;
	}

	/**
	 * 获取{@link Calendar}中的对应值<br>
	 * 此值从0开始，即0表示一月
	 *
	 * @return {@link Calendar}中的对应月份值，从0开始计数
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * 获取月份值，此值与{@link java.time.Month}对应<br>
	 * 此值从1开始，即1表示一月
	 *
	 * @return 月份值，对应{@link java.time.Month}，从1开始计数
	 
	 */
	public int getValueBaseOne() {
		return getValue() + 1;
	}

	/**
	 * 获取此月份最后一天的值，不支持的月份（例如UNDECIMBER）返回-1
	 *
	 * @param isLeapYear 是否闰年
	 * @return 此月份最后一天的值
	 */
	public int getLastDay(boolean isLeapYear) {
		switch (this) {
			case FEBRUARY:
				return isLeapYear ? 29 : 28;
			case APRIL:
			case JUNE:
			case SEPTEMBER:
			case NOVEMBER:
				return 30;
			default:
				return 31;
		}
	}

	/**
	 * 将 {@link Calendar}月份相关值转换为Month枚举对象<br>
	 * 未找到返回{@code null}
	 *
	 * @param calendarMonthIntValue Calendar中关于Month的int值，从0开始
	 * @return Month
	 * @see Calendar#JANUARY
	 * @see Calendar#FEBRUARY
	 * @see Calendar#MARCH
	 * @see Calendar#APRIL
	 * @see Calendar#MAY
	 * @see Calendar#JUNE
	 * @see Calendar#JULY
	 * @see Calendar#AUGUST
	 * @see Calendar#SEPTEMBER
	 * @see Calendar#OCTOBER
	 * @see Calendar#NOVEMBER
	 * @see Calendar#DECEMBER
	 * @see Calendar#UNDECIMBER
	 */
	public static Month of(int calendarMonthIntValue) {
		if (calendarMonthIntValue >= ENUMS.length || calendarMonthIntValue < 0) {
			return null;
		}
		return ENUMS[calendarMonthIntValue];
	}
	/**
	 * 获得指定月的最后一天
	 *
	 * @param month      月份，从0开始
	 * @param isLeapYear 是否为闰年，闰年只对二月有影响
	 * @return 最后一天，可能为28,29,30,31
	 *
	 */
	public static int getLastDay(int month, boolean isLeapYear) {
		final Month of = of(month);
		return of.getLastDay(isLeapYear);
	}
}

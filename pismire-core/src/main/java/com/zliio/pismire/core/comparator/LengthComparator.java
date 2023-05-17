package com.zliio.pismire.core.comparator;

import com.zliio.pismire.core.util.CompareUtil;

import java.util.Comparator;

/**
 * 字符串长度比较器，短在前
 *
 * @author ZliIO
 *
 */
public class LengthComparator implements Comparator<CharSequence> {
	/**
	 * 单例的字符串长度比较器，短在前
	 */
	public static final LengthComparator INSTANCE = new LengthComparator();

	@Override
	public int compare(CharSequence o1, CharSequence o2) {
		int result = Integer.compare(o1.length(), o2.length());
		if (0 == result) {
			result = CompareUtil.compare(o1.toString(), o2.toString());
		}
		return result;
	}
}

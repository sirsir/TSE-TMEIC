package jp.co.tmeic.mespd.utils;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.beans.util.Beans;

/** ArrayUtil */
public final class ArrayUtil {

	/** コンストラクタ */
	private ArrayUtil() {
	}

	/**
	 * リストをコピーします。
	 *
	 * @param destClass
	 * @param src
	 * @return
	 */
	public static <T> List<T> copy(Class<T> destClass, List<?> src) {

		List<T> list = new ArrayList<>();

		for (Object object : src) {
			list.add(Beans.createAndCopy(destClass, object)
					.timestampConverter("dd/MM/yyyy HH:mm:ss")
					.dateConverter("dd/MM/yyyy")
					.execute());
		}

		return list;
	}

	/**
	 * リストをコピーします。
	 *
	 * @param destClass
	 * @param src
	 * @return
	 */
	public static <T> List<T> copy(Class<T> destClass, List<?> src, String... colName) {

		List<T> list = new ArrayList<>();

		for (Object object : src) {
			list.add(Beans.createAndCopy(destClass, object)
					.timestampConverter("dd/MM/yyyy HH:mm:ss")
					.execute());
		}

		return list;
	}
}

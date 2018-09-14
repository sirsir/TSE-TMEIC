package jp.co.tmeic.mespd.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.TypeReference;

import org.apache.commons.lang3.StringUtils;

/** JSONクラス */
public final class JSONUtil {

	/** コンストラクタ */
	private JSONUtil() {
	}

	/**
	 * オブジェクトをJSONに変換する。
	 *
	 * @param src
	 *            JSON変換するオブジェクト・配列
	 * @return JSON
	 */
	public static String encode(Object src) {

		if (src == null) {
			return "[]";
		}

		JSON json = new JSON();

		json.setDateFormat("dd/MM/yyyy HH:mm:ss");

		return json.format(src);
	}

	/**
	 * JSONをオブジェクトに変換する。
	 *
	 * @param src
	 *            オブジェクト・配列変換するJSON
	 * @return JSON
	 */
	public static <T> List<T> decode(String src, Class<? extends T[]> cls) {

		List<T> list = new ArrayList<>();

		T[] arrays = JSON.decode(src, cls);

		for (T array : arrays) {
			list.add(array);
		}

		return list;
	}

	/**
	 * JSONをMapに変換する。
	 *
	 * @param src
	 *            Map変換するJSON
	 * @return int型のキーを持つMap
	 */
	public static Map<Integer, Object> decodeToMapInt(String src) {

		return JSON
				.decode(src, new TypeReference<HashMap<Integer, Object>>() {
				});

	}

	/**
	 * JSONをMapに変換する。
	 *
	 * @param src
	 *            Map変換するJSON
	 * @return string型のキーを持つMap
	 */
	public static Map<String, Object> decodeToMapString(String src) {

		return JSON
				.decode(src, new TypeReference<HashMap<String, Object>>() {
				});
	}

	/**
	 * Unicode文字列に変換する。
	 *
	 * @param original
	 * @return
	 */
	public static String convertToUnicode(String original) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < original.length(); i++) {
			if (!StringUtils.isAsciiPrintable(String.valueOf(original.charAt(i)))) {
				sb.append(String.format("\\u%04X", Character.codePointAt(original, i)));
			} else {
				sb.append(original.charAt(i));
			}
		}

		return sb.toString();
	}
}

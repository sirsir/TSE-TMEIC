package jp.co.tmeic.mespd.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/** Locale */
public final class MapUtil {

	/** コンストラクタ */
	private MapUtil() {
	}

	public static Map<String, String> putMap(String... values) {

		Map<String, String> map = new LinkedHashMap<>();

		for (String value : values) {
			if (value != null) {
				map.put(value, value);
			}
		}

		return map;
	}
}

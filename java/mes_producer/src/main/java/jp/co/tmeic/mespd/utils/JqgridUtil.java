package jp.co.tmeic.mespd.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

import org.apache.commons.lang3.StringUtils;

/** Jqgrid */
public final class JqgridUtil {

	/** コンストラクタ */
	private JqgridUtil() {
	}

	public static <T extends JqgridDto> T init(T jqgridDto) {

		jqgridDto.crud = "R";
		jqgridDto.id = String.valueOf(0);

		return jqgridDto;
	}

	public static <T extends JqgridDto> List<T> init(List<T> jqgridDtos) {

		int i = 0;

		for (T jqgridDto : jqgridDtos) {
			jqgridDto.crud = "R";
			jqgridDto.id = String.valueOf(i++);
		}

		return jqgridDtos;
	}

	public static List<Map<String, Object>> initMap(List<Map<String, Object>> jqgridDtos) {

		int i = 0;

		for (Map<String, Object> jqgridDto : jqgridDtos) {
			jqgridDto.put("crud", "R");
			jqgridDto.put("id", String.valueOf(i++));
		}

		return jqgridDtos;
	}

	public static String toSelect(Map<String, String> map) {

		// HTML特殊文字をエスケープすると区切り文字と被るため変更する。
		String separator = ":::";
		String delimiter = "|||";

		List<String> list = new ArrayList<>();

		list.add(String.format("%s", separator));

		for (String key : map.keySet()) {
			list.add(String.format("%s%s%s", key, separator, map.get(key)));
		}

		return StringUtils.join(list, delimiter);
	}
}

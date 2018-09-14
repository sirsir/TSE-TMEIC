package jp.co.tmeic.mespd.constant;

import java.util.Locale;

import org.seasar.struts.util.MessageResourcesUtil;

/** 進捗状況シリーズタイプ */
public final class ProgressSeriesType {

	/** コンストラクタ */
	private ProgressSeriesType() {
	}

	/** 生産中 */
	public static final int PLAN = 0;

	/** 中断 */
	public static final int RESULT = 1;

	/** 中止 */
	public static final int RESULT_TIME = 2;

	public static String getName(Locale locale, int type) {

		String[] name = {
				MessageResourcesUtil.getMessage(locale, "plan"),
				MessageResourcesUtil.getMessage(locale, "result"),
				MessageResourcesUtil.getMessage(locale, "result.time")
		};

		return name[type];
	}

	public static String getColor(int type) {

		String[] color = {
				"blue",
				"red",
				"orange"
		};

		return color[type];
	}
}

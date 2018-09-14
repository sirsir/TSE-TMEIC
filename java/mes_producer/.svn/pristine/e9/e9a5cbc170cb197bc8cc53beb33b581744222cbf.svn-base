package jp.co.tmeic.mespd.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.seasar.struts.util.MessageResourcesUtil;

/** 工程内製品実績 状態 */
public final class ProcessProductStatus {

	/** コンストラクタ */
	private ProcessProductStatus() {
	}

	/** 未設定 */
	public static final int NONE = 0;

	/** 開始 */
	public static final int START = 1;

	/** 終了 */
	public static final int COMPLETE = 2;

	/** ステータス名取得 */
	public static String getStatus(Locale locale) {

		List<String> list = new ArrayList<>();

		list.add(String.format("%s:%s", "", MessageResourcesUtil.getMessage(locale, "not.set")));
		list.add(String.format("%s:%s", "null", MessageResourcesUtil.getMessage(locale, "not.set")));

		list.add(String.format("%s:%s", NONE, MessageResourcesUtil.getMessage(locale, "not.set")));
		list.add(String.format("%s:%s", START, MessageResourcesUtil.getMessage(locale, "working")));
		list.add(String.format("%s:%s", COMPLETE, MessageResourcesUtil.getMessage(locale, "complete")));

		return StringUtils.join(list, ";");
	}
}

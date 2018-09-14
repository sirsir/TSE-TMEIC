package jp.co.tmeic.mespd.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.seasar.struts.util.MessageResourcesUtil;

/** 工程実績 状態 */
public final class ProcessStatus {

	/** コンストラクタ */
	private ProcessStatus() {
	}

	/** 未設定 */
	public static final int NONE = 0;

	/** 着工 */
	public static final int START = 1;

	/** 完了 */
	public static final int COMPLETE = 2;

	/** ステータス名取得 */
	public static String getStatus(Locale locale) {

		List<String> list = new ArrayList<>();

		list.add(String.format("%s:%s", "", MessageResourcesUtil.getMessage(locale, "not.set")));
		list.add(String.format("%s:%s", "null", MessageResourcesUtil.getMessage(locale, "not.set")));

		list.add(String.format("%s:%s", NONE, MessageResourcesUtil.getMessage(locale, "not.set")));
		list.add(String.format("%s:%s", START,  MessageResourcesUtil.getMessage(locale, "process.status.start")));
		list.add(String.format("%s:%s", COMPLETE,  MessageResourcesUtil.getMessage(locale, "process.status.complete")));

		return StringUtils.join(list, ";");
	}

}

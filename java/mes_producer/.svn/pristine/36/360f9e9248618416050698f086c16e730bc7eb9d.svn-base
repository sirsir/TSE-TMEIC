package jp.co.tmeic.mespd.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.seasar.struts.util.MessageResourcesUtil;

/** 製造実績 状態 */
public final class ProductStatus {

	/** コンストラクタ */
	private ProductStatus() {
	}

	/** 未設定 */
	public static final int NONE = 0;

	/** 生産中 */
	public static final int IN_PRODUCTION = 1;

	/** 中断 */
	public static final int SUSPEND = 2;

	/** 中止 */
	public static final int STOP = 3;

	/** 終了 */
	public static final int COMPLETE = 4;

	/** ステータス名取得 */
	public static String getStatus(Locale locale) {

		List<String> list = new ArrayList<>();

		list.add(String.format("%s:%s", "", MessageResourcesUtil.getMessage(locale, "not.set")));
		list.add(String.format("%s:%s", "null", MessageResourcesUtil.getMessage(locale, "not.set")));

		list.add(String.format("%s:%s", NONE, MessageResourcesUtil.getMessage(locale, "not.set")));
		list.add(String.format("%s:%s", IN_PRODUCTION, MessageResourcesUtil.getMessage(locale, "in.production")));
		list.add(String.format("%s:%s", SUSPEND, MessageResourcesUtil.getMessage(locale, "break")));
		list.add(String.format("%s:%s", STOP, MessageResourcesUtil.getMessage(locale, "canceled")));
		list.add(String.format("%s:%s", COMPLETE, MessageResourcesUtil.getMessage(locale, "finished")));

		return StringUtils.join(list, ";");
	}

}

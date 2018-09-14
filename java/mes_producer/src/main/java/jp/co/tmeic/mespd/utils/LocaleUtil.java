package jp.co.tmeic.mespd.utils;

import java.util.Locale;

/** Locale */
public final class LocaleUtil {

	/** コンストラクタ */
	private LocaleUtil() {
	}

	public static Locale getLocale() {

		Locale locale = new Locale("ja");

		return locale;
	}
}

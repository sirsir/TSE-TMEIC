package jp.co.tmeic.mespd.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/** TimestampUtil */
public final class TimestampUtil {

	/** コンストラクタ */
	private TimestampUtil() {
	}

	public static Timestamp now() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp toTimestamp(String timestamp) throws ParseException {
		return new Timestamp(DateUtil.toDate(timestamp).getTime());
	}

	public static Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static Timestamp toTimestamp(String timestamp, String pattern) throws ParseException {
		return new Timestamp(DateUtil.toDate(timestamp, pattern).getTime());
	}

	public static Timestamp toTimestamp(String timestamp, String... pattern) throws ParseException {
		return new Timestamp(DateUtil.toDate(timestamp, pattern).getTime());
	}
}

package jp.co.tmeic.mespd.utils;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateUtils;

/** SqlDateUtil */
public final class SqlDateUtil {

	/** コンストラクタ */
	private SqlDateUtil() {
	}

	public static Date now() {

		Calendar cal = Calendar.getInstance();

		cal.setTime(new java.util.Date());

		cal = DateUtils.truncate(cal, Calendar.HOUR_OF_DAY);

		return new java.sql.Date(cal.getTimeInMillis());
	}

	public static Date toDate(String date) throws ParseException {
		return toDate(DateUtil.toDate(date));
	}

	public static Date toDate(java.util.Date date) throws ParseException {

		Calendar cal = Calendar.getInstance();

		cal.setTime(new java.sql.Date(date.getTime()));
		cal = DateUtils.truncate(cal, Calendar.HOUR_OF_DAY);

		return new java.sql.Date(cal.getTimeInMillis());
	}
}

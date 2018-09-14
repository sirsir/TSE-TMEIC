package jp.co.tmeic.mespd.utils;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/** TimestampUtil */
public final class TimeUtil {

	/** コンストラクタ */
	private TimeUtil() {
	}

	private static String[] parsePatterns = {
			"HH:mm", "HHmm", "HH:mm:ss", "HHmmss"
	};

	public static int getHours(Time time) {

		if (time == null) {
			return 0;
		}

		return Integer.parseInt(DateFormatUtils.format(time.getTime(), "HH"));
	}

	public static int getMinutes(Time time) {

		if (time == null) {
			return 0;
		}

		return Integer.parseInt(DateFormatUtils.format(time.getTime(), "mm"));
	}

	public static int getSeconds(Time time) {

		if (time == null) {
			return 0;
		}

		return Integer.parseInt(DateFormatUtils.format(time.getTime(), "ss"));
	}

	public static boolean isTime(String value)  {

		try {
			toTime(value);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	public static Time toTime(String value) throws ParseException {
		return new Time(DateUtils.parseDateStrictly(value, parsePatterns).getTime());
	}

	public static Time of(int hour, int minute) {

		Calendar cal = minTime();

		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);

		return new Time(cal.getTimeInMillis());
	}

	public static Time min() {
		return of(0, 0, 0);
	}

	public static Time max() {
		return of(23, 59, 59);
	}

	public static Time of(int hour, int minute, int second) {

		Calendar cal = minTime();

		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);

		return new Time(cal.getTimeInMillis());
	}

	private static Calendar minTime() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1970);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);

		return DateUtils.truncate(cal, Calendar.HOUR_OF_DAY);
	}

	/**
	 * time1 == time2 の時刻比較を行います。
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean eq(Time time1, Time time2) {
		return time1.compareTo(time2) == 0;
	}

	/**
	 * time1 != time2 の時刻比較を行います。
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean ne(Time time1, Time time2) {
		return time1.compareTo(time2) != 0;
	}

	/**
	 * time1 > time2 の時刻比較を行います。
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean gt(Time time1, Time time2) {
		return time1.compareTo(time2) > 0;
	}

	/**
	 * time1 < time2 の時刻比較を行います。
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean lt(Time time1, Time time2) {
		return time1.compareTo(time2) < 0;
	}

	/**
	 * time1 >= time2 の時刻比較を行います。
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean ge(Time time1, Time time2) {
		return time1.compareTo(time2) >= 0;
	}

	/**
	 * time1 <= time2 の時刻比較を行います。
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean le(Time time1, Time time2) {
		return time1.compareTo(time2) <= 0;
	}
}

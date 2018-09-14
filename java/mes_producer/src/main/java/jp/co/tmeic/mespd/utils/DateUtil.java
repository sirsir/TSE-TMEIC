package jp.co.tmeic.mespd.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.seasar.framework.exception.ParseRuntimeException;

/** DateUtil */
public final class DateUtil {

	/** コンストラクタ */
	private DateUtil() {
	}

	private static String[] parsePatterns = {
			"dd/MM/yyyy", "yyyy-MM-dd", "dd/MM/yyyy HH:mm:ss"
	};

	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	public static String now(String format) {
		return DateFormatUtils.format(now(), format);
	}

	public static Date toDate(String date) {

		if (date == null) {
			return null;
		}

		if (date.isEmpty()) {
			return null;
		}

		try {
			return DateUtils.parseDate(date, parsePatterns);
		} catch (ParseException ex) {
			throw new ParseRuntimeException(ex);
		}
	}

	public static Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public static Date toDate(String date, String... parsePatterns) throws ParseException {
		return DateUtils.parseDate(date, parsePatterns);
	}

	public static boolean isDate(String date) {

		try {
			DateUtils.parseDate(date, parsePatterns);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	/**
	 * dateにtimeの時分秒を設定します。
	 *
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date setTime(Date date, Time time) {

		Date result = new Date(date.getTime());

		result = DateUtils.setHours(result, TimeUtil.getHours(time));
		result = DateUtils.setMinutes(result, TimeUtil.getMinutes(time));
		result = DateUtils.setSeconds(result, TimeUtil.getSeconds(time));

		return result;
	}

	/**
	 * dateに時分を設定します。
	 *
	 * @param date
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date setTime(Date date, int hour, int minute) {
		return setTime(date, TimeUtil.of(hour, minute));
	}

	/**
	 * dateに時分秒を設定します。
	 *
	 * @param date
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date setTime(Date date, int hour, int minute, int second) {
		return setTime(date, TimeUtil.of(hour, minute, second));
	}

	/**
	 * Dateを生成します。
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date of(int year, int month, int day) {

		Date date = new Date();

		date = DateUtils.setYears(date, year);
		date = DateUtils.setMonths(date, month - 1);
		date = DateUtils.setDays(date, day);
		date = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);

		return date;
	}

	/**
	 * 指定日の月初日付を取得します。
	 *
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {

		Date result = new Date(date.getTime());

		result = DateUtils.truncate(result, Calendar.DATE);

		return result;
	}

	/**
	 * 指定日の月末日付を取得します。
	 *
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {

		Date result = new Date(date.getTime());

		result = DateUtils.truncate(result, Calendar.DATE);

		Calendar cal = Calendar.getInstance();
		cal.setTime(result);

		result = DateUtils.setDays(result, cal.getActualMaximum(Calendar.DATE));

		return result;
	}

	/**
	 * 指定日の日の値を取得します。
	 *
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DATE);
	}

	/**
	 * date1 == date2 の日時比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean eq(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * date1 != date2 の日時比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean ne(Date date1, Date date2) {
		return date1.compareTo(date2) != 0;
	}

	/**
	 * date1 > date2 の日時比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean gt(Date date1, Date date2) {
		return date1.compareTo(date2) > 0;
	}

	/**
	 * date1 < date2 の日時比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean lt(Date date1, Date date2) {
		return date1.compareTo(date2) < 0;
	}

	/**
	 * date1 >= date2 の日時比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean ge(Date date1, Date date2) {
		return date1.compareTo(date2) >= 0;
	}

	/**
	 * date1 <= date2 の日時比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean le(Date date1, Date date2) {
		return date1.compareTo(date2) <= 0;
	}

	/**
	 * date1 == date2 の日付比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean eqDate(Date date1, Date date2) {
		return truncatedCompareToDate(date1, date2) == 0;
	}

	/**
	 * date1 != date2 の日付比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean neDate(Date date1, Date date2) {
		return truncatedCompareToDate(date1, date2) != 0;
	}

	/**
	 * date1 > date2 の日付比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean gtDate(Date date1, Date date2) {
		return truncatedCompareToDate(date1, date2) > 0;
	}

	/**
	 * date1 < date2 の日付比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean ltDate(Date date1, Date date2) {
		return truncatedCompareToDate(date1, date2) < 0;
	}

	/**
	 * date1 >= date2 の日付比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean geDate(Date date1, Date date2) {
		return truncatedCompareToDate(date1, date2) >= 0;
	}

	/**
	 * date1 <= date2 の日付比較を行います。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean leDate(Date date1, Date date2) {
		return truncatedCompareToDate(date1, date2) <= 0;
	}

	private static int truncatedCompareToDate(Date date1, Date date2) {
		return DateUtils.truncatedCompareTo(date1, date2, Calendar.DATE);
	}

	/**
	 * 開始日付から終了日付までの日数を取得します。
	 *
	 * @param startDate
	 * @param endDate
	 * @return 開始日付から終了日付までの日数
	 */
	public static long days(Date startDate, Date endDate) {

		ZonedDateTime start = startDate.toInstant().atZone(ZoneId.systemDefault());
		ZonedDateTime end = endDate.toInstant().atZone(ZoneId.systemDefault());

		Duration duration = Duration.between(start, end);

		return duration.toDays();
	}

}

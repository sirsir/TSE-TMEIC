package jp.co.tmeic.mespd.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import jp.co.tmeic.mespd.entity.DProcessPlan;
import jp.co.tmeic.mespd.entity.MWorkCalendar;
import jp.co.tmeic.mespd.entity.MWorkCalendarBreak;
import jp.co.tmeic.mespd.utils.SqlDateUtil;
import jp.co.tmeic.mespd.utils.TimestampUtil;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.util.TimeConversionUtil;

/**
 * {@link ScheduleCalculationService}のテストクラスです。
 *
 */
public class ScheduleCalculationServiceTest extends S2TestCase {

	private ScheduleCalculationService scheduclCalculation;

	private int planQty;
	private DProcessPlan dProcessPlan1;
	private DProcessPlan dProcessPlan2;
	private DProcessPlan dProcessPlan3;

	private MWorkCalendar mWorkCalendar;
	private MWorkCalendar mWorkCalendarNextDay;

	private MWorkCalendarBreak mBreak1;
	private MWorkCalendarBreak mBreak2;
	private MWorkCalendarBreak mBreak3;
	private MWorkCalendarBreak mBreak4;

	/**
	 * 事前処理をします。
	 *
	 * @throws Exception
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("app.dicon");

		planQty = 100;

		dProcessPlan1 = new DProcessPlan();
		dProcessPlan2 = new DProcessPlan();
		dProcessPlan3 = new DProcessPlan();

		mWorkCalendar = new MWorkCalendar();
		mWorkCalendar.holiday = false;
		mWorkCalendar.workDay = SqlDateUtil.toDate("2015/05/01");

		mWorkCalendarNextDay = new MWorkCalendar();
		mWorkCalendarNextDay.holiday = false;
		mWorkCalendarNextDay.workDay = SqlDateUtil.toDate("2015/05/02");

		mBreak1 = new MWorkCalendarBreak();
		mBreak2 = new MWorkCalendarBreak();
		mBreak3 = new MWorkCalendarBreak();
		mBreak4 = new MWorkCalendarBreak();
	}

	/**
	 * 工程数１のスケジュール計算(日跨ぎなし）をテストします。<BR>
	 *
	 * 操業カレンダデータなし
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfOneProcess() throws ParseException {

		// パターン①
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 10:00:00");

		dProcessPlan1.processTime = 300;

		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 10:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 10:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 18:20:00"), dProcessPlan1.planEndDate);
	}

	/**
	 * 工程数１のスケジュール計算(日跨ぎなし）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・休憩時刻設定あり（複数）<BR>
	 * （スケジュール時間内の休憩時刻ありと時刻外）
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfOneProcessForBreaks() throws ParseException {

		// パターン②
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 10:00:00");

		dProcessPlan1.processTime = 300;

		mBreak1.breakStartTime = TimeConversionUtil.toTime("12:00", "HH:mm");
		mBreak1.breakEndTime = TimeConversionUtil.toTime("13:00", "HH:mm");

		mBreak2.breakStartTime = TimeConversionUtil.toTime("23:15", "HH:mm");
		mBreak2.breakEndTime = TimeConversionUtil.toTime("23:30", "HH:mm");

		List<MWorkCalendarBreak> mBreaks = Arrays.asList(mBreak1, mBreak2);

		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 10:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 10:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 19:20:00"), dProcessPlan1.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎなし）をテストします。<BR>
	 *
	 * 操業カレンダデータなし
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcess() throws ParseException {

		// パターン③
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 120;
		dProcessPlan2.processTime = 240;
		dProcessPlan3.processTime = 360;

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, null, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 10:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:02:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:02:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 13:42:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:06:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:06:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 17:06:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎなし）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・休憩時刻設定あり（複数）<BR>
	 * （スケジュール時間内の休憩時刻ありと時刻外）<BR>
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessForBreaks() throws ParseException {

		// パターン④
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 120;
		dProcessPlan2.processTime = 240;
		dProcessPlan3.processTime = 360;

		mBreak1.breakStartTime = TimeConversionUtil.toTime("12:00", "HH:mm");
		mBreak1.breakEndTime = TimeConversionUtil.toTime("13:00", "HH:mm");

		mBreak2.breakStartTime = TimeConversionUtil.toTime("23:15", "HH:mm");
		mBreak2.breakEndTime = TimeConversionUtil.toTime("23:30", "HH:mm");

		List<MWorkCalendarBreak> mBreaks = Arrays.asList(mBreak1, mBreak2);

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 10:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:02:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:02:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 14:42:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:06:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:06:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 18:06:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数１のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータなし
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfOneProcessStepOverDay() throws ParseException {

		// パターン⑤
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 23:00:00");

		dProcessPlan1.processTime = 300;

		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 07:20:00"), dProcessPlan1.planEndDate);

		// 日またぎ(操業カレンダなし)
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 07:20:00"), dProcessPlan1.planEndDate);
	}

	/**
	 * 工程数１のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・翌日稼働開始時刻設定あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfOneProcessStepOverDayNextWorkStartTime() throws ParseException {

		// パターン⑥
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 23:00:00");

		dProcessPlan1.processTime = 300;

		// 翌日操業カレンダ
		mWorkCalendarNextDay.workStartTime = TimeConversionUtil.toTime("07:00", "HH:mm");

		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 07:20:00"), dProcessPlan1.planEndDate);

		// 日またぎ(翌日操業カレンダあり)
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 14:20:00"), dProcessPlan1.planEndDate);
	}

	/**
	 * 工程数１のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・当日稼働終了時刻設定あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfOneProcessStepOverDayWorkEndTime() throws ParseException {

		// パターン⑦
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 20:00:00");

		dProcessPlan1.processTime = 300;

		// 当日操業カレンダ
		mWorkCalendar.workEndTime = TimeConversionUtil.toTime("22:00", "HH:mm");

		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 20:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 20:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 06:20:00"), dProcessPlan1.planEndDate);

		// 日またぎ(操業カレンダなし)
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 20:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 06:20:00"), dProcessPlan1.planEndDate);
	}

	/**
	 * 工程数１のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・翌日稼働開始時刻設定あり<BR>
	 * ・当日稼働終了時刻設定あり<BR>
	 * ・休憩時刻設定あり（複数）
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfOneProcessStepOverDaySetWorkAndBreaks() throws ParseException {

		// パターン⑧
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 20:00:00");

		dProcessPlan1.processTime = 300;

		// 当日操業カレンダ
		mWorkCalendar.workDay = SqlDateUtil.toDate("2015/05/01");
		mWorkCalendar.workEndTime = TimeConversionUtil.toTime("22:00", "HH:mm");

		mBreak1.breakStartTime = TimeConversionUtil.toTime("12:00", "HH:mm");
		mBreak1.breakEndTime = TimeConversionUtil.toTime("13:00", "HH:mm");

		mBreak2.breakStartTime = TimeConversionUtil.toTime("21:15", "HH:mm");
		mBreak2.breakEndTime = TimeConversionUtil.toTime("21:30", "HH:mm");

		List<MWorkCalendarBreak> mBreaks = Arrays.asList(mBreak1, mBreak2);

		// 翌日操業カレンダ
		MWorkCalendar nextMWorkCalendar = new MWorkCalendar();
		nextMWorkCalendar.holiday = false;
		nextMWorkCalendar.workDay = SqlDateUtil.toDate("2015/05/02");
		nextMWorkCalendar.workStartTime = TimeConversionUtil.toTime("07:00", "HH:mm");

		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 20:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 20:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 06:35:00"), dProcessPlan1.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, nextMWorkCalendar, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 20:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 13:35:00"), dProcessPlan1.planEndDate);
	}

	/**
	 * 工程数１のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・翌日休日指定あり<BR>
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfOneProcessNextDayIsHoliday() throws ParseException {

		// パターン⑨
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 23:00:00");

		dProcessPlan1.processTime = 300;

		// 翌日操業カレンダ
		mWorkCalendarNextDay.holiday = true;

		MWorkCalendar mWorkCalendarTwoNextDay = new MWorkCalendar();
		mWorkCalendarTwoNextDay.workDay = SqlDateUtil.toDate("2015/05/03");
		mWorkCalendarTwoNextDay.holiday = false;

		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 07:20:00"), dProcessPlan1.planEndDate);

		// 日またぎ
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/03 07:20:00"), dProcessPlan1.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarTwoNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/03 07:20:00"), dProcessPlan1.planEndDate);
	}

	/**
	 * 工程数１のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * 翌日・翌々日休日指定あり<BR>
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfOneProcessNextDayAndTwoDaysLaterAreHoliday() throws ParseException {

		// パターン⑩
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 23:00:00");

		dProcessPlan1.processTime = 300;

		// 翌日操業カレンダ
		mWorkCalendarNextDay.holiday = true;

		// 翌日操業カレンダ
		MWorkCalendar mWorkCalendarTwoNextDay = new MWorkCalendar();
		mWorkCalendarTwoNextDay.workDay = SqlDateUtil.toDate("2015/05/03");
		mWorkCalendarTwoNextDay.holiday = true;

		// 翌日操業カレンダ
		MWorkCalendar mWorkCalendarThreeNextDay = new MWorkCalendar();
		mWorkCalendarThreeNextDay.workDay = SqlDateUtil.toDate("2015/05/04");
		mWorkCalendarThreeNextDay.holiday = false;

		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 07:20:00"), dProcessPlan1.planEndDate);

		// 日またぎ
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/03 07:20:00"), dProcessPlan1.planEndDate);

		// 日またぎ
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarTwoNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/04 07:20:00"), dProcessPlan1.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarThreeNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/04 07:20:00"), dProcessPlan1.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータなし
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessStepOverDay() throws ParseException {

		// パターン⑪
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 600;
		dProcessPlan3.processTime = 900;

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 15:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:45:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:15:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:15:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ <BR>
	 * ・翌日稼働開始時刻設定あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessStepOverDayNextWorkStartTime() throws ParseException {

		// パターン⑫
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 600;
		dProcessPlan3.processTime = 900;

		// 翌日操業カレンダ
		mWorkCalendarNextDay.workStartTime = TimeConversionUtil.toTime("08:00", "HH:mm");

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 15:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:45:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:15:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 16:15:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ <BR>
	 * ・当日稼働終了時刻設定あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessStepOverDayWorkEndTime() throws ParseException {

		// パターン⑬
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 600;
		dProcessPlan3.processTime = 900;

		// 当日操業カレンダ
		mWorkCalendar.workEndTime = TimeConversionUtil.toTime("22:00", "HH:mm");

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 15:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 01:45:00"), dProcessPlan2.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 01:45:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 10:15:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 10:15:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・翌日稼働開始時刻設定あり<BR>
	 * ・当日稼働終了時刻設定あり<BR>
	 * ・休憩時刻設定あり（複数）
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessStepOverDaySetWorkAndBreaks() throws ParseException {

		// パターン⑭
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 600;
		dProcessPlan3.processTime = 900;

		// 当日操業カレンダ
		mWorkCalendar.workEndTime = TimeConversionUtil.toTime("22:00", "HH:mm");

		mBreak1.breakStartTime = TimeConversionUtil.toTime("12:00", "HH:mm");
		mBreak1.breakEndTime = TimeConversionUtil.toTime("13:00", "HH:mm");

		mBreak2.breakStartTime = TimeConversionUtil.toTime("21:15", "HH:mm");
		mBreak2.breakEndTime = TimeConversionUtil.toTime("21:30", "HH:mm");

		List<MWorkCalendarBreak> mBreaks = Arrays.asList(mBreak1, mBreak2);

		// 翌日操業カレンダ
		mWorkCalendarNextDay.holiday = false;
		mWorkCalendarNextDay.workStartTime = TimeConversionUtil.toTime("08:00", "HH:mm");

		mBreak3.breakStartTime = TimeConversionUtil.toTime("09:00", "HH:mm");
		mBreak3.breakEndTime = TimeConversionUtil.toTime("09:30", "HH:mm");

		mBreak4.breakStartTime = TimeConversionUtil.toTime("23:15", "HH:mm");
		mBreak4.breakEndTime = TimeConversionUtil.toTime("23:30", "HH:mm");

		List<MWorkCalendarBreak> nextMBreaks = Arrays.asList(mBreak3, mBreak4);

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 16:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 03:00:00"), dProcessPlan2.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendarNextDay, nextMBreaks, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 11:30:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 11:30:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, nextMBreaks, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 20:00:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・翌日稼働開始時刻設定あり<BR>
	 * ・当日稼働終了時刻設定あり<BR>
	 * ・休憩時刻設定あり（複数）<BR>
	 * 工程開始時刻と休憩時刻の開始時刻が同じ
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessStartTimeEqualBreakStartTime() throws ParseException {

		// パターン⑮
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 600;
		dProcessPlan3.processTime = 900;

		// 当日操業カレンダ
		mWorkCalendar.workEndTime = TimeConversionUtil.toTime("22:00", "HH:mm");

		mBreak1.breakStartTime = TimeConversionUtil.toTime("07:05", "HH:mm");
		mBreak1.breakEndTime = TimeConversionUtil.toTime("07:15", "HH:mm");

		mBreak2.breakStartTime = TimeConversionUtil.toTime("12:00", "HH:mm");
		mBreak2.breakEndTime = TimeConversionUtil.toTime("13:00", "HH:mm");

		mBreak3.breakStartTime = TimeConversionUtil.toTime("21:15", "HH:mm");
		mBreak3.breakEndTime = TimeConversionUtil.toTime("21:30", "HH:mm");

		List<MWorkCalendarBreak> mBreaks = Arrays.asList(mBreak1, mBreak2, mBreak3);

		// 翌日操業カレンダ
		mWorkCalendarNextDay.holiday = false;
		mWorkCalendarNextDay.workStartTime = TimeConversionUtil.toTime("08:00", "HH:mm");

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 16:30:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 03:10:00"), dProcessPlan2.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 11:10:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:25:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:25:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 11:40:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:25:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 19:40:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・翌日稼働開始時刻設定あり<BR>
	 * ・当日稼働終了時刻設定あり<BR>
	 * ・工程②の開始時刻が翌日
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcesStartTimeOnTheNextDay() throws ParseException {

		// パターン⑯
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 21:55:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 360;
		dProcessPlan3.processTime = 480;

		// 当日操業カレンダ
		mWorkCalendar.workEndTime = TimeConversionUtil.toTime("22:00", "HH:mm");

		// 翌日操業カレンダ
		mWorkCalendarNextDay.holiday = false;
		mWorkCalendarNextDay.workStartTime = TimeConversionUtil.toTime("08:00", "HH:mm");

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 21:55:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 21:55:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:15:00"), dProcessPlan1.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 21:55:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 16:15:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/02 00:00:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendarNextDay, null, true));
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:00:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendarNextDay, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:00:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 18:00:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendarNextDay, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:06:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:06:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 21:26:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・翌日休日指定あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessNextDayIsHoliday() throws ParseException {

		// パターン⑰
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 600;
		dProcessPlan3.processTime = 900;

		// 翌日操業カレンダ
		mWorkCalendarNextDay.holiday = true;

		// 翌々日操業カレンダ
		MWorkCalendar mWorkCalendarTwoNextDay = new MWorkCalendar();
		mWorkCalendarTwoNextDay.workDay = SqlDateUtil.toDate("2015/05/03");
		mWorkCalendarTwoNextDay.holiday = false;

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 15:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:45:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:15:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/03 08:15:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarTwoNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/03 08:15:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・翌日・翌々日休日指定あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessNextDayAndTwoDaysLaterAreHoliday() throws ParseException {

		// パターン⑱
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 600;
		dProcessPlan3.processTime = 900;

		// 翌日操業カレンダ
		mWorkCalendarNextDay.holiday = true;

		// 翌日操業カレンダ
		MWorkCalendar mWorkCalendarTwoNextDay = new MWorkCalendar();
		mWorkCalendarTwoNextDay.holiday = true;
		mWorkCalendarTwoNextDay.workDay = SqlDateUtil.toDate("2015/05/03");

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 15:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:45:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, null, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 08:15:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/03 08:15:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarTwoNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/04 08:15:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		// 翌日操業カレンダ
		MWorkCalendar mWorkCalendarThreeNextDay = new MWorkCalendar();
		mWorkCalendarThreeNextDay.workDay = SqlDateUtil.toDate("2015/05/04");
		mWorkCalendarThreeNextDay.holiday = false;

		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarThreeNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/04 08:15:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・休憩時刻設定あり（複数）<BR>
	 * 重複している休憩時刻あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessIsDuplication() throws ParseException {

		// パターン⑲
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 07:00:00");

		dProcessPlan1.processTime = 300;
		dProcessPlan2.processTime = 600;
		dProcessPlan3.processTime = 900;

		mBreak1.breakStartTime = TimeConversionUtil.toTime("12:00", "HH:mm");
		mBreak1.breakEndTime = TimeConversionUtil.toTime("13:00", "HH:mm");

		mBreak2.breakStartTime = TimeConversionUtil.toTime("12:30", "HH:mm");
		mBreak2.breakEndTime = TimeConversionUtil.toTime("12:45", "HH:mm");

		mBreak3.breakStartTime = TimeConversionUtil.toTime("18:30", "HH:mm");
		mBreak3.breakEndTime = TimeConversionUtil.toTime("19:00", "HH:mm");

		mBreak4.breakStartTime = TimeConversionUtil.toTime("23:15", "HH:mm");
		mBreak4.breakEndTime = TimeConversionUtil.toTime("23:30", "HH:mm");

		List<MWorkCalendarBreak> mBreaks = Arrays.asList(mBreak1, mBreak2, mBreak3, mBreak4);

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 15:20:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 00:30:00"), dProcessPlan2.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:05:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 00:30:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(true, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 09:00:00"), dProcessPlan3.planEndDate);

		// 日またぎ
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendarNextDay, null, true));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 07:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/02 09:00:00"), dProcessPlan3.planEndDate);
	}


	/**
	 * 工程数３のスケジュール計算(日跨ぎあり）をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・休憩時刻設定あり<BR>
	 * 休憩時間内に工程２の開始予定時刻あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfThreeProcessForStartTimeWithinBreak() throws ParseException {

		// パターン⑳
		Timestamp startDateTime = TimestampUtil.toTimestamp("2015/05/01 10:15:00");

		planQty = 10;

		dProcessPlan1.processTime = 3600;
		dProcessPlan2.processTime = 3600;
		dProcessPlan3.processTime = 3600;

		mBreak1.breakStartTime = TimeConversionUtil.toTime("11:00", "HH:mm");
		mBreak1.breakEndTime = TimeConversionUtil.toTime("12:00", "HH:mm");

		List<MWorkCalendarBreak> mBreaks = Arrays.asList(mBreak1);

		// 工程１
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, null, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 10:15:00"), dProcessPlan1.planStartDate);
		assertNull(dProcessPlan1.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 10:15:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 21:15:00"), dProcessPlan1.planEndDate);

		// 工程２
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 12:15:00"), dProcessPlan2.planStartDate);
		assertNull(dProcessPlan2.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 12:15:00"), dProcessPlan2.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 22:15:00"), dProcessPlan2.planEndDate);

		// 工程３
		// 開始時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(startDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 13:15:00"), dProcessPlan3.planStartDate);
		assertNull(dProcessPlan3.planEndDate);

		// 終了時刻
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 13:15:00"), dProcessPlan3.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 23:15:00"), dProcessPlan3.planEndDate);
	}

	/**
	 * 工程数３のスケジュール計算(日跨ぎなし)をテストします。<BR>
	 *
	 * 操業カレンダデータ<BR>
	 * ・休憩時刻設定あり<BR>
	 * 休憩時間内に工程1の開始予定時刻あり
	 *
	 * @throws ParseException
	 */
	public void testCalculateScheduleOfTwoProductPlanForStartTimeWithinBreak() throws ParseException {

		// パターン21
		Timestamp plan1StartDateTime = TimestampUtil.toTimestamp("2015/05/01 13:00:00");
		Timestamp plan2StartDateTime = TimestampUtil.toTimestamp("2015/05/01 12:15:00");

		planQty = 10;

		dProcessPlan1.processTime = 60;
		dProcessPlan2.processTime = 240;
		dProcessPlan3.processTime = 360;

		DProcessPlan plan2ProcessPlan1 = new DProcessPlan();
		DProcessPlan plan2ProcessPlan2 = new DProcessPlan();
		DProcessPlan plan2ProcessPlan3 = new DProcessPlan();
		plan2ProcessPlan1.processTime = 60;
		plan2ProcessPlan2.processTime = 240;
		plan2ProcessPlan3.processTime = 360;

		mBreak1.breakStartTime = TimeConversionUtil.toTime("12:00", "HH:mm");
		mBreak1.breakEndTime = TimeConversionUtil.toTime("13:00", "HH:mm");

		List<MWorkCalendarBreak> mBreaks = Arrays.asList(mBreak1);

		// 製造計画NO1
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(plan1StartDateTime, planQty, null, dProcessPlan1, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan1, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(plan1StartDateTime, planQty, dProcessPlan1, dProcessPlan2, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan2, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(plan1StartDateTime, planQty, dProcessPlan2, dProcessPlan3, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, dProcessPlan3, mWorkCalendar, mBreaks, false));

		// 製造計画NO1
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(plan2StartDateTime, planQty, null, plan2ProcessPlan1, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, plan2ProcessPlan1, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(plan2StartDateTime, planQty, plan2ProcessPlan1, plan2ProcessPlan2, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, plan2ProcessPlan2, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfStartDate(plan2StartDateTime, planQty, plan2ProcessPlan2, plan2ProcessPlan3, mWorkCalendar, mBreaks, false));
		assertEquals(false, scheduclCalculation.calculateScheduleOfEndDate(planQty, plan2ProcessPlan3, mWorkCalendar, mBreaks, false));

		assertEquals(TimestampUtil.toTimestamp("2015/05/01 13:00:00"), dProcessPlan1.planStartDate);
		assertEquals(TimestampUtil.toTimestamp("2015/05/01 12:15:00"), plan2ProcessPlan1.planStartDate);

		assertEquals(dProcessPlan1.planEndDate, plan2ProcessPlan1.planEndDate);

		assertEquals(dProcessPlan2.planStartDate, plan2ProcessPlan2.planStartDate);
		assertEquals(dProcessPlan2.planEndDate, plan2ProcessPlan2.planEndDate);

		assertEquals(dProcessPlan3.planStartDate, plan2ProcessPlan3.planStartDate);
		assertEquals(dProcessPlan3.planEndDate, plan2ProcessPlan3.planEndDate);
	}
}
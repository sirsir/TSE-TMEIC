package jp.co.tmeic.mespd.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.co.tmeic.mespd.dto.DateRrangeDto;
import jp.co.tmeic.mespd.entity.DProcessPlan;
import jp.co.tmeic.mespd.entity.MWorkCalendar;
import jp.co.tmeic.mespd.entity.MWorkCalendarBreak;
import jp.co.tmeic.mespd.utils.DateUtil;
import jp.co.tmeic.mespd.utils.TimestampUtil;

import org.apache.commons.lang3.time.DateUtils;

public class ScheduleCalculationService {

	/**
	 * 開始時刻のスケジュール計算し、工程に設定します。
	 *
	 * @param planStartDate
	 *            製造計画開始予定日時
	 * @param planQty
	 *            製造予定数
	 * @param beforeProcessPlan
	 *            前工程
	 * @param processPlan
	 *            工程
	 * @param workCalendar
	 *            操業カレンダ
	 * @param workCalendarBreaks
	 *            休憩時間リスト
	 * @param isStepOverDay
	 *            日跨ぎあり
	 *
	 * @return 日跨ぎあり
	 */
	public boolean calculateScheduleOfStartDate(
			Timestamp planStartDate, int planQty, DProcessPlan beforeProcessPlan,
			DProcessPlan processPlan, MWorkCalendar workCalendar, List<MWorkCalendarBreak> workCalendarBreaks, boolean isStepOverDay) {

		if (beforeProcessPlan == null) {
			processPlan.planStartDate = TimestampUtil.toTimestamp(planStartDate);
			return false;
		}

		Date date = null;

		if (isStepOverDay) {
			date = DateUtil.toDate(processPlan.planStartDate);
		} else {
			date = DateUtils.addSeconds(beforeProcessPlan.planStartDate, beforeProcessPlan.processTime);
		}

		List<DateRrangeDto> downTimes = getDownTimes(workCalendar, workCalendarBreaks);

		// 操業カレンダチェック
		Date beforeDate = DateUtils.truncate(beforeProcessPlan.planStartDate, Calendar.DAY_OF_MONTH);

		if (downTimes.isEmpty()) {
			// 操業カレンダなし
			processPlan.planStartDate = TimestampUtil.toTimestamp(date);

			if (!isStepOverDay && DateUtil.neDate(beforeDate, processPlan.planStartDate)) {
				return true;
			}

			return false;
		}

		// 休止時間チェック
		for (DateRrangeDto downTime : downTimes) {

			// 休止開始時刻 <= 開始時刻 < 休止終了時刻 = 休止終了時刻 + (開始時刻 - 休止開始時刻)
			if (DateUtil.ge(date, downTime.start) && DateUtil.lt(date, downTime.end)) {
				long time = downTime.end.getTime() + (date.getTime() - downTime.start.getTime());

				if (DateUtil.ge(beforeProcessPlan.planStartDate, downTime.start) && DateUtil.lt(beforeProcessPlan.planStartDate, downTime.end)) {
					time = time - (beforeProcessPlan.planStartDate.getTime() - downTime.start.getTime());
				}

				date = new Date(time);
			}
		}

		processPlan.planStartDate = TimestampUtil.toTimestamp(date);

		if (DateUtil.neDate(workCalendar.workDay, processPlan.planStartDate)) {
			return true;
		}

		return false;
	}

	/**
	 * 終了時刻のスケジュール計算し、工程に設定します。
	 *
	 * @param planQty
	 *            製造予定数
	 * @param beforeProcessPlan
	 *            工程
	 * @param workCalendar
	 *            操業カレンダ
	 * @param workCalendarBreaks
	 *            休憩時間リスト
	 * @param isStepOverDay
	 *            日跨ぎあり
	 *
	 * @return 日跨ぎあり
	 */
	public boolean calculateScheduleOfEndDate(
			int planQty, DProcessPlan processPlan, MWorkCalendar workCalendar, List<MWorkCalendarBreak> workCalendarBreaks, boolean isStepOverDay) {

		Date date = null;

		if (isStepOverDay) {
			date = DateUtil.toDate(processPlan.planEndDate);
		} else {
			date = DateUtils.addSeconds(processPlan.planStartDate, processPlan.processTime * planQty);
		}

		List<DateRrangeDto> downTimes = getDownTimes(workCalendar, workCalendarBreaks);

		if (downTimes.isEmpty()) {
			// 操業カレンダなし
			processPlan.planEndDate = TimestampUtil.toTimestamp(date);

			// チェック操業カレンダより終了日が後の場合は日跨ぎ
			if (DateUtil.ltDate(workCalendar.workDay, processPlan.planEndDate)) {
				return true;
			}

			return false;
		}

		// 休止時間チェック
		for (DateRrangeDto downtime : downTimes) {

			Date startDateTime = downtime.start;
			Date endDateTime = downtime.end;

			// 開始時刻 >= 休止開始 && 開始時刻 < 休止終了
			if (DateUtil.ge(processPlan.planStartDate, startDateTime) && DateUtil.lt(processPlan.planStartDate, endDateTime)) {
				date = new Date(date.getTime() + (endDateTime.getTime() - processPlan.planStartDate.getTime()));
				continue;
			}

				// 開始時刻 >= 休止終了 = 終了時刻変化なし
			if (DateUtil.ge(processPlan.planStartDate, endDateTime)) {
				continue;
			}

			// 終了時刻 <= 休止開始 = 終了時刻変化なし
			if (DateUtil.le(date, startDateTime)) {
				continue;
			}

			// 開始時刻 <= 休止開始 && 終了時刻 >= 休止終了 = [終了時刻 + (休止終了 - 休止開始)]
			if (DateUtil.le(processPlan.planStartDate, startDateTime) && DateUtil.ge(date, endDateTime)) {
				date = new Date(date.getTime() + (endDateTime.getTime() - startDateTime.getTime()));
				continue;
			}

			// 開始時刻 <= 休止開始 && 終了時刻 <= 休止終了 = [休止終了 + (終了時刻 - 休止開始)]
			if (DateUtil.le(processPlan.planStartDate, startDateTime) && DateUtil.le(date, endDateTime)) {
				date = new Date(endDateTime.getTime() + (date.getTime() - startDateTime.getTime()));
			}
		}

		processPlan.planEndDate = TimestampUtil.toTimestamp(date);

		if (DateUtil.neDate(workCalendar.workDay, processPlan.planEndDate)) {
			return true;
		}

		return false;
	}

	/** 休止時間リスト取得 */
	private List<DateRrangeDto> getDownTimes(MWorkCalendar workCalendar, List<MWorkCalendarBreak> workCalendarBreaks) {

		if (workCalendar == null) {
			return new ArrayList<>();
		}

		Date startDate = DateUtils.truncate(workCalendar.workDay, Calendar.DAY_OF_MONTH);

		if (workCalendar.holiday) {

			List<DateRrangeDto> downTimes = new ArrayList<>();
			DateRrangeDto dateRrange = new DateRrangeDto();
			dateRrange.start = startDate;
			dateRrange.end = DateUtils.addDays(startDate, 1);

			downTimes.add(dateRrange);

			return downTimes;
		}

		List<DateRrangeDto> downTimes = new ArrayList<>();

		if (workCalendar.workStartTime != null) {

			DateRrangeDto dateRrange = new DateRrangeDto();
			dateRrange.start = startDate;
			dateRrange.end = DateUtil.setTime(startDate, workCalendar.workStartTime);

			downTimes.add(dateRrange);
		}

		List<DateRrangeDto> breakDateTimes = new ArrayList<>();

		if (workCalendarBreaks != null) {

			for (MWorkCalendarBreak workCalendarBreak : workCalendarBreaks) {

				if (workCalendarBreak.breakStartTime == null || workCalendarBreak.breakEndTime == null) {
					continue;
				}

				Date breakStartDate = DateUtil.setTime(startDate, workCalendarBreak.breakStartTime);
				Date breakEndDate = DateUtil.setTime(startDate, workCalendarBreak.breakEndTime);

				if (breakDateTimes.isEmpty()) {

					DateRrangeDto dateRrange = new DateRrangeDto();
					dateRrange.start = breakStartDate;
					dateRrange.end = breakEndDate;

					breakDateTimes.add(dateRrange);

					continue;
				}

				// 時刻重複チェック
				boolean isDuplication = false;

				for (int i = 0; i < breakDateTimes.size(); i++) {

					DateRrangeDto breakTime = breakDateTimes.get(i);

					Date breakStartTime = breakTime.start;
					Date breakEndTime = breakTime.end;

					boolean isDuplicationOfStartTimeInRange = (DateUtil.ge(breakStartDate, breakStartTime) && DateUtil.lt(breakStartDate, breakEndTime));
					boolean isDuplicationOfEndTimeInRange = (DateUtil.gt(breakEndDate, breakStartTime) && DateUtil.le(breakEndDate, breakEndTime));
					boolean isDuplicationWithinEndTimeFromStartTime = (DateUtil.le(breakStartDate, breakStartTime) && DateUtil.ge(breakEndDate, breakEndTime));

					if (isDuplicationOfEndTimeInRange || isDuplicationOfStartTimeInRange || isDuplicationWithinEndTimeFromStartTime) {
						breakDateTimes.remove(i);
						isDuplication = true;
						continue;
					}
				}

				if (isDuplication) {
					continue;
				}

				DateRrangeDto dateRrange = new DateRrangeDto();
				dateRrange.start = breakStartDate;
				dateRrange.end = breakEndDate;

				breakDateTimes.add(dateRrange);
			}

			// 開始時刻と休憩時間の重複チェック
			if (downTimes.isEmpty() && !breakDateTimes.isEmpty()) {
				downTimes = breakDateTimes;

			} else if (!breakDateTimes.isEmpty() && !downTimes.isEmpty()) {

				for (DateRrangeDto checkMapBreak : breakDateTimes) {

					if (DateUtil.lt(checkMapBreak.end, downTimes.get(0).end)) {
						continue;
					}

					downTimes.add(checkMapBreak);
				}
			}
		}

		if (workCalendar.workEndTime != null) {

			Date startWorkDate = DateUtil.setTime(startDate, workCalendar.workEndTime);

			List<DateRrangeDto> checkedDownTimes = new ArrayList<>();

			// 終了時刻との重複チェック
			for (DateRrangeDto downTime : downTimes) {

				if (DateUtil.lt(startWorkDate, downTime.end)) {
					continue;
				}

				checkedDownTimes.add(downTime);
			}

			downTimes = checkedDownTimes;

			// 終了時刻追加
			DateRrangeDto dateRrange = new DateRrangeDto();
			dateRrange.start = startWorkDate;
			dateRrange.end = DateUtils.addDays(startDate, 1);

			downTimes.add(dateRrange);
		}

		return downTimes;
	}
}

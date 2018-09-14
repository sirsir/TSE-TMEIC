package jp.co.tmeic.mespd.dto.jqgrid.master;

import java.text.ParseException;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.seasar.struts.util.RequestUtil;

/**
 * 操業カレンダマスタ
 *
 */
public class MWorkCalendarDto extends JqgridDto {

	/** 操業カレンダID */
	public String workCalendarId;

	/** 設定日付 */
	public String workDay;

	/** 休日設定 */
	public String holiday;

	/** 稼働時間開始 */
	public String workStartTime;

	/** 稼働時間終了 */
	public String workEndTime;

	/** 休憩時間1ID */
	public String breakTimeId1;

	/** 休憩開始時間1 */
	public String breakStartTime1;

	/** 休憩終了時間1 */
	public String breakEndTime1;

	/** 休憩時間2ID */
	public String breakTimeId2;

	/** 休憩開始時間2 */
	public String breakStartTime2;

	/** 休憩終了時間2 */
	public String breakEndTime2;

	/** 休憩時間3ID */
	public String breakTimeId3;

	/** 休憩開始時間3 */
	public String breakStartTime3;

	/** 休憩終了時間3 */
	public String breakEndTime3;

	/** 休憩時間4ID */
	public String breakTimeId4;

	/** 休憩開始時間4 */
	public String breakStartTime4;

	/** 休憩終了時間4 */
	public String breakEndTime4;

	/** 休憩時間5ID */
	public String breakTimeId5;

	/** 休憩開始時間5 */
	public String breakStartTime5;

	/** 休憩終了時間5 */
	public String breakEndTime5;

	/** 日付(曜日) */
	public String getWorkDayAndWeek() throws ParseException {

		if (StringUtils.isBlank(workDay)) {
			return StringUtils.EMPTY;
		}

		return DateFormatUtils.format(DateUtils.parseDate(workDay, "yyyy/MM/dd"), "dd(E)", RequestUtil.getRequest().getLocale());
	}

	/**
	 * 休憩時間データブランクチェック
	 *
	 * @return
	 */
	public boolean isBlankOfBreakTimes() {

		return isBlankOfBreakTime(breakStartTime1, breakEndTime1)
				&& (isBlankOfBreakTime(breakStartTime2, breakEndTime2))
				&& (isBlankOfBreakTime(breakStartTime3, breakEndTime3))
				&& (isBlankOfBreakTime(breakStartTime4, breakEndTime4))
				&& (isBlankOfBreakTime(breakStartTime5, breakEndTime5));
	}

	/**
	 * 休憩時間データブランクチェック
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private boolean isBlankOfBreakTime(String startTime, String endTime) {
		return (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime));
	}

}

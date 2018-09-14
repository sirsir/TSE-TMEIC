/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.tmeic.mespd.action.master;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import jp.co.tmeic.mespd.action.AbstractAction;
import jp.co.tmeic.mespd.dto.jqgrid.master.MWorkCalendarDto;
import jp.co.tmeic.mespd.entity.MWorkCalendar;
import jp.co.tmeic.mespd.entity.MWorkCalendarBreak;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.master.WorkCalendarForm;
import jp.co.tmeic.mespd.service.MWorkCalendarBreakService;
import jp.co.tmeic.mespd.service.MWorkCalendarService;
import jp.co.tmeic.mespd.service.ProductPlanService;
import jp.co.tmeic.mespd.utils.BrowserMsg;
import jp.co.tmeic.mespd.utils.DateUtil;
import jp.co.tmeic.mespd.utils.JSONUtil;
import jp.co.tmeic.mespd.utils.JqgridUtil;
import jp.co.tmeic.mespd.utils.JqueryUtil;
import jp.co.tmeic.mespd.utils.TimeUtil;
import jp.co.tmeic.mespd.utils.TimestampUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.TimeConversionUtil;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth(authorityId = "operation")
public class WorkCalendarAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** UserTransaction */
	public UserTransaction userTransaction;

	/** ActionForm */
	@Resource
	@ActionForm
	protected WorkCalendarForm workCalendarForm;

	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected MWorkCalendarService mWorkCalendarService;

	@Resource
	protected MWorkCalendarBreakService mWorkCalendarBreakService;

	@Resource
	protected ProductPlanService productPlanService;

	/** 検索 */
	@Execute(validator = false)
	public String index() {

		try {

			Date now = DateUtil.now();
			final String nowYear = DateFormatUtils.format(now, "yyyy");
			final String nowMonth = DateFormatUtils.format(now, "MM");

			if (StringUtils.isBlank(workCalendarForm.year)) {
				workCalendarForm.year = nowYear;
				workCalendarForm.month = nowMonth;
			}

			workCalendarForm.yearOptionItems = new ArrayList<>();

			// データベースにある最古の設定年から年リストを生成する
			int[] years = this.getStartAndEndYear(nowYear);

			for (int i = years[0]; i <= years[1]; i++) {
				String y = String.valueOf(i);
				workCalendarForm.yearOptionItems.add(new LabelValueBean(y, y));
			}

			workCalendarForm.monthOptionItems = new ArrayList<>();

			workCalendarForm.weekdayNames =
					Arrays.asList(MessageResourcesUtil.getMessage(request.getLocale(), "default.dialog.weeks").split(","));

			for (int i = 1; i < 13; i++) {
				String month = String.format("%02d", i);
				workCalendarForm.monthOptionItems.add(new LabelValueBean(month, month));
			}

			indexValidation(workCalendarForm);

			if (StringUtils.isNotBlank(workCalendarForm.changeYear) && StringUtils.isNotBlank(workCalendarForm.changeMonth)) {

				int changeYear = NumberUtils.toInt(workCalendarForm.changeYear);
				int changeMonth = NumberUtils.toInt(workCalendarForm.changeMonth);

				boolean checkYear = (years[0] > changeYear || years[1] < changeYear);
				boolean checkMonth = (changeMonth < 1 || changeMonth > 12);

				if (checkYear || checkMonth) {
					workCalendarForm.changeYear = null;
					workCalendarForm.changeMonth = null;

					throw new ValidationException(
							MessageResourcesUtil.getMessage(locale(), "errors.out.of.range",
									MessageResourcesUtil.getMessage(locale(), "setting.month")));
				}

				workCalendarForm.year = workCalendarForm.changeYear;
				workCalendarForm.month = workCalendarForm.changeMonth;
				workCalendarForm.changeYear = null;
				workCalendarForm.changeMonth = null;
			}

			int settingYear = NumberUtils.toInt(workCalendarForm.year);
			int settingMonth = NumberUtils.toInt(workCalendarForm.month);

			if (years[0] > settingYear || years[1] < settingYear) {

				throw new ValidationException(
						MessageResourcesUtil.getMessage(locale(), "errors.out.of.range",
								MessageResourcesUtil.getMessage(locale(), "setting.month")));
			}

			Date startDate = DateUtil.of(settingYear, settingMonth, 1);

			workCalendarForm.workCalendarJson =
					JSONUtil.encode(JqgridUtil.init(mWorkCalendarService.findByEndOfMonthFromStartWorkDay(startDate)));

		} catch (ValidationException ex) {

			workCalendarForm.workCalendarJson = "[]";
			BrowserMsg.toAlert(ex.getMessage());

		} catch (Exception ex) {
			logger.error("Exception", ex);
		}

		return "index.jsp";
	}

	/**
	 * 検索可能か検証を行う。
	 *
	 * @param form
	 */
	private void indexValidation(WorkCalendarForm form) {

		ValidateUtil.integer("setting.year", form.year);
		ValidateUtil.integer("setting.month", form.month);
	}

	private int[] getStartAndEndYear(final String nowYear) {

		int[] years = new int[2];
		Date firstWorkDay = mWorkCalendarService.getFirstWorkDay();
		years[0] = Integer.parseInt(DateFormatUtils.format(firstWorkDay, "yyyy"));
		int iNowYear = Integer.parseInt(nowYear);

		if (years[0] >= iNowYear) {
			years[0] = iNowYear - 1;
		}

		years[1] = iNowYear + 3;

		return years;
	}

	/** 登録 */
	@Execute(validator = false)
	public String register() {

		Map<String, String> json = new HashMap<>();

		boolean isCommit = false;

		try {
			List<MWorkCalendarDto> workCalendars = JSONUtil.decode(workCalendarForm.workCalendarJson, MWorkCalendarDto[].class);

			if (registerExe(workCalendars, json)) {
				json.put("result", "OK");

				isCommit = true;
			}

		} catch (Exception ex) {
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.register"));
		} finally {

			if (!isCommit) {
				try {
					userTransaction.setRollbackOnly();
				} catch (IllegalStateException | SystemException ex) {
					logger.error("Exception", ex);
				}
			}

			JqueryUtil.callback(JSONUtil.encode(json));
		}

		return null;
	}

	/** 登録実行 */
	private boolean registerExe(List<MWorkCalendarDto> workCalendars, Map<String, String> json) {

		List<MWorkCalendar> mWorkCalendars = new ArrayList<>();

		for (MWorkCalendarDto workCalendar : workCalendars) {

			MWorkCalendar mWorkCalendar =
					Beans.createAndCopy(MWorkCalendar.class, workCalendar).timeConverter("HHmm", "workStartTime", "workEndTime").execute();

			if (workCalendar.workCalendarId != null) {
				MWorkCalendar oldMWorkCalendar = mWorkCalendarService.findById(workCalendar.workCalendarId);

				if (oldMWorkCalendar != null) {
					mWorkCalendarBreakService.deleteByWorkCalendarId(oldMWorkCalendar.workCalendarId);
					mWorkCalendarService.delete(oldMWorkCalendar);
				}

			} else {
				mWorkCalendar.workCalendarId = DateFormatUtils.format(mWorkCalendar.workDay, "yyyyMMdd");
			}

			mWorkCalendarService.insert(mWorkCalendar);

			// 休憩時間の追加・更新
			if (!insertOrUpdateMWorkCalendarBreak(mWorkCalendar.workCalendarId, workCalendar, json)) {
				return false;
			}
			mWorkCalendars.add(mWorkCalendar);
		}

		// 製造計画スケジュール再計算
		productPlanService.rescheduleNonStart();

		return true;
	}

	/**
	 * 休憩時間の追加・更新
	 *
	 * @param workCalendarId
	 * @param workCalendar
	 * @param json
	 * */
	private boolean insertOrUpdateMWorkCalendarBreak(String workCalendarId, MWorkCalendarDto workCalendar, Map<String, String> json) {

		if (workCalendar.isBlankOfBreakTimes()) {
			return true;
		}

		// エラーチェックする。
		if (isBreakTimesBlank(workCalendar)) {
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.invalid", MessageResourcesUtil.getMessage(locale(), "work.calendar.break.time")));
			return false;
		}

		List<MWorkCalendarBreak> updateMBreaks = new ArrayList<>();

		if (StringUtils.isNotBlank(workCalendar.breakStartTime1)) {
			updateMBreaks.add(createMWorkCalendarBreak(workCalendarId, 1, workCalendar.breakStartTime1, workCalendar.breakEndTime1));
		}

		if (StringUtils.isNotBlank(workCalendar.breakStartTime2)) {
			updateMBreaks.add(createMWorkCalendarBreak(workCalendarId, 2, workCalendar.breakStartTime2, workCalendar.breakEndTime2));
		}

		if (StringUtils.isNotBlank(workCalendar.breakStartTime3)) {
			updateMBreaks.add(createMWorkCalendarBreak(workCalendarId, 3, workCalendar.breakStartTime3, workCalendar.breakEndTime3));
		}

		if (StringUtils.isNotBlank(workCalendar.breakStartTime4)) {
			updateMBreaks.add(createMWorkCalendarBreak(workCalendarId, 4, workCalendar.breakStartTime4, workCalendar.breakEndTime4));
		}

		if (StringUtils.isNotBlank(workCalendar.breakStartTime5)) {
			updateMBreaks.add(createMWorkCalendarBreak(workCalendarId, 5, workCalendar.breakStartTime5, workCalendar.breakEndTime5));
		}

		// 重複チェック
		if (isDuplicate(updateMBreaks)) {
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.duplicate", MessageResourcesUtil.getMessage(locale(), "work.calendar.break.time")));
			return false;
		}

		mWorkCalendarBreakService.deleteByWorkCalendarId(workCalendarId);

		for (MWorkCalendarBreak updateMBreak : updateMBreaks) {
			jdbcManager.insert(updateMBreak).excludesNull().execute();
		}

		return true;
	}

	/**
	 * 休憩時間の開始または、終了のどちらかが空白になっているか確認する。
	 *
	 * @param workCalendar
	 * */
	private boolean isBreakTimesBlank(MWorkCalendarDto workCalendar) {

		if (isBreakTimeBlank(workCalendar.breakStartTime1, workCalendar.breakEndTime1)) {
			return true;
		}

		if (isBreakTimeBlank(workCalendar.breakStartTime2, workCalendar.breakEndTime2)) {
			return true;
		}

		if (isBreakTimeBlank(workCalendar.breakStartTime3, workCalendar.breakEndTime3)) {
			return true;
		}

		if (isBreakTimeBlank(workCalendar.breakStartTime4, workCalendar.breakEndTime4)) {
			return true;
		}

		if (isBreakTimeBlank(workCalendar.breakStartTime5, workCalendar.breakEndTime5)) {
			return true;
		}

		return false;
	}

	/**
	 * 休憩時間エラー確認
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private boolean isBreakTimeBlank(String startTime, String endTime) {

		if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
			return false;
		}

		// 開始のみ設定されている場合エラー
		if (StringUtils.isNotBlank(startTime) && StringUtils.isBlank(endTime)) {
			return true;
		}

		// 終了のみ設定されている場合エラー
		if (StringUtils.isBlank(startTime) && StringUtils.isNotBlank(endTime)) {
			return true;
		}

		if (!NumberUtils.isNumber(startTime)) {
			return true;
		}

		if (!NumberUtils.isNumber(endTime)) {
			return true;
		}

		if (Integer.parseInt(startTime) >= Integer.parseInt(endTime)) {
			return true;
		}

		return false;
	}

	/** 休憩時間重複チェック */
	private boolean isDuplicate(List<MWorkCalendarBreak> updateMBreaks) {

		for (int i = 0; i < updateMBreaks.size(); i++) {

			MWorkCalendarBreak mBreak = updateMBreaks.get(i);

			for (int checkIndex = 0; checkIndex < updateMBreaks.size(); checkIndex++) {

				if (i == checkIndex) {
					continue;
				}

				MWorkCalendarBreak checkMBreak = updateMBreaks.get(checkIndex);

				Time breakStartTime = checkMBreak.breakStartTime;
				Time breakEndTime = checkMBreak.breakEndTime;

				// 開始時刻に重複がある
				boolean isDuplicationOfStartTimeInRange = (TimeUtil.ge(mBreak.breakStartTime, breakStartTime) && TimeUtil.lt(mBreak.breakStartTime, breakEndTime));
				// 終了時刻に重複がある
				boolean isDuplicationOfEndTimeInRange = (TimeUtil.gt(mBreak.breakEndTime, breakStartTime) && TimeUtil.le(mBreak.breakEndTime, breakEndTime));
				// 開始時刻から終了時刻内に重複がある
				boolean isDuplicationWithinEndTimeFromStartTime = (TimeUtil.le(mBreak.breakStartTime, breakStartTime) && TimeUtil.ge(mBreak.breakEndTime, breakEndTime));

				if (isDuplicationOfStartTimeInRange || isDuplicationOfEndTimeInRange || isDuplicationWithinEndTimeFromStartTime) {
					return true;
				}
			}
		}

		return false;
	}

	/** 休憩時間エンティティ生成 */
	private MWorkCalendarBreak createMWorkCalendarBreak(String workCalendarId, int breakTimeId, String breakStartTime, String breakEndTime) {

		MWorkCalendarBreak mBreak = new MWorkCalendarBreak();
		mBreak.createDate = TimestampUtil.now();
		mBreak.updateDate = TimestampUtil.now();
		mBreak.workCalendarId = workCalendarId;
		mBreak.breakTimeId = breakTimeId;
		mBreak.breakStartTime = TimeConversionUtil.toTime(breakStartTime, "HHmm");
		mBreak.breakEndTime = TimeConversionUtil.toTime(breakEndTime, "HHmm");

		return mBreak;
	}
}

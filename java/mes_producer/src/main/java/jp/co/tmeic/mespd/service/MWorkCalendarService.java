package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MWorkCalendarNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.dto.jqgrid.master.MWorkCalendarDto;
import jp.co.tmeic.mespd.entity.MWorkCalendar;
import jp.co.tmeic.mespd.entity.MWorkCalendarBreak;
import jp.co.tmeic.mespd.utils.DateUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.StringConversionUtil;

/**
 * {@link MWorkCalendar}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MWorkCalendarService extends AbstractService<MWorkCalendar> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param workCalendarId
	 *            識別子
	 * @return エンティティ
	 */
	public MWorkCalendar findById(String workCalendarId) {
		return select().id(workCalendarId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MWorkCalendar> findAllOrderById() {
		return select().orderBy(asc(workCalendarId())).getResultList();
	}

	/**
	 * 開始日から月末までを検索します。
	 *
	 * @param startWorkDay
	 *            開始日
	 * @return 操業カレンダリスト
	 */
	public List<MWorkCalendarDto> findByEndOfMonthFromStartWorkDay(Date startWorkDay) {

		if (startWorkDay == null) {
			return new ArrayList<>();
		}

		Date endWorkDay = DateUtil.getMonthLastDay(startWorkDay);

		List<MWorkCalendar> mWorkCalendars =
				select()
						.leftOuterJoin(MWorkCalendarBreakList())
						.where(new SimpleWhere().ge(workDay(), startWorkDay).le(workDay(), endWorkDay))
						.orderBy(
								asc(workCalendarId()),
								asc(MWorkCalendarBreakList().breakTimeId()))
						.getResultList();

		int startDay = DateUtil.getDay(startWorkDay);
		int endDay = DateUtil.getDay(endWorkDay);

		Date setDay = startWorkDay;

		List<MWorkCalendarDto> workCalendarDtos = new ArrayList<>();

		for (int i = startDay; i <= endDay; i++) {

			MWorkCalendarDto mWorkCalendarDto = getMWorkCalendarDtoBy(mWorkCalendars, setDay);
			workCalendarDtos.add(mWorkCalendarDto);

			setDay = DateUtils.addDays(setDay, 1);
		}

		return workCalendarDtos;
	}

	/**
	 * @param mWorkCalendars
	 * @param date
	 * @return
	 */
	private MWorkCalendarDto getMWorkCalendarDtoBy(List<MWorkCalendar> mWorkCalendars, Date date) {

		for (MWorkCalendar mWorkCalendar : mWorkCalendars) {
			if (DateUtils.isSameDay(mWorkCalendar.workDay, date)) {

				MWorkCalendarDto mWorkCalendarDto =
						Beans.createAndCopy(MWorkCalendarDto.class, mWorkCalendar).timeConverter("HHmm", "workStartTime", "workEndTime").execute();

				for (int i = 0; i < CollectionUtils.size(mWorkCalendar.MWorkCalendarBreakList); i++) {

					MWorkCalendarBreak mWorkCalendarBreak = mWorkCalendar.MWorkCalendarBreakList.get(i);

					final String breakTimeId = String.valueOf(mWorkCalendarBreak.breakTimeId);
					final String breakStartTime = StringConversionUtil.toString(mWorkCalendarBreak.breakStartTime, "HHmm");
					final String breakEndTime = StringConversionUtil.toString(mWorkCalendarBreak.breakEndTime, "HHmm");

					switch (i) {
						case 0:
							mWorkCalendarDto.breakTimeId1 = breakTimeId;
							mWorkCalendarDto.breakStartTime1 = breakStartTime;
							mWorkCalendarDto.breakEndTime1 = breakEndTime;
							break;

						case 1:
							mWorkCalendarDto.breakTimeId2 = breakTimeId;
							mWorkCalendarDto.breakStartTime2 = breakStartTime;
							mWorkCalendarDto.breakEndTime2 = breakEndTime;
							break;

						case 2:
							mWorkCalendarDto.breakTimeId3 = breakTimeId;
							mWorkCalendarDto.breakStartTime3 = breakStartTime;
							mWorkCalendarDto.breakEndTime3 = breakEndTime;
							break;

						case 3:
							mWorkCalendarDto.breakTimeId4 = breakTimeId;
							mWorkCalendarDto.breakStartTime4 = breakStartTime;
							mWorkCalendarDto.breakEndTime4 = breakEndTime;
							break;

						case 4:
							mWorkCalendarDto.breakTimeId5 = breakTimeId;
							mWorkCalendarDto.breakStartTime5 = breakStartTime;
							mWorkCalendarDto.breakEndTime5 = breakEndTime;
							break;
					}
				}

				return mWorkCalendarDto;
			}
		}

		MWorkCalendarDto mWorkCalendarDto = new MWorkCalendarDto();
		mWorkCalendarDto.workDay = DateFormatUtils.format(date, "yyyy/MM/dd");

		return mWorkCalendarDto;
	}

	/**
	 * 最初の設定日付を取得します。
	 *
	 * @return 設定日付
	 */
	public Date getFirstWorkDay() {

		MWorkCalendar mWorkCalendars =
				select()
						.maxRows(1)
						.orderBy(asc(workDay()))
						.getSingleResult();

		if (mWorkCalendars == null) {
			return DateUtil.now();
		}

		return mWorkCalendars.workDay;
	}

	/**
	 * 設定日付でエンティティを検索します。
	 *
	 * @param workDay
	 *            設定日付
	 * @return エンティティ
	 */
	public MWorkCalendar findByWorkDay(Date workDay) {

		Date workDayTruncate = DateUtils.truncate(workDay, Calendar.DAY_OF_MONTH);

		return select()
				.leftOuterJoin(MWorkCalendarBreakList())
				.where(new SimpleWhere().eq(workDay(), workDayTruncate))
				.orderBy(asc(workStartTime())).getSingleResult();
	}
}
package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MWorkCalendarBreakNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MWorkCalendarBreak;

import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link MWorkCalendarBreak}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MWorkCalendarBreakService extends AbstractService<MWorkCalendarBreak> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param workCalendarId
	 *            識別子
	 * @param breakTimeId
	 *            識別子
	 * @return エンティティ
	 */
	public MWorkCalendarBreak findById(String workCalendarId, Integer breakTimeId) {
		return select().id(workCalendarId, breakTimeId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MWorkCalendarBreak> findAllOrderById() {
		return select().orderBy(asc(workCalendarId()), asc(breakTimeId())).getResultList();
	}

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param workCalendarId
	 *            識別子
	 * @return エンティティ
	 */
	public List<MWorkCalendarBreak> findByWorkCalendarId(String workCalendarId) {
		return select()
				.where(new SimpleWhere()
						.eq(workCalendarId(), workCalendarId))
				.orderBy(asc(breakTimeId()))
				.getResultList();
	}

	/**
	 * 識別子を条件に削除します。
	 *
	 * @param workCalendarId
	 * @return 削除件数
	 */
	public int deleteByWorkCalendarId(String workCalendarId) {
		return jdbcManager
				.updateBySql("DELETE FROM m_work_calendar_break WHERE work_calendar_id=?", String.class)
				.params(workCalendarId)
				.execute();
	}
}
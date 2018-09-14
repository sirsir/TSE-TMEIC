package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DSpecProductPlanNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DSpecProductPlan;

import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link DSpecProductPlan}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DSpecProductPlanService extends AbstractService<DSpecProductPlan> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @param specComponentNo
	 *            識別子
	 * @return エンティティ
	 */
	public DSpecProductPlan findById(String productPlanNo, Integer processComponentNo, Integer specComponentNo) {
		return select().id(productPlanNo, processComponentNo, specComponentNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DSpecProductPlan> findAllOrderById() {
		return select().orderBy(asc(productPlanNo()), asc(processComponentNo()), asc(specComponentNo())).getResultList();
	}

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @param specComponentNo
	 *            識別子
	 * @return エンティティ
	 */
	public List<DSpecProductPlan> findByIdJoinSpecPlan(String productPlanNo, Integer processComponentNo) {
		return select()
				.where(new SimpleWhere()
						.eq(productPlanNo(), productPlanNo)
						.eq(processComponentNo(), processComponentNo))
				.leftOuterJoin(DSpecPlan())
				.leftOuterJoin(DSpecPlan().DSpecAttributePlan())
				.leftOuterJoin(DSpecProductResultList())
				.orderBy(asc(productPlanNo()), asc(processComponentNo()))
				.getResultList();
	}

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @return エンティティのリスト
	 */
	public List<DSpecProductPlan> findByProcessComponentNo(String productPlanNo, Integer processComponentNo) {
		return select()
				.where(new SimpleWhere()
						.eq(productPlanNo(), productPlanNo)
						.eq(processComponentNo(), processComponentNo))
				.getResultList();
	}
}
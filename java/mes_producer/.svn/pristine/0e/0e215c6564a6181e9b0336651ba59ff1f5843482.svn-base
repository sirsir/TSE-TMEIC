package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DMaterialPlanNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DMaterialPlan;

import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link DMaterialPlan}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DMaterialPlanService extends AbstractService<DMaterialPlan> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @param materialComponentNo
	 *            識別子
	 * @return エンティティ
	 */
	public DMaterialPlan findById(String productPlanNo, Integer processComponentNo, Integer materialComponentNo) {
		return select().id(productPlanNo, processComponentNo, materialComponentNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DMaterialPlan> findAllOrderById() {
		return select().orderBy(asc(productPlanNo()), asc(processComponentNo()), asc(materialComponentNo())).getResultList();
	}

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 *
	 * @return エンティティのリスト
	 */
	public List<DMaterialPlan> findByProcessComponentNo(String productPlanNo, Integer processComponentNo) {
		return select()
				.where(new SimpleWhere()
						.eq(productPlanNo(), productPlanNo)
						.eq(processComponentNo(), processComponentNo))
				.getResultList();
	}
}
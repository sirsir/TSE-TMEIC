package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DMaterialProcessResultNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DMaterialProcessResult;

/**
 * {@link DMaterialProcessResult}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class DMaterialProcessResultService extends AbstractService<DMaterialProcessResult> {

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
    public DMaterialProcessResult findById(String productPlanNo, Integer processComponentNo, Integer materialComponentNo) {
        return select().id(productPlanNo, processComponentNo, materialComponentNo).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<DMaterialProcessResult> findAllOrderById() {
        return select().orderBy(asc(productPlanNo()), asc(processComponentNo()), asc(materialComponentNo())).getResultList();
    }

	/**
	 * データの挿入、または更新を行います。
	 *
	 * @param entity
	 */
	public void insertOrUpdate(DMaterialProcessResult entity) {

		boolean isExist = isExist(entity.productPlanNo, entity.processComponentNo, entity.materialComponentNo);

		if (isExist) {
			update(entity);
		} else {
			insert(entity);
		}
	}

	/**
	 * データの挿入、または更新を行います。
	 *
	 * @param entities
	 */
	public void insertOrUpdate(List<DMaterialProcessResult> entities) {

		for (DMaterialProcessResult entity : entities) {
			insertOrUpdate(entity);
		}
	}
}
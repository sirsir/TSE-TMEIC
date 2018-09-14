package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DSpecProcessResultNames.processComponentNo;
import static jp.co.tmeic.mespd.entity.DSpecProcessResultNames.productPlanNo;
import static jp.co.tmeic.mespd.entity.DSpecProcessResultNames.specComponentNo;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DSpecProcessResult;

/**
 * {@link DSpecProcessResult}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class DSpecProcessResultService extends AbstractService<DSpecProcessResult> {

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
    public DSpecProcessResult findById(String productPlanNo, Integer processComponentNo, Integer specComponentNo) {
        return select().id(productPlanNo, processComponentNo, specComponentNo).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<DSpecProcessResult> findAllOrderById() {
        return select().orderBy(asc(productPlanNo()), asc(processComponentNo()), asc(specComponentNo())).getResultList();
    }

	/**
	 * データの挿入、または更新を行います。
	 *
	 * @param entity
	 */
	public void insertOrUpdate(DSpecProcessResult entity) {

		boolean isExist = isExist(entity.productPlanNo, entity.processComponentNo, entity.specComponentNo);

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
	public void insertOrUpdate(List<DSpecProcessResult> entities) {

		for (DSpecProcessResult entity : entities) {
			insertOrUpdate(entity);
		}
	}
}
package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DMaterialProductResultNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DMaterialProcessResult;
import jp.co.tmeic.mespd.entity.DMaterialProductResult;

/**
 * {@link DMaterialProductResult}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DMaterialProductResultService extends AbstractService<DMaterialProductResult> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @param serialNo
	 *            識別子
	 * @param revision
	 *            識別子
	 * @param materialComponentNo
	 *            識別子
	 * @return エンティティ
	 */
	public DMaterialProductResult findById(String productPlanNo, Integer processComponentNo, String serialNo, Integer revision, Integer materialComponentNo) {
		return select().id(productPlanNo, processComponentNo, serialNo, revision, materialComponentNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DMaterialProductResult> findAllOrderById() {
		return select().orderBy(asc(productPlanNo()), asc(processComponentNo()), asc(serialNo()), asc(revision()), asc(materialComponentNo())).getResultList();
	}

    /**
     * 識別子でエンティティを削除します。
     *
     * @param productPlanNo
     *            識別子
     * @param processComponentNo
     *            識別子
     * @param serialNo
     *            識別子
     * @param revision
     *            識別子
     * @return エンティティ
     */
    public int deleteById(String productPlanNo, Integer processComponentNo, String serialNo, Integer revision) {
    	return jdbcManager
		.updateBySql(
				"DELETE FROM d_material_product_result WHERE "
						+ "product_plan_no=? AND "
						+ "process_component_no=? AND "
						+ "serial_no=? AND "
						+ "revision=?",
				String.class ,
				Integer.class ,
				String.class ,
				Integer.class)
		.params(productPlanNo, processComponentNo, serialNo, revision)
		.execute();

    }

	/**
	 * 指定工程の部材実績を集計した結果を取得します。
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @return
	 */
	public List<DMaterialProcessResult> findActualSum(String productPlanNo, Integer processComponentNo) {

		Map<String, Object> param = new HashMap<>();

		param.put("productPlanNo", productPlanNo);
		param.put("processComponentNo", processComponentNo);

		return jdbcManager.selectBySqlFile(
				DMaterialProcessResult.class,
				"jp/co/tmeic/mespd/service/DMaterialProductResultService_findActualSum.sql",
				param)
				.getResultList();
	}

	/**
	 * データの挿入、または更新を行います。
	 *
	 * @param entity
	 */
	public void insertOrUpdate(DMaterialProductResult entity) {

		boolean isExist =
				isExist(entity.productPlanNo,
						entity.processComponentNo,
						entity.serialNo,
						entity.revision,
						entity.materialComponentNo);

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
	public void insertOrUpdate(List<DMaterialProductResult> entities) {

		for (DMaterialProductResult entity : entities) {
			insertOrUpdate(entity);
		}
	}
}
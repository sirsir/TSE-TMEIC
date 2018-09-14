package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DProductResultNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;
import javax.annotation.Resource;

import jp.co.tmeic.mespd.constant.MesErrorMessageKey;
import jp.co.tmeic.mespd.constant.ProductStatus;
import jp.co.tmeic.mespd.entity.DProductResult;
import jp.co.tmeic.mespd.exception.MesException;

/**
 * {@link DProductResult}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DProductResultService extends AbstractService<DProductResult> {

	@Resource
	protected DSpecProductResultService dSpecProductResultService;

	@Resource
	protected DMaterialProductResultService dMaterialProductResultService;


	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @return エンティティ
	 */
	public DProductResult findById(String productPlanNo) {
		return select().id(productPlanNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DProductResult> findAllOrderById() {
		return select().orderBy(asc(productPlanNo())).getResultList();
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

    	dSpecProductResultService.deleteById(productPlanNo, processComponentNo, serialNo, revision);

    	dMaterialProductResultService.deleteById(productPlanNo, processComponentNo, serialNo, revision);

    	return jdbcManager
		.updateBySql(
				"DELETE FROM d_process_product_result WHERE "
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
	 * 指定製造計画Noの状態を取得します。
	 *
	 * @param productPlanNo
	 *            製造計画No
	 * @return 状態
	 */
	public int getStatus(String productPlanNo) {

		DProductResult dProductResult = select().id(productPlanNo).getSingleResult();

		if (dProductResult == null) {
			return ProductStatus.NONE;
		}

		return dProductResult.status;
	}

	/**
	 * 工程が開始可能か確認します。開始できない場合は、例外を発生させる。
	 *
	 * @param productPlanNo
	 * @exception MesException
	 */
	public void processStartPossible(String productPlanNo) {

		int status = getStatus(productPlanNo);

		switch (status) {
			case ProductStatus.NONE:
				throw new MesException("Production plan of not start, you can not start.", MesErrorMessageKey.MANUFACTURING_PLAN_NOT_STARTED);

			case ProductStatus.IN_PRODUCTION:
				break;

			default:
				throw new MesException("Manufacturing planning, it has been stopped.", MesErrorMessageKey.MANUFACTURING_PLAN_STOP);
		}
	}

}
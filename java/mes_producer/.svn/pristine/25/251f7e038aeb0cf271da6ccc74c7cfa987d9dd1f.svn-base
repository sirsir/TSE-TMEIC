package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DBarcodeLabelManagementNames.barcodeNo;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DBarcodeLabelManagement;
import jp.co.tmeic.mespd.entity.DQualityLabelManagement;

@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DQualityLabelManagementService extends AbstractService<DQualityLabelManagement>{
	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param barcodeNo
	 *            識別子
	 * @return エンティティ
	 */
	public DQualityLabelManagement findById(String barcodeNo,String productPlanNo) {
		return select().id(barcodeNo,productPlanNo).getSingleResult();
	}
	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param serial_no
	 *            識別子
	 * @return エンティティ
	 */
	public DQualityLabelManagement findByProductPlanNoAndSerialNo(String productPlanNo,String serialNo) {
		return jdbcManager
				.selectBySql(
						DQualityLabelManagement.class,
						"SELECT * FROM d_quality_label_management WHERE product_plan_no=? AND serial_no=?",
						productPlanNo, serialNo)
				.getSingleResult();
	}
	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DQualityLabelManagement> findAllOrderById() {
		return select().orderBy(asc(barcodeNo())).getResultList();
	}
	/**
	 * 識別子を条件に削除します。
	 *
	 * @param productPlanNo
	 * 
	 * @param serialNo
	 * @return 削除件数
	 */
	public int deleteById(String productPlanNo,String serialNo) {
		return jdbcManager
				.updateBySql("DELETE FROM d_quality_label_management WHERE product_plan_no=? and serial_no=?", String.class, String.class)
				.params(productPlanNo, serialNo)
				.execute();
	}
	/**
	 * 品質ラベルが存在することを確認してください。
	 *
	 * @param productPlanNo
	 * @param barcodeNo
	 * @return
	 */
	public boolean checkQualityLabel(String productPlanNo, String barcodeNo) {

		Map<String, Object> param = new HashMap<>();

		param.put("productPlanNo", productPlanNo);
		param.put("barcodeNo", barcodeNo);

		long checkQualityLabelCount =
				jdbcManager.getCountBySqlFile(
						"jp/co/tmeic/mespd/service/QualityLabelService_checkQualityLabel.sql",
						param);

		return checkQualityLabelCount != 1;
	}
}

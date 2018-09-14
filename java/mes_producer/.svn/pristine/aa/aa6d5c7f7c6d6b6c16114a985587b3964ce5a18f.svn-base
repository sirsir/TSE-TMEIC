package jp.co.tmeic.mespd.service;



import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DLatestQualityBarcode;

@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DLatestQualityBarcodeService extends AbstractService<DLatestQualityBarcode>{
	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param barcodeNo
	 *            識別子
	 * @return エンティティ
	 */
	public DLatestQualityBarcode findById(String barcodeNo) {
		return select().id(barcodeNo).getSingleResult();
	}
	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @return エンティティ
	 */
	public String selectBarcode() {
		return jdbcManager
				.selectBySql(
						String.class,
						"SELECT barcode_no FROM d_latest_quality_barcode")
				.getSingleResult();
	}
	/**
	 * 挿入到着結果列。
	 *
	 * @param barcode
	 * @param oldBarcode
	 * @return 削除件数
	 */
	public int updateBarcode(String barcode,String oldBarcode) {
		return jdbcManager
				.updateBySql("UPDATE d_latest_quality_barcode SET barcode_no=? WHERE barcode_no=? ;", String.class, String.class)
				.params(barcode,oldBarcode)
				.execute();
	}
	/**
	 * 挿入到着結果列。
	 *
	 * @param barcode
	 * @return 削除件数
	 */
	public int insertBarcode(String barcode) {
		return jdbcManager
				.updateBySql("INSERT INTO d_latest_quality_barcode values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)", String.class)
				.params(barcode)
				.execute();
	}
	
}

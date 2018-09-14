package jp.co.tmeic.mespd.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;
import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;

import jp.co.tmeic.mespd.dto.jqgrid.product.VDBarcodeAssociationDbDto;
import jp.co.tmeic.mespd.entity.DBarcodeLabelManagement;

@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class VDBarcodeAssociationService  {
	
	@Resource
	protected JdbcManager jdbcManager;
	
	public VDBarcodeAssociationDbDto findByBarcode(String barcodeNo, String productPlanNo) {

		Map<String, Object> param = new HashMap<>();
		param.put("barcodeNo", barcodeNo);
		param.put("productPlanNo", productPlanNo);
		
		return jdbcManager.selectBySqlFile(
				VDBarcodeAssociationDbDto.class,
				"/jp/co/tmeic/mespd/service/VDBarcodeAssociation_findSerialNo.sql",
				param)
				.getSingleResult();
	}
	public VDBarcodeAssociationDbDto findByProductPlanNoAndSerialNo(String productPlanNo, String serialNo) {
		return jdbcManager
				.selectBySql(
						VDBarcodeAssociationDbDto.class,
						"SELECT * FROM v_d_barcode_association WHERE product_plan_no=? AND serial_no=?",
						productPlanNo, serialNo)
				.getSingleResult();
	}
	public VDBarcodeAssociationDbDto findProductPlanByBarcodeOnly(String barcodeNo) {

		return jdbcManager
				.selectBySql(
						VDBarcodeAssociationDbDto.class,
						"SELECT max(product_plan_no) FROM v_d_barcode_association WHERE barcode_no=?",
						barcodeNo)
				.getSingleResult();
	}
	
	
}
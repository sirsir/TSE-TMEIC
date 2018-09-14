package jp.co.tmeic.mespd.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MPrinter;
import static jp.co.tmeic.mespd.entity.MPrinterNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MPrinter}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class MPrinterService extends AbstractService<MPrinter> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param printerId
     *            識別子
     * @return エンティティ
     */
    public MPrinter findById(String printerId) {
        return select().id(printerId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MPrinter> findAllOrderById() {
        return select().orderBy(asc(printerId())).getResultList();
    }
    
    /**
     * 品質ラベルをプリントしなさい
     * 
     * @return エンティティのリスト
     */
    public MPrinter printQualityLabel() {
        return jdbcManager
				.selectBySql(
						MPrinter.class,
						"SELECT * FROM m_printer WHERE printer_type='1' AND default_use=TRUE limit 1")
				.getSingleResult();
    }
    
    public List<MPrinter> printShippingTicket() {
        return jdbcManager
				.selectBySql(
						MPrinter.class,
						"SELECT * FROM m_printer WHERE printer_type='2' AND default_use=TRUE")
				.getResultList();
    }
    
    public List<Map<String, Object>> barcodePrinter(){
    	List<Map<String, Object>> fields = new ArrayList<>();
    	MPrinter mPrinter = printQualityLabel();
    	Map<String, Object> field = printerFieldData(mPrinter);
		fields.add(field);

		return fields;
    	
    }
    
    public List<Map<String, Object>> shippingPrinter(){
    	List<Map<String, Object>> fields = new ArrayList<>();
    	List<MPrinter> mPrinters = printShippingTicket();
		for (MPrinter mPrinter : mPrinters) {
			Map<String, Object> field = printerFieldData(mPrinter);
			fields.add(field);
		}

		return fields;
    	
    }
    
    private Map<String, Object> printerFieldData(MPrinter mPrinter) {

    	Map<String, Object> field = new LinkedHashMap<>();

		field.put("printerId", mPrinter.printerId);
		field.put("printerName", mPrinter.printerName);

		return field;
	}
    
}
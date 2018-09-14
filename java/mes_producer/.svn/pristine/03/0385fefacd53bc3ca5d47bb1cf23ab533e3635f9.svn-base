package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DQualityLabelPrintQueueNames.printerId;
import static jp.co.tmeic.mespd.entity.DQualityLabelPrintQueueNames.sequenceNo;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DQualityLabelPrintQueue;

/**
 * {@link MPrinter}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class DQualtyLabelPrintQueueService extends AbstractService<DQualityLabelPrintQueue> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param printerId
     * @param sequenceNo
     *            識別子
     * @return エンティティ
     */
    public DQualityLabelPrintQueue findById(String printerId,long sequenceNo) {
        return select().id(printerId,sequenceNo).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<DQualityLabelPrintQueue> findAllOrderById() {
        return select().orderBy(asc(printerId()), asc(sequenceNo())).getResultList();
    }
    
    public int addQueueQualityLabel(String printerId,String printerType,String qcBarcodeNo,String barcodeNo){
    	return jdbcManager
    			/*create_date timestamp with time zone NOT NULL, -- 追加日
    			  update_date timestamp with time zone NOT NULL, -- 更新日
    			  printer_id character varying(10) NOT NULL, -- プリンタID
    			  sequence_no bigint NOT NULL, -- 印刷順
    			  print_type character varying(10) NOT NULL, -- 印刷種別
    			  qc_barcode_no character varying(6) NOT NULL, -- 品質ラベルバーコードNo
    			  barcode_no character varying(6) NOT NULL, -- バーコードNo*/
    			  .updateBySql("INSERT INTO d_quality_label_print_queue (create_date,update_date,printer_id,print_type,qc_barcode_no,barcode_no) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?,?,?)", String.class, String.class, String.class, String.class)
				//.updateBySql("INSERT INTO d_quality_label_print_queue values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,(select extract(epoch from date_trunc('milliseconds', CURRENT_TIMESTAMP)) * 1000),?,?,?)", String.class, String.class, String.class, String.class)
				.params(printerId,printerType,qcBarcodeNo,barcodeNo)
				.execute();
    	
    }
}
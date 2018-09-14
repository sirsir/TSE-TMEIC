package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DShippingTicketPrintQueueNames.printerId;
import static jp.co.tmeic.mespd.entity.DShippingTicketPrintQueueNames.sequenceNo;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.dto.jqgrid.product.DShippingDbDto;
import jp.co.tmeic.mespd.entity.DShippingTicketPrintQueue;

/**
 * {@link MPrinter}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class DShippingTicketPrintQueueService extends AbstractService<DShippingTicketPrintQueue> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param printerId
     * @param sequenceNo
     *            識別子
     * @return エンティティ
     */
    public DShippingTicketPrintQueue findById(String printerId,long sequenceNo) {
        return select().id(printerId,sequenceNo).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<DShippingTicketPrintQueue> findAllOrderById() {
        return select().orderBy(asc(printerId()), asc(sequenceNo())).getResultList();
    }
    
    public int addQueueShippingTicket(String printerId,String printType,String customerName,String partNo,String partName,String model,String mfgDate,String planCode,
    		String lotNo,String timeOfDelivery,String checker,String packingDate,String color,String serialNo,String quantity,String location,String qualityRank){
    	
    	Map<String, Object> param = new HashMap<>();
		param.put("printerId", printerId);
		param.put("printType", printType);
		param.put("customerName", customerName);
		param.put("partName", partName);
		param.put("partNo", partNo);
		param.put("lotNo", lotNo);
		param.put("mfgDate", changeMonth(mfgDate));
		param.put("planCode", planCode);
		param.put("timeOfDelivery", timeOfDelivery);
		param.put("checker", checker);
		param.put("packingDate", changeMonth(packingDate));
		param.put("model", model);
		param.put("color", color);
		param.put("serialNo", serialNo);
		param.put("quantity", quantity);
		param.put("location", location);
		param.put("qualityRank", qualityRank);

    	return jdbcManager
				.updateBySqlFile("/jp/co/tmeic/mespd/service/DShippingTicketPrintQueue_addQueueShippingTicket.sql", param)
				.execute();
    	
    }
    private String changeMonth(String date){
    	String newDate = "";
    	String[] d = date.split("/");
    	String month = d[1];
    	switch(d[1])
    	{
    	case "01": month = "Jan"; 
    		break;
    	case "02": month = "Feb"; 
		   break;
    	case "03": month = "Mar"; 
		   break;
    	case "04": month = "Apr"; 
		   break;
    	case "05": month = "May"; 
		   break;
    	case "06": month = "Jun"; 
		   break;
    	case "07": month = "Jul"; 
		   break;
    	case "08": month = "Aug"; 
		   break;
    	case "09": month = "Sep"; 
		   break;
    	case "10": month = "Oct"; 
		   break;
    	case "11": month = "Nov"; 
		   break;
    	case "12": month = "Dec"; 
		   break;
    	}
    	newDate = d[0]+" "+month+" "+d[2];
    	return newDate;
    }
}
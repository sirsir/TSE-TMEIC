package jp.co.tmeic.mespd.dto.report;

import java.util.Date;

public class ProductPlanMonthlyDbDto {

	/** 製品ID */
	public String productId;

	/** 製品名 */
	public String productName;

	/** 実績日 */
	public Date resultDate;

	/** 良否NG */
	public Integer quality;

}

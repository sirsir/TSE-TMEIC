package jp.co.tmeic.mespd.dto.report;

import java.util.Date;

public class MaterialPlanMonthlyDbDto {

	/** 部材ID */
	public String materialId;

	/** 部材名 */
	public String materialName;

	/** 部材単位 */
	public String materialUnit;

	/** 実績日 */
	public Date resultDate;

	/** 部材使用数 */
	public Integer materialQty = 0;

}

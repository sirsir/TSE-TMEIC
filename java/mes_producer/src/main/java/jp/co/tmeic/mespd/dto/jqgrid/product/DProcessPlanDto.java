package jp.co.tmeic.mespd.dto.jqgrid.product;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

/**
 * 工程計画
 *
 */
public class DProcessPlanDto extends JqgridDto {

	/** 対象 */
	public String target;

	/** 製造計画No */
	public String productPlanNo;

	/** 工程構成No */
	public String processComponentNo;

	/** 工程名 */
	public String processName;

}

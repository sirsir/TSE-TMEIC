package jp.co.tmeic.mespd.dto.jqgrid.product;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

/**
 * 仕様実績
 *
 */
public class DSpecResultDto extends JqgridDto {

	/** 仕様構成No */
	public String specComponentNo;

	/** 仕様計画 */
	public DSpecPlanDto dSpecPlan;

	/** 入力値 */
	public String inputValue;

}

package jp.co.tmeic.mespd.dto.jqgrid.product;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

/**
 * 到着
 *
 */
public class DArrivalDto extends JqgridDto {

	/** 到着No */
	public String arrivalNo;

	/** 到着の日付 */
	public String arrivalDate;

	/** 部分的な数 */
	public String partNo;

	/** 部分的な名前 */
	public String partName;

	/** 顧客名 */
	public String customerName;

	/** モデル */
	public String model;
	
	/** アイテムNo */
	public String itemNo;

	/** 予定数量 */
	public String planQty;

	/** 結果量 */
	public String resultQty;
}
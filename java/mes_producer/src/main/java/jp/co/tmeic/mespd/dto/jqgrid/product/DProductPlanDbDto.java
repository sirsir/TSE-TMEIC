package jp.co.tmeic.mespd.dto.jqgrid.product;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 製造計画
 *
 */
public class DProductPlanDbDto {

	/** 製造計画No */
	public String productPlanNo;

	/** 製造日 */
	public Date manufactureDate;

	/** 部分的な数 */
	public String partNo;

	/** 部分的な名前 */
	public String partName;
	
	/** 顧客名 */
	public String customerName;
	
	/** モデル*/
	public String model;

	/** 製品種別 */
	public Integer productKind;

	/** 予定数量 */
	public Integer planQty;

	/** 実績数量 */
	public Integer resultQty;

	/** 開始予定日時 */
	public Timestamp planStartDate;

	/** 終了予定日時 */
	public Timestamp planEndDate;

	/** 開始時刻 */
	public Timestamp startDatetime;

	/** 終了時刻 */
	public Timestamp endDatetime;

	/** 状態 */
	public Integer status;

}
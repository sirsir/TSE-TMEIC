package jp.co.tmeic.mespd.dto.jqgrid.product;

import java.text.ParseException;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 製造計画登録
 *
 */
public class DProductPlanDto extends JqgridDto {

	/** 製造計画No */
	public String productPlanNo;

	/** 製造日 */
	public String manufactureDate;

	/** 部分的な数 */
	public String partNo;

	/** 部分的な名前 */
	public String partName;
	
	/** 顧客名 */
	public String customerName;
	
	/** モデル*/
	public String model;

	/** 製品種別 */
	public String productKind;

	/** 予定数量 */
	public String planQty;

	/** 実績数量 */
	public String resultQty;

	/** 開始予定日時 */
	public String planStartDate;

	/** 開始予定時刻 */
	public String planStartTime;

	/** 終了予定日時 */
	public String planEndDate;

	/** 開始時刻 */
	public String startDatetime;

	/** 終了時刻 */
	public String endDatetime;

	/** 状態 */
	public String status;

	/** 開始予定時刻 */
	public String getPlanStartTime() throws ParseException {

		if (StringUtils.isBlank(planStartDate)) {
			return StringUtils.EMPTY;
		}
		return DateFormatUtils.format(DateUtils.parseDate(planStartDate, "dd/MM/yyyy HH:mm:ss"), "HHmm");
	}

	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
}

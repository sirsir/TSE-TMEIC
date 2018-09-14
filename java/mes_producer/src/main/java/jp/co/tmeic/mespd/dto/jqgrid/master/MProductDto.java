package jp.co.tmeic.mespd.dto.jqgrid.master;

import java.util.List;

import javax.persistence.Column;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

/**
 * 製品マスタ
 *
 */
public class MProductDto extends JqgridDto {

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
	
	/** 通過設備(EP6/EP9) */
	public String platingMachine;
	
	/** 製品種別 */
	public String productKind;

	/** 工程 */
	public List<MProcessComponentDto> process;
}

package jp.co.tmeic.mespd.dto.jqgrid.master;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

/**
 * 工程単位仕様構成マスタ
 *
 */
public class MSpecProcessComponentDto extends JqgridDto {

	/** 部品No */
	public String partNo;

	/** 工程構成No */
	public String processComponentNo;

	/** 仕様構成No */
	public String specComponentNo;

	/** 仕様ID */
	public String specId;

	/** 仕様表示順 */
	public String displayOrder;
}
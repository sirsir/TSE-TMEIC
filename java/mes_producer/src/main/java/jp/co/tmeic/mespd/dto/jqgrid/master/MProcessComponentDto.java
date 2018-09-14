package jp.co.tmeic.mespd.dto.jqgrid.master;

import java.util.ArrayList;
import java.util.List;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

/**
 * 工程構成マスタ
 *
 */
public class MProcessComponentDto extends JqgridDto {

	/** 部品No */
	public String partNo;

	/** 工程構成_no */
	public String processComponentNo;

	/** 工程ID */
	public String processId;

	/** 工程内容 */
	public String processContents;

	/** 標準時間(sec) */
	public String processTime;

	/** 標準人員 */
	public String personnelRequired;

	/** 並列作業数 */
	public String parallelWork;

	 /** ユニットサイズ */
	public String unitSize;

	/** 工程順 */
	public Integer processOrder;

	/** 製品単位仕様構成マスタ */
	public List<MSpecProductComponentDto> specProducts = new ArrayList<>();

	/** 工程単位仕様構成マスタ */
	public List<MSpecProcessComponentDto> specProcesses = new ArrayList<>();

	/** 部材構成マスタ */
	public List<MMaterialComponentDto> materials = new ArrayList<>();
}

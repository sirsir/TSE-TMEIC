/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.tmeic.mespd.form.product;

/** 製造計画実績 */
public class ProductResultInputForm {

	/** 製造計画No */
	public String productPlanNo;

	/** 工程構成No */
	public String processComponentNo;
	
	/** シリアルNo */
	public String serialNo;

	/** 実績登録回数 */
	public String revision;

	/** ステータスID */
	public String statusId;

	/** 製造計画 */
	public String productJson;
	
	/** 名前プリンタ */
	public String printersJson;
	
	/** 名前プリンタ */
	public String printerShippingsJson;
	
	/** 工程実績 */
	public String processResultJson;

	/** 工程内製品単位実績 */
	public String processProductResultJson;

	/** 工程単位部材実績数 */
	public String processMaterialSize;

	/** 工程単位仕様実績数 */
	public String processSpecSize;

	/** 部材最大数 */
	public String processMaterialPlanMax;

	/** 製品単位仕様最大数 */
	public String processProductSpecPlanMax;

	/** シリアルNo最大桁数 */
	public int serialnoMaxLength;
	
	/** カラムのバーコード最大数   */
	public int barcodeMaxLength;

	/** 数量最大桁数 */
	public int qtyMaxLength;

	/** 部材数量最大桁数 */
	public int materialQuantityMaxLength;

	/** 仕様入力値最大桁数 */
	public int specInputvalueMaxLength;

	/** 工程実績グリッド 選択行 */
	public String selectRowOfProcessGrid;

	/** 工程ステータス一覧 */
	public String processStatus;

	/** 製品ステータス一覧 */
	public String processProductStatus;

	/** 呼出し元 表示開始日（製造日） */
	public String callerDisplayStartDate;

	/** 呼出し元 表示終了日（製造日） */
	public String callerDisplayEndDate;
	
	/** パッキング日付 */
	public String packingDate;
	
	/** チェッカー*/
	public String checker;
}

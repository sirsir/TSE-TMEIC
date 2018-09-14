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

import java.util.ArrayList;
import java.util.List;

import jp.co.tmeic.mespd.entity.MProduct;

import org.apache.commons.lang3.StringUtils;

/** 出荷の登録 */
public class ShippingForm {

	/** 出荷計画 */
	public String shippingJson;

	/** 表示開始日（製造日） */
	public String displayStartDate;

	/** 表示終了日（製造日） */
	public String displayEndDate;

	/** 製品マスタ */
	public List<MProduct> mProducts = new ArrayList<>();

	/** ステータス一覧 */
	public String productStatus;

	/** 予定数量最大桁数 */
	public String planQtyMaxLength;

	/** 出荷不請求書システム */
	public String shippingNoNumberingMethod;

	/** 製品リスト */
	public String getProductList() {

		List<String> list = new ArrayList<>();

		list.add(":");

		for (MProduct mProduct : mProducts) {
			list.add(String.format("%s:%s", mProduct.partNo, mProduct.partName));
		}

		return StringUtils.join(list, ";");
	}
	
	/** 製品詳細 */
	public String getProductDetail() {
		List<String> list = new ArrayList<>();

		for (MProduct mProduct : mProducts) {
			list.add(String.format("%s\t%s\t%s\t%s\t%s", mProduct.partNo, mProduct.partName, mProduct.customerName, mProduct.model, mProduct.itemNo));  //list.add(String.format("%s:%s", mProduct.productId, mProduct.productName));
		}

		return StringUtils.join(list, "_n-");
	}
}
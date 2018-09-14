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

import org.apache.commons.lang3.StringUtils;

/** 進捗状況 */
public abstract class AbstractProductProgressForm {

	/** 進捗状況自動更新周期 */
	public String interval;

	/** 自動更新状態 */
	public String isAutoUpdating;

	/** 進捗状況 */
	public String progressJson;

	/** 表示開始日（製造日） */
	public String displayStartDate;

	/** 表示終了日（製造日） */
	public String displayEndDate;

	/** ガントチャート開始日 */
	public String displayGanttStartDate;

	/** ガントチャート終了日 */
	public String displayGanttEndDate;

	/** 選択製造計画No */
	public String selectProductPlanNo;

	/** ページNo */
	public String page;

	/** ページ総数 */
	public String pageTotalCount;

	/** 自動更新状態 */
	public boolean isAutoUpdate() {
		return StringUtils.equals(isAutoUpdating, "1");
	}
}

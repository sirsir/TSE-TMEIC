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
package jp.co.tmeic.mespd.action.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.tmeic.mespd.action.AbstractAction;
import jp.co.tmeic.mespd.constant.ProgressSeriesType;
import jp.co.tmeic.mespd.dto.jqgrid.product.ProductProgressDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.ProductProgressSeriesDto;
import jp.co.tmeic.mespd.form.product.AbstractProductProgressForm;
import jp.co.tmeic.mespd.utils.BrowserMsg;
import jp.co.tmeic.mespd.utils.DateUtil;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;

import org.apache.commons.lang3.time.DateUtils;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth
public abstract class AbstractProductProgressAction extends AbstractAction {

	/** 初期ページ */
	protected static final int INIT_PAGE = 1;

	/**
	 * 製造計画の進捗データ(計画、実績、実績時間)を作成します。
	 *
	 * @param progress
	 * @return 製造計画の進捗データ(計画、実績、実績時間)
	 */
	protected ProductProgressSeriesDto[] createSeries(ProductProgressDto progress) {

		ProductProgressSeriesDto[] serieses = new ProductProgressSeriesDto[3];

		serieses[0] = createProgressPlan(
				progress.id + "1", progress.planStartDate, progress.planEndDate, progress.barText);
		
		serieses[1] = createProgressResult(
				progress.id + "2", progress.planStartDate, progress.planEndDate, progress.planQty, progress.resultQty);
		
		serieses[2] = createProgressResultTime(
				progress.id + "3", progress.startDatetime, progress.endDatetime);
		
		return serieses;
	}

	/**
	 * 製造計画の進捗データを作成します。
	 *
	 * @param id
	 * @param planStart
	 * @param planEnd
	 * @param text
	 * @return 製造計画の進捗データ
	 */
	protected ProductProgressSeriesDto createProgressPlan(String id, Date planStart, Date planEnd, String text) {

		ProductProgressSeriesDto series = new ProductProgressSeriesDto();

		series.id = id;
		series.name = ProgressSeriesType.getName(locale(), ProgressSeriesType.PLAN);
		series.color = ProgressSeriesType.getColor(ProgressSeriesType.PLAN);
		series.startDate = planStart;
		series.endDate = planEnd;
		series.text = text;

		return series;
	}

	/**
	 * 製造実績の進捗データを作成します。
	 *
	 * @param id
	 * @param planStart
	 * @param planEnd
	 * @param planQty
	 * @param resultQty
	 * @return 製造実績の進捗データ
	 */
	protected ProductProgressSeriesDto createProgressResult(String id, Date planStart, Date planEnd, Integer planQty, Integer resultQty) {

		ProductProgressSeriesDto series = new ProductProgressSeriesDto();

		series.id = id;
		series.name = ProgressSeriesType.getName(locale(), ProgressSeriesType.RESULT);
		series.color = ProgressSeriesType.getColor(ProgressSeriesType.RESULT);

		// 実績数の実績開始・終了日時設定
		if (resultQty == null) {
			return series;
		}

		// 予定時間
		long planTime = planEnd.getTime() - planStart.getTime();
		// 実績数の実績終了時間
		long planTimeOfPlanQty = (planTime / planQty) * resultQty;
		// 実績数の実績終了時間
		long resultTime = planStart.getTime() + planTimeOfPlanQty;

		series.startDate = planStart;
		series.endDate = new Date(resultTime);
		series.text = String.format("%d / %d", resultQty, planQty);

		return series;
	}

	/**
	 * 製造実績時間の進捗データを作成します。
	 *
	 * @param id
	 * @param resultFirst
	 * @param resultLast
	 * @return 製造実績時間の進捗データ
	 */
	protected ProductProgressSeriesDto createProgressResultTime(String id, Date resultFirst, Date resultLast) {

		ProductProgressSeriesDto series = new ProductProgressSeriesDto();

		series.id = id;
		series.name = ProgressSeriesType.getName(locale(), ProgressSeriesType.RESULT_TIME);
		series.color = ProgressSeriesType.getColor(ProgressSeriesType.RESULT_TIME);

		if (resultFirst == null || resultLast == null) {
			return series;
		}

		series.startDate = resultFirst;
		series.endDate = resultLast;

		return series;
	}

	/**
	 * 指定サイズ分の進捗表示データを作成します。
	 *
	 * @param size
	 * @return
	 */
	protected List<ProductProgressDto> sizeAdjustment(int size) {
		return sizeAdjustment(new ArrayList<ProductProgressDto>(), size);
	}

	/**
	 * 進捗表示データが指定サイズになるまで、空データを追加します。
	 *
	 * @param progresses
	 * @param size
	 * @return
	 */
	protected List<ProductProgressDto> sizeAdjustment(List<ProductProgressDto> progresses, int size) {

		while (progresses.size() < size) {

			ProductProgressDto productProgressDto = new ProductProgressDto();
			productProgressDto.series = createSeries(new ProductProgressDto());

			progresses.add(productProgressDto);
		}

		return progresses;
	}

	/**
	 * 進捗状況自動更新有無を取得します。
	 *
	 * @return 進捗状況自動更新有無(0:無 1:有)
	 */
	protected int getAutoUpdating() {

		if (SystemParameterUtil.progressAutoupdate()) {
			return 1;
		}

		return 0;
	}

	/**
	 * ページNoのエラーかどうか確認します。
	 *
	 * @param page
	 *            ページNo
	 * @param dataCount
	 *            ページ総数
	 * @return エラーかどうか
	 */
	protected boolean isErrorPage(int page, int dataCount) {

		if (page <= 0) {

			BrowserMsg.toAlert(
					MessageResourcesUtil.getMessage(
							locale(),
							"errors.out.of.range",
							MessageResourcesUtil.getMessage(locale(), "page")));

			return true;
		}

		int end = page * 10;
		int startIndex = end - 10;

		// 開始がページ総数超え
		if (startIndex >= dataCount) {

			BrowserMsg.toAlert(
					MessageResourcesUtil.getMessage(
							locale(),
							"errors.out.of.range",
							MessageResourcesUtil.getMessage(locale(), "page")));

			return true;
		}

		return false;
	}

	/**
	 * フォームのガントページデータをクリアします。
	 *
	 * @param form
	 *            フォーム
	 */
	protected void clearFormOfGanntPageData(AbstractProductProgressForm form) {
		form.page = "1";
		form.pageTotalCount = "0";
	}

	/**
	 * ページ総数を取得します。
	 *
	 * @param dataCount
	 *            データ総数
	 * @return ページ総数
	 */
	protected int getPageTotalCount(int dataCount) {

		int pageTotalCount = dataCount / 10;

		if ((dataCount % 10) != 0) {
			pageTotalCount = pageTotalCount + 1;
		}

		return pageTotalCount;
	}

	/**
	 * ページの開始Indexを取得します。
	 *
	 * @param page
	 *            ページNo
	 * @return ページ開始Index
	 */
	protected int getPageStartIndex(int page) {
		return (page * 10) - 10;
	}

	/**
	 * ページの最終サイズを取得します。
	 *
	 * @param page
	 *            ページNo
	 * @param dataSize
	 * @return ページの最終サイズ
	 */
	protected int getPageEndSize(int page, int dataSize) {

		int endSize = page * 10;

		if (dataSize >= endSize) {
			return endSize;
		}

		return dataSize;
	}

	/**
	 * 制限日を生成します。
	 *
	 * @param displayGanttStartDate
	 *            ガントチャート開始日時
	 * @return 制限日
	 */
	protected Date createLimitingDate(Date displayGanttStartDate) {
		Date limitingDate = DateUtils.addDays(displayGanttStartDate, SystemParameterUtil.progressGanttDisplayLimitDays());
		limitingDate = DateUtil.setTime(limitingDate, 0, 0, 0);

		return DateUtils.addSeconds(limitingDate, -1);
	}

	/**
	 * 制限の終了日を生成します。
	 * @param displayGanttStartDate
	 * @return
	 */
	protected Date createLimitEndDate(Date displayGanttStartDate) {
		Date  endDate = createLimitingDate(displayGanttStartDate);

		return DateUtil.setTime(endDate, 23, 0, 0);
	}

	/**
	 * 表示可能な日数を超えているかチェックします。
	 *
	 * @param displayGanttStartDate
	 *            ガントチャート開始日時
	 * @param displayGanttEndDate
	 *            ガントチャート終了日時
	 * @return 超えているかどうか
	 */
	protected boolean isOverLimitDays(Date displayGanttStartDate, Date displayGanttEndDate) {
		Date checkEndDate = createLimitingDate(displayGanttStartDate);

		if (DateUtil.gt(displayGanttEndDate, checkEndDate)) {
			return true;
		}

		return false;
	}

	/**
	 * 表示可能な日数を超えているデータを削除します。
	 *
	 * @param displayGanttStartDate
	 *            ガントチャート開始日時
	 * @param productProgresss
	 *            進捗状況リスト
	 * @return 削除後進捗状況リスト
	 */
	protected List<ProductProgressDto> deleteProgressOfOverLimitDays(Date displayGanttStartDate, List<ProductProgressDto> productProgresss) {
		Date checkEndDate = createLimitingDate(displayGanttStartDate);

		List<ProductProgressDto> resultProgress = new ArrayList<>();

		for (ProductProgressDto productProgress : productProgresss) {

			productProgress.series[0] = deleteSeriesOfOverLimitDays(productProgress.series[0], checkEndDate);
			productProgress.series[1] = deleteSeriesOfOverLimitDays(productProgress.series[1], checkEndDate);
			productProgress.series[2] = deleteSeriesOfOverLimitDays(productProgress.series[2], checkEndDate);

			resultProgress.add(productProgress);
		}

		return resultProgress;
	}

	/**
	 * 表示可能な日数を超えているSeriesのデータを削除します。
	 *
	 * @param series シリーズ
	 * @param checkDate チェック日
	 * @return 削除後シリーズ
	 */
	private ProductProgressSeriesDto deleteSeriesOfOverLimitDays(ProductProgressSeriesDto series, Date checkDate) {

		ProductProgressSeriesDto resultSeries = series;

		if (series.startDate == null) {
			return resultSeries;
		}

		if (DateUtil.gt(series.startDate, checkDate)) {
			resultSeries.startDate = null;
			resultSeries.endDate = null;
			return resultSeries;
		}

		if (DateUtil.lt(checkDate, series.endDate)) {

			resultSeries.endDate = checkDate;
		}

		return resultSeries;
	}
}

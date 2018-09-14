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

import javax.annotation.Resource;

import jp.co.tmeic.mespd.dto.DateRrangeDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.ProductProgressDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.ProductProgressSeriesDto;
import jp.co.tmeic.mespd.entity.DProcessPlan;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.entity.DProductPlan;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.product.ProductProgressProcessForm;
import jp.co.tmeic.mespd.service.DProcessPlanService;
import jp.co.tmeic.mespd.service.DProductPlanService;
import jp.co.tmeic.mespd.service.DProductResultService;
import jp.co.tmeic.mespd.service.ProductPlanService;
import jp.co.tmeic.mespd.utils.BrowserMsg;
import jp.co.tmeic.mespd.utils.DateUtil;
import jp.co.tmeic.mespd.utils.JSONUtil;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.Beans;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth
public class ProductProgressProcessAction extends AbstractProductProgressAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	@Resource
	protected JdbcManager jdbcManager;

	/** ActionForm */
	@Resource
	@ActionForm
	protected ProductProgressProcessForm productProgressProcessForm;

	@Resource
	protected DProductPlanService dProductPlanService;

	@Resource
	protected DProcessPlanService dProcessPlanService;

	@Resource
	protected DProductResultService dProductResultService;

	@Resource
	protected ProductPlanService productPlanService;

	/** 検索 */
	@Execute(validator = false)
	public String index() {

		ProductProgressProcessForm form = productProgressProcessForm;

		try {

			indexInitFrom(productProgressProcessForm);

			ValidateUtil.required("production.plan.no", form.selectProductPlanNo);

			DProductPlan dProductPlan = dProductPlanService.findById(form.selectProductPlanNo);

			if (dProductPlan == null) {
				return "index.jsp";
			}

			form.productName = productPlanService.getProductName(dProductPlan.productPlanNo);

			List<DProcessPlan> dProcessPlans = dProcessPlanService.findProcessesByProductPlanNo(form.selectProductPlanNo);

			if (dProcessPlans.isEmpty()) {
				clearFormOfGanntPageData(form);
				return "index.jsp";
			}

			if (isErrorPage(Integer.parseInt(form.page), dProcessPlans.size())) {
				form.page = String.valueOf(INIT_PAGE);
			}

			// ページ総数設定
			form.pageTotalCount = String.valueOf(getPageTotalCount(dProcessPlans.size()));

			int page = NumberUtils.toInt(form.page, 1);
			List<ProductProgressDto> progressProcessesOfPage = createProgressProcessesOfTargetPage(page, dProcessPlans);

			DateRrangeDto displayDate = getDisplayGanttDate(progressProcessesOfPage);

			Date displayStartDate = (displayDate.start != null ? displayDate.start : DateUtil.toDate(form.displayGanttStartDate));
			Date displayEndDate = (displayDate.end != null ? displayDate.end : DateUtil.toDate(form.displayGanttEndDate));

			// 表示可能な限界の日数チェック
			if (isOverLimitDays(displayStartDate, displayEndDate)) {
				displayEndDate = createLimitEndDate(displayStartDate);
				progressProcessesOfPage = deleteProgressOfOverLimitDays(displayStartDate, progressProcessesOfPage);
				BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), "errors.over.range.display.graph.days"));
			}

			form.displayGanttStartDate = DateFormatUtils.format(displayStartDate, "yyyy/MM/dd HH:mm:ss");

			Date endDate = DateUtil.setTime(displayEndDate, 23, 0);
			form.displayGanttEndDate = DateFormatUtils.format(endDate, "yyyy/MM/dd HH:mm:ss");

			progressProcessesOfPage = sizeAdjustment(progressProcessesOfPage, 10);

			form.progressJson = JSONUtil.encode(progressProcessesOfPage);

		} catch (ValidationException ex) {
			logger.warn(ex.getMessage());
			form.displayGanttStartDate = StringUtils.EMPTY;
			form.displayGanttEndDate = StringUtils.EMPTY;
			clearFormOfGanntPageData(form);

			BrowserMsg.toAlert(ex.getMessage());

		} catch (Exception ex) {
			logger.error("Exception", ex);
		}

		return "index.jsp";
	}

	/**
	 * フォーム初期化
	 *
	 * @param form
	 */
	private void indexInitFrom(ProductProgressProcessForm form) {

		// 初期ページ番号
		if (StringUtils.isBlank(form.page)) {
			form.page = String.valueOf(INIT_PAGE);
		}

		// 自動更新状態
		if (StringUtils.isBlank(form.isAutoUpdating)) {
			form.isAutoUpdating = String.valueOf(getAutoUpdating());
		}

		form.progressJson = "[]";

		// 表示自動周期
		form.interval = String.valueOf(SystemParameterUtil.progressUpdateInterval());
	}

	/**
	 * 該当ページの工程データを生成します。
	 *
	 * @param page
	 *            ページNo
	 * @param dProcessPlans
	 *            工程リスト
	 * @return 工程
	 */
	private List<ProductProgressDto> createProgressProcessesOfTargetPage(int page, List<DProcessPlan> dProcessPlans) {

		int startIndex = getPageStartIndex(page);

		if (dProcessPlans.size() <= startIndex) {
			return new ArrayList<>();
		}

		int endSize = getPageEndSize(page, dProcessPlans.size());

		List<DProcessPlan> progressProcessesOfPage = dProcessPlans.subList(startIndex, endSize);
		return createProductProgressProcessDto(progressProcessesOfPage);
	}

	/**
	 * 進捗状況表示データを生成します。
	 *
	 * @param dProcessPlans
	 *            工程リスト
	 * @return 進捗状況表示データ
	 */
	private List<ProductProgressDto> createProductProgressProcessDto(List<DProcessPlan> dProcessPlans) {

		List<ProductProgressDto> productProgressProcesses = new ArrayList<>();

		for (DProcessPlan processPlan : dProcessPlans) {

			ProductProgressDto productProgress = Beans.createAndCopy(ProductProgressDto.class, processPlan).execute();

			productProgress.id = String.valueOf(processPlan.processComponentNo);
			productProgress.name = processPlan.processName;
			productProgress.barText = processPlan.processName;

			DProcessResult dProcessResult = processPlan.DProcessResult;

			if (dProcessResult != null) {
				productProgress.startDatetime = dProcessResult.startDatetime;
				productProgress.endDatetime = dProcessResult.productResultLastdate;
				productProgress.resultQty = dProcessResult.resultQty;
			}

			productProgress.series = createSeries(productProgress);

			productProgressProcesses.add(productProgress);
		}

		return productProgressProcesses;
	}

	/**
	 * ガントデータ表示開始・終了時刻を取得します。
	 *
	 * @param progresses
	 *            進捗状況表示データ
	 * @return 開始・終了時刻
	 */
	private DateRrangeDto getDisplayGanttDate(List<ProductProgressDto> progresses) {

		if (progresses.isEmpty()) {
			return new DateRrangeDto();
		}

		if (progresses.get(0).series == null) {
			return new DateRrangeDto();
		}

		Date startDate = progresses.get(0).series[0].startDate;
		Date endDate = progresses.get(0).series[0].endDate;

		for (ProductProgressDto progress : progresses) {

			ProductProgressSeriesDto[] series = progress.series;

			if (series == null) {
				continue;
			}

			for (ProductProgressSeriesDto seriesDto : series) {

				if (seriesDto.startDate == null && seriesDto.endDate == null) {
					continue;
				}

				if (seriesDto.startDate != null && startDate.after(seriesDto.startDate)) {
					startDate = seriesDto.startDate;
				}

				if (seriesDto.startDate != null && endDate.before(seriesDto.startDate)) {
					endDate = seriesDto.startDate;
				}

				if (seriesDto.endDate != null && endDate.before(seriesDto.endDate)) {
					endDate = seriesDto.endDate;
				}
			}
		}

		Date defaultStartDate = DateUtil.setTime(startDate, SystemParameterUtil.progressGanttStartingPointTime());

		if (DateUtil.lt(defaultStartDate, startDate)) {
			startDate = defaultStartDate;
		}

		DateRrangeDto displayDate = new DateRrangeDto();

		if (startDate != null) {
			startDate = DateUtils.setMinutes(startDate, 0);
			startDate = DateUtils.setSeconds(startDate, 0);
			displayDate.start = startDate;
		}

		if (endDate != null) {
			displayDate.end = DateUtil.setTime(endDate, 23, 0);
		}

		return displayDate;
	}
}

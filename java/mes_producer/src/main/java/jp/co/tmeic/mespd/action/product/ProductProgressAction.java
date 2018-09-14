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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.dto.jqgrid.product.DProductPlanDbDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.ProductProgressDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.ProductProgressSeriesDto;
import jp.co.tmeic.mespd.entity.DProductResult;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.product.ProductProgressForm;
import jp.co.tmeic.mespd.service.DProcessPlanService;
import jp.co.tmeic.mespd.service.DProcessResultService;
import jp.co.tmeic.mespd.service.DProductPlanService;
import jp.co.tmeic.mespd.service.DProductResultService;
import jp.co.tmeic.mespd.service.ProductResultService;
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
import org.seasar.framework.beans.util.Beans;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth
public class ProductProgressAction extends AbstractProductProgressAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** ActionForm */
	@Resource
	@ActionForm
	protected ProductProgressForm productProgressForm;

	@Resource
	protected DProductPlanService dProductPlanService;

	@Resource
	protected DProductResultService dProductResultService;

	@Resource
	protected DProcessPlanService dProcessPlanService;

	@Resource
	protected DProcessResultService dProcessResultService;

	@Resource
	protected ProductResultService productResultService;

	/** 検索 */
	@Execute(validator = false)
	public String index() {

		try {

			indexInitFrom(productProgressForm);
			indexValidate(productProgressForm);

			// 始点時刻
			Time ganttStartingPointTime = SystemParameterUtil.progressGanttStartingPointTime();

			Date displayStartDate = DateUtil.toDate(productProgressForm.displayStartDate);
			Date displayEndDate = DateUtil.toDate(productProgressForm.displayEndDate);
			
			Date ganttStartDate = DateUtil.setTime(displayStartDate, ganttStartingPointTime);
			Date ganttEndDate = DateUtil.setTime(displayEndDate, 23, 0);

			productProgressForm.displayGanttStartDate = DateFormatUtils.format(ganttStartDate, "yyyy/MM/dd HH:mm:ss");
			productProgressForm.displayGanttEndDate = DateFormatUtils.format(ganttEndDate, "yyyy/MM/dd HH:mm:ss");

			List<DProductPlanDbDto> productPlans = dProductPlanService.findByManufactureDate(displayStartDate, displayEndDate);

			if (productPlans.isEmpty()) {
				clearFormOfGanntPageData(productProgressForm);
				productProgressForm.progressJson = JSONUtil.encode(sizeAdjustment(10));
				return "index.jsp";
			}

			if (isErrorPage(Integer.parseInt(productProgressForm.page), productPlans.size())) {
				productProgressForm.page = String.valueOf(INIT_PAGE);
			}

			// ページ総数設定
			productProgressForm.pageTotalCount = String.valueOf(getPageTotalCount(productPlans.size()));

			int page = NumberUtils.toInt(productProgressForm.page, 1);
			List<ProductProgressDto> productProgressOfPage = createProductProgressOfTargetPage(page, productPlans);

			Date displayGanttStartDate = getDisplayGanttStartDate(productProgressOfPage, ganttStartDate);
			Date displayGanttEndDate = getDisplayGanttEndDate(productProgressOfPage, displayEndDate);

			// 表示可能な限界の日数チェック
			if (isOverLimitDays(displayGanttStartDate, displayGanttEndDate)) {
				displayGanttEndDate = createLimitEndDate(displayGanttStartDate);
				productProgressOfPage = deleteProgressOfOverLimitDays(displayGanttStartDate, productProgressOfPage);
				BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), "errors.over.range.display.graph.days"));
			}

			productProgressForm.displayGanttStartDate = DateFormatUtils.format(displayGanttStartDate, "yyyy/MM/dd HH:mm:ss");
			productProgressForm.displayGanttEndDate = DateFormatUtils.format(displayGanttEndDate, "yyyy/MM/dd HH:mm:ss");

			productProgressOfPage = sizeAdjustment(productProgressOfPage, 10);

			productProgressForm.progressJson = JSONUtil.encode(productProgressOfPage);

		} catch (ValidationException ex) {

			productProgressForm.displayGanttStartDate = StringUtils.EMPTY;
			productProgressForm.displayGanttEndDate = StringUtils.EMPTY;
			clearFormOfGanntPageData(productProgressForm);

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
	private void indexInitFrom(ProductProgressForm form) {

		final Date nowDate = DateUtil.now();

		// 初期ページ番号
		if (StringUtils.isBlank(form.page)) {
			form.page = String.valueOf(INIT_PAGE);
		}

		form.interval = String.valueOf(SystemParameterUtil.progressUpdateInterval());
		form.progressJson = "[]";

		// 自動更新状態
		if (StringUtils.isBlank(form.isAutoUpdating)) {
			form.isAutoUpdating = String.valueOf(getAutoUpdating());
		}

		// 自動更新の場合は開始時刻＝システム日付 終了時刻＝システム日付＋表示日数
		if (form.isAutoUpdate()) {

			form.displayStartDate = DateFormatUtils.format(nowDate, "dd/MM/yyyy");
			Date displayEndDate = DateUtils.addDays(nowDate, SystemParameterUtil.progressAutoupdateDisplayDates());
			form.displayEndDate = DateFormatUtils.format(displayEndDate, "dd/MM/yyyy");

		} else {
			// 初期日付
			if (StringUtils.isBlank(form.displayStartDate)) {
				form.displayStartDate = DateFormatUtils.format(nowDate, "dd/MM/yyyy");
			}

			if (StringUtils.isBlank(form.displayEndDate)) {
				form.displayEndDate = DateFormatUtils.format(nowDate, "dd/MM/yyyy");
			}
		}
	}

	/**
	 * 検索処理が可能か検証を行う。
	 *
	 * @param dProductPlanDtos
	 */
	private void indexValidate(ProductProgressForm form) {

		ValidateUtil.date("product.manifacturedate", form.displayStartDate);
		ValidateUtil.date("product.manifacturedate", form.displayEndDate);

		Date displayStartDate = DateUtil.toDate(form.displayStartDate);
		Date displayEndDate = DateUtil.toDate(form.displayEndDate);

		if (DateUtil.gt(displayStartDate, displayEndDate)) {
			throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.out.of.range",
					MessageResourcesUtil.getMessage(locale(), "product.manifacturedate")));
		}

		int displayDays = SystemParameterUtil.productDisplayDays();
		long days = DateUtil.days(displayStartDate, displayEndDate);

		ValidateUtil.displayLimitDays("product.manifacturedate", displayDays, days);

		ValidateUtil.integer("page", form.page);
	}

	/**
	 * @param page
	 * @param dProductPlans
	 * @return
	 */
	private List<ProductProgressDto> createProductProgressOfTargetPage(int page, List<DProductPlanDbDto> dProductPlans) {

		int startIndex = getPageStartIndex(page);

		if (dProductPlans.size() <= startIndex) {
			return new ArrayList<>();
		}

		int endSize = getPageEndSize(page, dProductPlans.size());

		List<DProductPlanDbDto> dProductPlanDbDtoOfPage = dProductPlans.subList(startIndex, endSize);
		return createProductProgress(dProductPlanDbDtoOfPage);
	}

	/**
	 * 進捗データを作成する。<br>
	 *
	 * @param productPlans
	 * @return
	 */
	private List<ProductProgressDto> createProductProgress(List<DProductPlanDbDto> productPlans) {

		if (productPlans.isEmpty()) {
			return new ArrayList<>();
		}

		List<ProductProgressDto> productProgresses = new ArrayList<>();

		for (DProductPlanDbDto productPlan : productPlans) {

			ProductProgressDto productProgress = Beans.createAndCopy(ProductProgressDto.class, productPlan).execute();

			productProgress.id = productProgress.productPlanNo;
			productProgress.name =
					String.format("%s :%s", MessageResourcesUtil.getMessage(locale(), "plan.no"), productProgress.productPlanNo);
			productProgress.subName = productPlan.partName;
			productProgress.barText = productPlan.productPlanNo;

			DProductResult dProductResult = dProductResultService.findById(productProgress.productPlanNo);

			if (dProductResult != null) {
				productProgress.startDatetime = dProductResult.startDatetime;
			}

			productProgress.resultQty = productResultService.resultQty(productProgress.productPlanNo);
			productProgress.endDatetime = productResultService.resultLastdate(productProgress.productPlanNo);

			productProgress.series = createSeries(productProgress);

			productProgresses.add(productProgress);
		}

		return productProgresses;
	}

	/**
	 * 進捗表示の初回日を取得する。
	 *
	 * @param productProgresses
	 *            進捗状況表示リスト
	 * @param displayStartDate
	 *            製造日検索開始時刻
	 * @return 進捗表示の初回日
	 */
	private Date getDisplayGanttStartDate(List<ProductProgressDto> productProgresses, Date displayStartDate) {

		Date startDate = displayStartDate;

		if (productProgresses.isEmpty()) {
			return startDate;
		}

		for (ProductProgressDto productProgress : productProgresses) {
			if (DateUtil.lt(productProgress.planStartDate, startDate)) {
				startDate = productProgress.planStartDate;
			}
		}

		startDate = DateUtils.setMinutes(startDate, 0);
		startDate = DateUtils.setSeconds(startDate, 0);

		return startDate;
	}

	/**
	 * 進捗表示の最終日を取得する。
	 *
	 * @param productProgresses
	 *            進捗状況表示リスト
	 * @param displayEndDate
	 *            製造日検索終了時刻
	 * @return 進捗表示の最終日
	 */
	private Date getDisplayGanttEndDate(List<ProductProgressDto> productProgresses, Date displayEndDate) {

		if (productProgresses.isEmpty()) {
			return DateUtil.setTime(displayEndDate, 23, 0);
		}

		Date endDate = new Date(displayEndDate.getTime());

		for (ProductProgressDto productProgress : productProgresses) {

			if (productProgress.series == null) {
				continue;
			}

			for (ProductProgressSeriesDto series : productProgress.series) {

				if (series.startDate == null && series.endDate == null) {
					continue;
				}

				if (series.startDate != null && endDate.before(series.startDate)) {
					endDate = series.startDate;
				}

				if (series.endDate != null && endDate.before(series.endDate)) {
					endDate = series.endDate;
				}
			}
		}

		return DateUtil.setTime(endDate, 23, 0);
	}
}

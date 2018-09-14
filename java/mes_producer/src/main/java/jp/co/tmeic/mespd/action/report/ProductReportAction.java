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
package jp.co.tmeic.mespd.action.report;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.dto.report.ProductPlanDto;
import jp.co.tmeic.mespd.dto.report.ProductPlanMonthlyDto;
import jp.co.tmeic.mespd.service.ProcessProductResultService;
import jp.co.tmeic.mespd.utils.DateUtil;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth
public class ProductReportAction extends AbstractReportAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	@Resource
	protected ProcessProductResultService processProductResultService;

	/**
	 * 製造レポート(日報)をダウンロードします。
	 *
	 * @param date
	 *            指定日付
	 */
	protected void dailyReport(Date date) {

		try {

			List<ProductPlanDto> list = processProductResultService.findProductResultByDay(date);

			URL template = ResourceUtil.getResourceNoException("/jp/co/tmeic/mespd/action/report/ProductReportDaily.xls");

			Map<String, Object> parames = new HashMap<>();
			parames.put("reportName", MessageResourcesUtil.getMessage(locale(), "product.report"));
			parames.put("reportType", MessageResourcesUtil.getMessage(locale(), "daily.report"));
			parames.put("printDate", DateUtil.now());
			parames.put("reportDate", DateFormatUtils.format(date, "yyyy/MM/dd"));
			parames.put("productPlan", list);
			parames.put("resultQtyLabel", MessageResourcesUtil.getMessage(locale(), "result.qty"));
			parames.put("inferiorQtyLabel", MessageResourcesUtil.getMessage(locale(), "inferior.qty"));

			String downloadFileName = String.format(
					"%s_%s.pdf",
					MessageResourcesUtil.getMessage(locale(), "product.report"),
					MessageResourcesUtil.getMessage(locale(), "daily.report"));

			downloadReport(template, parames, downloadFileName);

		} catch (Exception ex) {
			logger.error("Exception", ex);
		}
	}

	/**
	 * 製造レポート(月報)をダウンロードします。
	 *
	 * @param date
	 *            指定月
	 */
	protected void monthlyReport(Date date) {

		try {
			List<ProductPlanMonthlyDto> list = processProductResultService.findProductResultByMonth(date);

			URL template = ResourceUtil.getResourceNoException("/jp/co/tmeic/mespd/action/report/ProductReportMonthly.xls");

			Map<String, Object> parames = new HashMap<>();
			parames.put("reportName", MessageResourcesUtil.getMessage(locale(), "product.report"));
			parames.put("reportType", MessageResourcesUtil.getMessage(locale(), "monthly.report"));
			parames.put("printDate", DateUtil.now());
			parames.put("reportDate", DateFormatUtils.format(date, "yyyy/MM"));
			parames.put("productPlan", list);
			parames.put("monthLastDay", DateUtil.getDay(DateUtil.getMonthLastDay(date)));
			parames.put("resultQtyLabel", MessageResourcesUtil.getMessage(locale(), "result.qty"));
			parames.put("inferiorQtyLabel", MessageResourcesUtil.getMessage(locale(), "inferior.qty"));
			parames.put("sumLabel", MessageResourcesUtil.getMessage(locale(), "sum"));

			String downloadFileName = String.format(
					"%s_%s.pdf",
					MessageResourcesUtil.getMessage(locale(), "product.report"),
					MessageResourcesUtil.getMessage(locale(), "monthly.report"));

			downloadReport(template, parames, downloadFileName);

		} catch (Exception ex) {
			logger.error("Exception", ex);
		}
	}
}

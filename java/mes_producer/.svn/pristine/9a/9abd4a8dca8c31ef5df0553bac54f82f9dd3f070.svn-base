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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import jp.co.tmeic.mespd.action.AbstractAction;
import jp.co.tmeic.mespd.constant.ProductStatus;
import jp.co.tmeic.mespd.dto.jqgrid.product.DProductPlanDto;
import jp.co.tmeic.mespd.entity.DProcessPlan;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.entity.DProductResult;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.product.ProductResultForm;
import jp.co.tmeic.mespd.service.DProcessPlanService;
import jp.co.tmeic.mespd.service.DProcessResultService;
import jp.co.tmeic.mespd.service.DProductPlanService;
import jp.co.tmeic.mespd.service.DProductResultService;
import jp.co.tmeic.mespd.service.MMaterialService;
import jp.co.tmeic.mespd.service.MProductService;
import jp.co.tmeic.mespd.service.ProductPlanService;
import jp.co.tmeic.mespd.utils.BrowserMsg;
import jp.co.tmeic.mespd.utils.DateUtil;
import jp.co.tmeic.mespd.utils.JSONUtil;
import jp.co.tmeic.mespd.utils.JqgridUtil;
import jp.co.tmeic.mespd.utils.JqueryUtil;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;
import jp.co.tmeic.mespd.utils.TimestampUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth
public class ProductResultAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** UserTransaction */
	public UserTransaction userTransaction;

	/** ActionForm */
	@Resource
	@ActionForm
	protected ProductResultForm productResultForm;

	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected MProductService mProductService;

	@Resource
	protected MMaterialService mMaterialService;

	@Resource
	protected DProductPlanService dProductPlanService;

	@Resource
	protected DProductResultService dProductResultService;

	@Resource
	protected DProcessPlanService dProcessPlanService;

	@Resource
	protected ProductPlanService productPlanService;

	@Resource
	protected DProcessResultService dProcessResultService;
	
	protected enum ProductStatusInput {
		NONE,
		IN_PRODUCTION,
		COMPLETE,
		SUSPEND,
		RESTART,
		STOP;
	}

	@Execute(validator = false)
	public String index() {

		try {
			indexInitFrom(productResultForm);
			indexValidate(productResultForm);

			Date startDate = DateUtil.toDate(productResultForm.displayStartDate);
			Date endDate = DateUtil.toDate(productResultForm.displayEndDate);

			List<DProductPlanDto> dProductResultDtos = productPlanService.findByManufactureDate(startDate, endDate);

			productResultForm.productResultJson = JSONUtil.encode(JqgridUtil.init(dProductResultDtos));
			
			String planNo = request.getParameter("planNo");
			if(planNo != null)
				productResultForm.selectProductPlanNo = planNo;

		} catch (ValidationException ex) {

			productResultForm.productResultJson = "[]";

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
	private void indexInitFrom(ProductResultForm form) {

		int interval = SystemParameterUtil.productAutoupdateInterval();
		productResultForm.interval = String.valueOf(interval);

		productResultForm.productStatus = ProductStatus.getStatus(locale());

		// 初回表示時は自動更新状態を設定
		if (StringUtils.isBlank(productResultForm.isAutoUpdating)) {
			String isAutoUpdate = "0";
			if (SystemParameterUtil.productAutoupdate()) {
				isAutoUpdate = "1";
			}
			productResultForm.isAutoUpdating = isAutoUpdate;
		}

		// 初回表示は本日
		if (StringUtils.isBlank(form.displayStartDate)) {
			form.displayStartDate = DateUtil.now("dd/MM/yyyy");
		}

		if (StringUtils.isBlank(form.displayEndDate)) {
			form.displayEndDate = DateUtil.now("dd/MM/yyyy");
		}

		form.productResultJson = "[]";
	}

	/**
	 * 検索処理が可能か検証を行う。
	 *
	 * @param form
	 */
	private void indexValidate(ProductResultForm form) {
		validateManufuctureDate(form.displayStartDate, form.displayEndDate);
	}

	/**
	 * 製造日を検証する。
	 *
	 * @param displayStartDate
	 * @param displayEndDate
	 */
	private void validateManufuctureDate(String displayStartDate, String displayEndDate) {

		ValidateUtil.date("product.manifacturedate", displayStartDate);
		ValidateUtil.date("product.manifacturedate", displayEndDate);

		int displayDays = SystemParameterUtil.productDisplayDays();
		long days = DateUtil.days(DateUtil.toDate(displayStartDate), DateUtil.toDate(displayEndDate));

		ValidateUtil.displayLimitDays("product.manifacturedate", displayDays, days);
	}

	/**
	 * 最新計画データを返却する（自動更新用）
	 */
	@Execute(validator = false)
	public String autoUpdate() {

		Map<String, String> json = new HashMap<>();

		try {
			String displayStartDate = request.getParameter("displayStartDate");
			String displayEndDate = request.getParameter("displayEndDate");

			validateManufuctureDate(displayStartDate, displayEndDate);

			Date startDate = DateUtil.toDate(displayStartDate);
			Date endDate = DateUtil.toDate(displayEndDate);

			List<DProductPlanDto> dProductResultDtos = productPlanService.findByManufactureDate(startDate, endDate);

			String productResultJson = JSONUtil.encode(JqgridUtil.init(dProductResultDtos));

			json.put("result", "OK");
			json.put("productResults", productResultJson);

			return null;

		} catch (ValidationException ex) {
			json.put("result", "NG");
			json.put("message", ex.getMessage());
		} catch (Exception ex) {
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.getdata"));

		} finally {
			JqueryUtil.callback(JSONUtil.encode(json));
		}

		return null;
	}

	/**
	 * 計画のステータスを変更する
	 */
	@Execute(validator = false)
	public String changeStatus() {

		boolean isCommit = false;

		try {

			String productPlanNo = productResultForm.selectProductPlanNo;
			String statusId = productResultForm.selectStatusId;

			if (productPlanNo == null || statusId == null) {

				BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
				return "/product/productResult/";
			}

			ProductStatusInput inputStatus = convertInputStatusNameToInputStatusValue(statusId);

			// 指定計画の状態は変更可能かどうか確認
			if (!isChangeableStatus(productPlanNo, inputStatus)) {

				BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), "errors.cannot.change.plan.state"));
				return "/product/productResult/";
			}

			// 状態変更処理
			int updateStatus = convertInputStatusValueToUpdateStatusValue(inputStatus);
			doChangeStatus(productPlanNo, updateStatus);

			isCommit = true;

		} catch (Exception ex) {
			logger.error("Exception", ex);
			BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), "errors.register"));
		} finally {

			if (!isCommit) {
				try {
					userTransaction.setRollbackOnly();
				} catch (IllegalStateException | SystemException ex) {
					logger.error("Exception", ex);
				}
			}
		}

		return "/product/productResult/";
	}

	/**
	 * 入力ステータス名->入力ステータス値へ変換
	 *
	 * @param statusId
	 * @return
	 */
	private ProductStatusInput convertInputStatusNameToInputStatusValue(String statusId) {

		switch (StringUtils.lowerCase(statusId)) {

			case "start":
				return ProductStatusInput.IN_PRODUCTION;

			case "complete":
				return ProductStatusInput.COMPLETE;

			case "suspend":
				return ProductStatusInput.SUSPEND;

			case "restart":
				return ProductStatusInput.RESTART;

			case "stop":
				return ProductStatusInput.STOP;
		}

		return ProductStatusInput.NONE;
	}

	/**
	 * 入力ステータス値->更新用ステータス値へ変換
	 *
	 * @param statusId
	 * @return
	 */
	private int convertInputStatusValueToUpdateStatusValue(ProductStatusInput inputStatus) {

		switch (inputStatus) {

			case IN_PRODUCTION:
				return ProductStatus.IN_PRODUCTION;

			case COMPLETE:
				return ProductStatus.COMPLETE;

			case SUSPEND:
				return ProductStatus.SUSPEND;

			case RESTART:
				return ProductStatus.IN_PRODUCTION;

			case STOP:
				return ProductStatus.STOP;

			default:
				break;
		}

		return ProductStatus.NONE;
	}

	/**
	 * 指定の状態に変更可能かチェックします。
	 *
	 * @param productPlanNo
	 * @param inputStatus
	 * @return
	 */
	private boolean isChangeableStatus(String productPlanNo, ProductStatusInput inputStatus) {

		int currentStatus = ProductStatus.NONE;

		DProductResult dProductResult = dProductResultService.findById(productPlanNo);

		if (dProductResult != null) {
			currentStatus = dProductResult.status;
		}

		return isChangeableStatus(currentStatus, inputStatus);
	}

	/**
	 * 指定の状態に変更可能かチェックします。
	 *
	 * @param currentStatus
	 * @param inputStatus
	 * @return
	 */
	private boolean isChangeableStatus(int currentStatus, ProductStatusInput inputStatus) {

		switch (currentStatus) {
			case ProductStatus.NONE:

				if (inputStatus == ProductStatusInput.IN_PRODUCTION) {
					return true;
				}

				break;

			case ProductStatus.IN_PRODUCTION:

				switch (inputStatus) {
					case COMPLETE:
					case SUSPEND:
					case STOP:
						return true;
					default:
						break;
				}

				break;

			case ProductStatus.SUSPEND:

				switch (inputStatus) {
					case COMPLETE:
					case RESTART:
					case STOP:
						return true;
					default:
						break;
				}

				break;

			case ProductStatus.STOP:
			case ProductStatus.COMPLETE:

				break;
		}

		return false;
	}

	/**
	 * 状態変更実行
	 *
	 * @param productPlanNo
	 * @param updateStatus
	 */
	private void doChangeStatus(String productPlanNo, int updateStatus) {

		DProductResult dProductResult = dProductResultService.findById(productPlanNo);
		List<DProcessPlan> dProcessPlans = null;
		boolean isNewRow = false;

		if (dProductResult == null) {

			isNewRow = true;

			dProductResult = new DProductResult();
			dProductResult.productPlanNo = productPlanNo;
			dProductResult.startDatetime = TimestampUtil.now();
		}

		dProductResult.status = updateStatus;

		switch (updateStatus) {

			case ProductStatus.IN_PRODUCTION:	// 開始の場合、計画データ追加する

				if (isNewRow) {
					productPlanService.createPlanData(productPlanNo);
				}
				break;

			case ProductStatus.STOP:     // 中止、終了の場合は終了時刻を追加する。
			case ProductStatus.COMPLETE:
				dProductResult.endDatetime = TimestampUtil.now();
			    dProcessPlans = dProcessPlanService.findProcessesByProductPlanNo(productPlanNo);
			    for(DProcessPlan dProcess : dProcessPlans){
			      DProcessResult dProcessResult = dProcessResultService.findById(productPlanNo, dProcess.processComponentNo);
			      if(dProcessResult == null)
			    	dProcessResultService.processStart(productPlanNo, dProcess.processComponentNo);
			      dProcessResultService.processComplet(productPlanNo, dProcess.processComponentNo);
			    }
				break;
		}

		if (isNewRow) {
			dProductResultService.insert(dProductResult);
			// 開始予定と終了予定を計算して設定する
			productPlanService.reschedule(productPlanNo);

		} else {
			dProductResultService.update(dProductResult);
		}
	}

	/** 製品を選びなさい */
	@Execute(validator = false)
	public String selectProduct() {
		Map<String, String> json = new HashMap<>();

		try {
			String barcode = request.getParameter("barcode");
			String planNo = jdbcManager.selectBySql(
									String.class,
									"select product_plan_no from v_d_barcode_association where barcode_no = ? order by product_plan_no desc limit 1",
									barcode).getSingleResult();

			if (planNo == null) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.barcode.not.exist"));
			}
			
			String manufatureDate = jdbcManager.selectBySql(
							String.class,
							"select manufacture_date from d_product_plan where product_plan_no = ?",
							planNo).getSingleResult();
			String[] s = manufatureDate.split("-");
			manufatureDate = s[2] + "/" + s[1] + "/" + s[0];
			
			json.put("result", "OK");
			json.put("planNo", planNo);
			json.put("manufatureDate", manufatureDate);
		} catch (ValidationException ex) {
			json.put("result", "NG");
			json.put("message", ex.getMessage());
		} catch (Exception ex) {
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.barcode.not.exist"));
		} finally {
			JqueryUtil.callback(JSONUtil.encode(json));
		}

		return null;
	}
}

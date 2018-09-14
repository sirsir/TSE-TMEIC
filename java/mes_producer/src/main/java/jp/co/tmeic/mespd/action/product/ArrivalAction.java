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

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import jp.co.tmeic.mespd.action.AbstractAction;
import jp.co.tmeic.mespd.constant.NoNumberingMethod;
import jp.co.tmeic.mespd.dto.jqgrid.product.DArrivalDto;
import jp.co.tmeic.mespd.entity.DArrivalPlan;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.product.ArrivalForm;
import jp.co.tmeic.mespd.service.ArrivalService;
import jp.co.tmeic.mespd.service.DArrivalService;
import jp.co.tmeic.mespd.service.MProductService;
import jp.co.tmeic.mespd.utils.BrowserMsg;
import jp.co.tmeic.mespd.utils.DateUtil;
import jp.co.tmeic.mespd.utils.JSONUtil;
import jp.co.tmeic.mespd.utils.JqgridUtil;
import jp.co.tmeic.mespd.utils.JqueryUtil;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.seasar.framework.beans.util.Beans;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth
public class ArrivalAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** UserTransaction */
	public UserTransaction userTransaction;

	/** ActionForm */
	@Resource
	@ActionForm
	protected ArrivalForm arrivalForm;

	@Resource
	protected MProductService mProductService;

	@Resource
	protected DArrivalService dArrivalService;

	@Resource
	protected ArrivalService arrivalService;

	@Execute(validator = false)
	public String index() throws ParseException {

		try {

			arrivalForm.planQtyMaxLength = String.valueOf(SystemParameterUtil.productQuantityMaxLength());
			arrivalForm.arrivalNoNumberingMethod = String.valueOf(SystemParameterUtil.arrivalNoNumberingMethod());
			//arrivalForm.productStatus = ProductStatus.getStatus(locale());
			arrivalForm.mProducts = mProductService.findAllOrderById();

			if (StringUtils.isBlank(arrivalForm.displayStartDate)) {

				// 初回表示は本日
				arrivalForm.displayStartDate = DateUtil.now("dd/MM/yyyy");
				arrivalForm.displayEndDate = DateUtil.now("dd/MM/yyyy");
			}

			indexValidate(arrivalForm);
			
			List<DArrivalDto> dArrivalDtos =
					arrivalService.findByArrivalDate(
							DateUtil.toDate(arrivalForm.displayStartDate),
							DateUtil.toDate(arrivalForm.displayEndDate));
			
			arrivalForm.arrivalJson = JSONUtil.encode(JqgridUtil.init(dArrivalDtos));
			
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
			arrivalForm.arrivalJson = "[]";

			BrowserMsg.toAlert(ex.getMessage());	

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception", ex);
		}

		return "index.jsp";
	}

	/**
	 * 検索処理が可能か検証を行う。
	 *
	 * @param dArrivalDtos
	 * @throws ParseException
	 */
	private void indexValidate(ArrivalForm form) {

		ValidateUtil.date("arrival.date", form.displayStartDate);
		ValidateUtil.date("arrival.date", form.displayEndDate);

		int displayDays = SystemParameterUtil.productDisplayDays();

		//try {
			long days = DateUtil.days(DateUtil.toDate(form.displayStartDate), DateUtil.toDate(form.displayEndDate));

			if (days > displayDays) {

				throw new IllegalStateException(
						MessageResourcesUtil.getMessage(locale(), "errors.out.of.range",
								MessageResourcesUtil.getMessage(locale(), "arrival.date")));
			}

		//} catch (ParseException ex) {
			// 事前に日付型チェック済み
		//}
	}

	/** 登録 */
	@Execute(validator = false)
	public String register() {

		Map<String, String> json = new HashMap<>();

		boolean isCommit = false;

		try {
			List<DArrivalDto> dArrivals = JSONUtil.decode(arrivalForm.arrivalJson, DArrivalDto[].class);
			
			registerValidate(dArrivals);
			registerExe(dArrivals);

			json.put("result", "OK");

			isCommit = true;

		} catch (ValidationException ex) {
			json.put("result", "NG");
			json.put("message", ex.getMessage());
		} catch (Exception ex) {
			//ex.printStackTrace();
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.register"));
		} finally {

			if (!isCommit) {
				try {
					userTransaction.setRollbackOnly();
				} catch (IllegalStateException | SystemException ex) {
					logger.error("Exception", ex);
				}
			}
			
			JqueryUtil.callback(JSONUtil.encode(json));
		}

		return null;
	}

	/**
	 * 登録可能か検証を行う。
	 *
	 * @param arrivals
	 * @throws ParseException
	 */
	private void registerValidate(List<DArrivalDto> arrivals) throws ParseException {

		for (DArrivalDto arrival : arrivals) {

			validateArrivalNo(arrival.crud, arrival.arrivalNo);
			ValidateUtil.required("arrival.date", arrival.arrivalDate);
			ValidateUtil.date("arrival.date", arrival.arrivalDate);

			Date startDate = DateUtil.toDate(arrivalForm.displayStartDate);
			Date endDate = DateUtil.toDate(arrivalForm.displayEndDate);
			Date arrivalDate = DateUtil.toDate(arrival.arrivalDate);
			
			ValidateUtil.outOfRange("arrival.date", startDate, endDate, arrivalDate);

			ValidateUtil.required("product.code", arrival.partNo);
			ValidateUtil.maxlength("product.code", arrival.partNo, 10);

			ValidateUtil.required("plan.qty", arrival.planQty);
			ValidateUtil.maxlength("plan.qty", arrival.planQty, SystemParameterUtil.productQuantityMaxLength());
		}
	}

	/**
	 * 検証を行う。
	 *
	 * @param crud
	 * @param arrivalNo
	 */
	private void validateArrivalNo(String crud, String arrivalNo) {

		int arrivalNoNumberingMethod = SystemParameterUtil.arrivalNoNumberingMethod();

		// 自動採番時は未入力許可
		boolean isNonCheck =
				arrivalNoNumberingMethod == NoNumberingMethod.AUTO
						&& StringUtils.equals(crud, "C")
						&& StringUtils.isBlank(arrivalNo);

		if (isNonCheck) {
			return;
		}
		
		ValidateUtil.required("arrival.no", arrivalNo);
		ValidateUtil.maxlength("arrival.no", arrivalNo, 10);

		// Noの重複チェック(新規)
		boolean isArrivalNoDuplicate = StringUtils.equals(crud, "C") && dArrivalService.isExist(arrivalNo);

		if (isArrivalNoDuplicate) {
			ValidateUtil.duplicate("arrival.no");
		}
		
		try {
			String sn4Digit = arrivalNo.substring(6, arrivalNo.length());
			int no = Integer.valueOf(sn4Digit);
			if(no < 1) {
				throw new ValidationException(
						MessageResourcesUtil.getMessage(locale(), "errors.arrival.no.format", arrivalNo));
			}
		}
		catch(Exception e) {
			throw new ValidationException(
					MessageResourcesUtil.getMessage(locale(), "errors.arrival.no.format", arrivalNo));
		}
	}

	/**
	 * 登録実行
	 *
	 * @param arrivals
	 *            登録製造計画リスト
	 *
	 * @throws ParseException
	 *             異常
	 */
	private void registerExe(List<DArrivalDto> arrivals) throws ParseException {

		for (DArrivalDto arrival : arrivals) {

			DArrivalPlan dArrival = Beans.createAndCopy(DArrivalPlan.class, arrival).execute();
			dArrival.arrivalDate = new java.sql.Date(DateUtils.parseDate(arrival.arrivalDate, "dd/MM/yyyy").getTime());
			
			switch (arrival.crud) {
				case "C":

					if (StringUtils.isBlank(dArrival.arrivalNo)) {
						dArrival.arrivalNo = dArrivalService.createArrivalNo(dArrival.arrivalDate);
					}

					dArrivalService.insert(dArrival);
					dArrivalService.insertArrivalResult(dArrival.arrivalNo, arrival.resultQty);
					break;

				case "U":
					dArrivalService.update(dArrival);
					dArrivalService.updateArrivalResult(dArrival.arrivalNo, arrival.resultQty);
					break;
			}

			// 終了時刻更新
			//arrivalService.updateAndCalculatePlanEndDate(dArrival.arrivalNo);
		}
	}

	/** 削除 */
	@Execute(validator = false)
	public String delete() {

		Map<String, String> json = new HashMap<>();

		boolean isCommit = false;

		try {
			String arrivalNo = request.getParameter("deleteArrivalNo");

			/*if (!dProductPlanService.isDeletable(productPlanNo)) {
				json.put("result", "NG");
				json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.delete"));
				return null;
			}*/

			dArrivalService.deleteById(arrivalNo);

			json.put("result", "OK");

			isCommit = true;

		} catch (Exception ex) {
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.delete"));
		} finally {

			if (!isCommit) {
				try {
					userTransaction.setRollbackOnly();
				} catch (IllegalStateException | SystemException ex) {
					logger.error("Exception", ex);
				}
			}

			JqueryUtil.callback(JSONUtil.encode(json));
		}

		return null;
	}
}

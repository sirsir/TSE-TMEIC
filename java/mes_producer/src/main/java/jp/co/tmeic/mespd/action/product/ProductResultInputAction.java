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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import jp.co.tmeic.mespd.action.AbstractAction;
import jp.co.tmeic.mespd.constant.ProcessProductStatus;
import jp.co.tmeic.mespd.constant.ProcessStatus;
import jp.co.tmeic.mespd.constant.SpecAttribute;
import jp.co.tmeic.mespd.convert.DMaterialResultConvert;
import jp.co.tmeic.mespd.convert.DProcessProductResultConvert;
import jp.co.tmeic.mespd.convert.DProcessResultConvert;
import jp.co.tmeic.mespd.convert.DSpecResultConvert;
import jp.co.tmeic.mespd.dto.jqgrid.product.DMaterialResultDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.DProcessProductResultDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.DProcessResultDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.DProductPlanDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.DSpecResultDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.VDBarcodeAssociationDbDto;
import jp.co.tmeic.mespd.entity.DBarcodeLabelManagement;
import jp.co.tmeic.mespd.entity.DMaterialPlan;
import jp.co.tmeic.mespd.entity.DMaterialProcessResult;
import jp.co.tmeic.mespd.entity.DMaterialProductResult;
import jp.co.tmeic.mespd.entity.DProcessPlan;
import jp.co.tmeic.mespd.entity.DProcessProductResult;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.entity.DProductPlan;
import jp.co.tmeic.mespd.entity.DQualityLabelManagement;
import jp.co.tmeic.mespd.entity.DSpecPlan;
import jp.co.tmeic.mespd.entity.DSpecProcessPlan;
import jp.co.tmeic.mespd.entity.DSpecProcessResult;
import jp.co.tmeic.mespd.entity.DSpecProductPlan;
import jp.co.tmeic.mespd.entity.DSpecProductResult;
import jp.co.tmeic.mespd.entity.MPrinter;
import jp.co.tmeic.mespd.exception.MesException;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.product.ProductResultInputForm;
import jp.co.tmeic.mespd.service.DBarcodeLabelManagementService;
import jp.co.tmeic.mespd.service.DMaterialProcessResultService;
import jp.co.tmeic.mespd.service.DMaterialProductResultService;
import jp.co.tmeic.mespd.service.DProcessPlanService;
import jp.co.tmeic.mespd.service.DProcessProductResultService;
import jp.co.tmeic.mespd.service.DProcessResultService;
import jp.co.tmeic.mespd.service.DProductPlanService;
import jp.co.tmeic.mespd.service.DProductResultService;
import jp.co.tmeic.mespd.service.DQualityLabelManagementService;
import jp.co.tmeic.mespd.service.DQualtyLabelPrintQueueService;
import jp.co.tmeic.mespd.service.DShippingTicketPrintQueueService;
import jp.co.tmeic.mespd.service.DSpecPlanService;
import jp.co.tmeic.mespd.service.DSpecProcessResultService;
import jp.co.tmeic.mespd.service.DSpecProductResultService;
import jp.co.tmeic.mespd.service.MPrinterService;
import jp.co.tmeic.mespd.service.ProcessProductResultService;
import jp.co.tmeic.mespd.service.ProcessResultService;
import jp.co.tmeic.mespd.service.QualityBarcodeService;
import jp.co.tmeic.mespd.service.SerialNoService;
import jp.co.tmeic.mespd.service.VDBarcodeAssociationService;
import jp.co.tmeic.mespd.utils.BrowserMsg;
import jp.co.tmeic.mespd.utils.JSONUtil;
import jp.co.tmeic.mespd.utils.JqgridUtil;
import jp.co.tmeic.mespd.utils.JqueryUtil;
import jp.co.tmeic.mespd.utils.MapUtil;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;
import jp.co.tmeic.mespd.validation.SerialNoValidation;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.StringUtil;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth
public class ProductResultInputAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** UserTransaction */
	public UserTransaction userTransaction;

	/** ActionForm */
	@Resource
	@ActionForm
	protected ProductResultInputForm productResultInputForm;

	@Resource
	protected DProductPlanService dProductPlanService;

	@Resource
	protected DProcessPlanService dProcessPlanService;

	@Resource
	protected DSpecPlanService dSpecPlanService;

	@Resource
	protected DProcessResultService dProcessResultService;

	@Resource
	protected DSpecProcessResultService dSpecProcessResultService;

	@Resource
	protected DMaterialProcessResultService dMaterialProcessResultService;

	@Resource
	protected DProcessProductResultService dProcessProductResultService;

	@Resource
	protected DSpecProductResultService dSpecProductResultService;

	@Resource
	protected DMaterialProductResultService dMaterialProductResultService;

	@Resource
	protected ProcessResultService processResultService;

	@Resource
	protected ProcessProductResultService processProductResultService;

	@Resource
	protected SerialNoService serialNoService;

	@Resource
	protected DProductResultService dProductResultService;
	
	@Resource
	protected VDBarcodeAssociationService vDBarcodeAssociationService;
	
	@Resource
	protected DBarcodeLabelManagementService dBarcodeLabelManagementService;
	
	@Resource
	protected DQualityLabelManagementService dQualityLabelManagementService;

	@Resource
	protected QualityBarcodeService qualityBarcodeService;
	
	@Resource
	protected MPrinterService mPrinterService;
	
	@Resource
	protected DQualtyLabelPrintQueueService dQualtyLabelPrintQueueService;
	
	@Resource
	protected DShippingTicketPrintQueueService dShippingTicketPrintQueueService;
	
	@Execute(validator = false)
	public String index() {

		try {

			indexInitFrom(productResultInputForm);

			final String productPlanNo = productResultInputForm.productPlanNo;			
			DProductPlan dProductPlan = dProductPlanService.findById(productPlanNo);
			DProductPlanDto productPlan = Beans.createAndCopy(DProductPlanDto.class, dProductPlan)
												.timestampConverter("dd/MM/yyyy HH:mm:ss")
												.dateConverter("dd/MM/yyyy")
												.execute();

			productResultInputForm.productJson = JSONUtil.encode(JqgridUtil.init(productPlan));

			List<DProcessPlan> dProcessPlans = dProcessPlanService.findProcessesByProductPlanNo(productPlanNo);
			List<Map<String, Object>> fields = processFieldData(dProcessPlans);
			
			productResultInputForm.processResultJson = JSONUtil.encode(JqgridUtil.initMap(fields));
			
			int processMaterialSize = processMaterialPlanMaxSize(dProcessPlans);
			productResultInputForm.processMaterialSize = String.valueOf(processMaterialSize);

			int processSpecSize = processSpecPlanMaxSize(dProcessPlans);
			productResultInputForm.processSpecSize = String.valueOf(processSpecSize);
			
			productResultInputForm.printersJson = JSONUtil.encode(JqgridUtil.initMap(mPrinterService.barcodePrinter()));
			productResultInputForm.printerShippingsJson = JSONUtil.encode(JqgridUtil.initMap(mPrinterService.shippingPrinter()));
			DProcessResult dprocessResult = dProcessResultService.findById(productPlanNo, SystemParameterUtil.pkcqcValue());
			if(dprocessResult!=null)
			{DProcessResultDto dprocessResultDto = Beans.createAndCopy(DProcessResultDto.class, dprocessResult)
													.timestampConverter("dd/MM/yyyy HH:mm:ss")
													.dateConverter("dd/MM/yyyy")
													.execute();
			productResultInputForm.packingDate = dprocessResultDto.endDatetime.substring(0, dprocessResultDto.endDatetime.indexOf(" "));
			}
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
	private void indexInitFrom(ProductResultInputForm form) {

		form.processStatus = ProcessStatus.getStatus(locale());
		form.processProductStatus = ProcessProductStatus.getStatus(locale());
		form.checker = userDto.getUserName();
		form.processMaterialPlanMax = String.valueOf(SystemParameterUtil.materialMaxSize());
		form.processProductSpecPlanMax = String.valueOf(SystemParameterUtil.productSpecMaxSize());
		form.serialnoMaxLength = SystemParameterUtil.productSerialnoMaxLength();
		form.barcodeMaxLength = SystemParameterUtil.productBarcodeMaxLength();
		form.qtyMaxLength = SystemParameterUtil.productQuantityMaxLength();
		form.materialQuantityMaxLength = SystemParameterUtil.materialQuantityMaxLength();
		form.specInputvalueMaxLength = SystemParameterUtil.specInputvalueMaxLength();
	}

	private List<Map<String, Object>> processFieldData(List<DProcessPlan> dProcessPlans) {

		List<Map<String, Object>> fields = new ArrayList<>();

		for (DProcessPlan dProcessPlan : dProcessPlans) {
			Map<String, Object> field = processFieldData(dProcessPlan);
			fields.add(field);
		}

		return fields;
	}

	/**
	 * 工程実績データをMapへ格納する
	 *
	 * @param dProcessPlan
	 * @return
	 */
	private Map<String, Object> processFieldData(DProcessPlan dProcessPlan) {

		Map<String, Object> field = new LinkedHashMap<>();

		field.put("processComponentNo", dProcessPlan.processComponentNo);
		field.put("processName", dProcessPlan.processName);
		field.put("planQty", dProcessPlan.planQty);

		DProcessResult dProcessResult = new DProcessResult();

		if (dProcessPlan.DProcessResult != null) {
			dProcessResult = dProcessPlan.DProcessResult;
		}

		field.put("resultQty", dProcessResult.resultQty);
		field.put("inferiorQty", dProcessResult.inferiorQty);
		field.put("status", dProcessResult.status);
		field.put("startDatetime", dProcessResult.startDatetime);
		field.put("endDatetime", dProcessResult.endDatetime);

		for (int i = 1; i <= dProcessPlan.DMaterialPlanList.size(); i++) {

			DMaterialPlan dMaterialPlan = dProcessPlan.DMaterialPlanList.get(i - 1);

			field.put("materialComponentNo" + i, dMaterialPlan.materialComponentNo);
			field.put("materialName" + i, dMaterialPlan.materialName);
			field.put("materialUnit" + i, dMaterialPlan.materialUnit);

			if (dMaterialPlan.DMaterialProcessResult != null) {
				field.put("materialQty" + i, dMaterialPlan.DMaterialProcessResult.materialQty);
			}
		}

		for (int i = 1; i <= dProcessPlan.DSpecProcessPlanList.size(); i++) {

			DSpecProcessPlan dSpecProcessPlan = dProcessPlan.DSpecProcessPlanList.get(i - 1);

			field.put("specComponentNo" + i, dSpecProcessPlan.specComponentNo);
			field.put("specId" + i, dSpecProcessPlan.specId);
			field.put("specAttributeId" + i, dSpecProcessPlan.DSpecPlan.specAttributeId);
			field.put("specName" + i, dSpecProcessPlan.DSpecPlan.specName);

			Map<String, String> specParts = createSpecParts(dSpecProcessPlan.DSpecPlan);
			field.put("specParts" + i, JqgridUtil.toSelect(specParts));

			if (dSpecProcessPlan.DSpecProcessResult != null) {
				field.put("inputValue" + i, dSpecProcessPlan.DSpecProcessResult.inputValue);
			}
		}

		return field;
	}

	private Map<String, String> createSpecParts(DSpecPlan dSpecPlan) {

		Map<String, String> specParts =
				MapUtil.putMap(
						dSpecPlan.specParts01,
						dSpecPlan.specParts02,
						dSpecPlan.specParts03,
						dSpecPlan.specParts04,
						dSpecPlan.specParts05,
						dSpecPlan.specParts06,
						dSpecPlan.specParts07,
						dSpecPlan.specParts08,
						dSpecPlan.specParts09,
						dSpecPlan.specParts10
						);

		specParts.remove(StringUtils.EMPTY);

		return specParts;

	}

	/**
	 * 工程部材の中で最大部材数を取得する。
	 *
	 * @param dProcessPlans
	 * @return
	 */
	private int processMaterialPlanMaxSize(List<DProcessPlan> dProcessPlans) {

		if (dProcessPlans == null) {
			return 0;
		}

		int maxSize = 0;

		for (DProcessPlan dProcessPlan : dProcessPlans) {

			if (dProcessPlan.DMaterialPlanList == null) {
				continue;
			}

			int currentPlanSize = dProcessPlan.DMaterialPlanList.size();

			if (maxSize < currentPlanSize) {
				maxSize = currentPlanSize;
			}
		}

		return maxSize;
	}

	/**
	 * 工程仕様の中で最大仕様数を取得する。
	 *
	 * @param dProcessPlans
	 * @return
	 */
	private int processSpecPlanMaxSize(List<DProcessPlan> dProcessPlans) {

		if (dProcessPlans == null) {
			return 0;
		}

		int maxSize = 0;

		for (DProcessPlan dProcessPlan : dProcessPlans) {

			if (dProcessPlan.DSpecProcessPlanList == null) {
				continue;
			}

			int currentPlanSize = dProcessPlan.DSpecProcessPlanList.size();

			if (maxSize < currentPlanSize) {
				maxSize = currentPlanSize;
			}
		}

		return maxSize;
	}

	@Execute(validator = false)
	public String processProductData() {

		Map<String, String> json = new HashMap<>();

		try {
			final String productPlanNo = request.getParameter("productPlanNo");
			final int processComponentNo = NumberUtils.toInt(request.getParameter("processComponentNo"), -1);

			if (StringUtil.isBlank(productPlanNo) || processComponentNo == -1) {

				json.put("result", "NG");
				json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));

				return null;
			}

			List<DProcessProductResult> dProcessProductResults =
					dProcessProductResultService.findProductResultsById(productPlanNo, processComponentNo);
			List<Map<String, Object>> resultFields = processProductResultFieldData(dProcessProductResults);

			DProcessPlan dProcessPlan = dProcessPlanService.findPlansById(productPlanNo, processComponentNo);
			Map<String, Object> planFields =
					processProductPlanFieldData(dProcessPlan.DMaterialPlanList, dProcessPlan.DSpecProductPlanList);

			int materialPlanSize = dProcessPlan.DMaterialPlanList.size();
			int specPlanSize = dProcessPlan.DSpecProductPlanList.size();

			json.put("result", "OK");
			json.put("productMaterialPlanSize", String.valueOf(materialPlanSize));
			json.put("productSpecPlanSize", String.valueOf(specPlanSize));
			json.put("productResultJson", JSONUtil.encode(JqgridUtil.initMap(resultFields)));
			json.put("productPlanJson", JSONUtil.encode(planFields));

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
	 * 工程単位製品計画データ(仕様・部材)をMapへ格納する
	 *
	 * @param dProcessProductResult
	 * @param dMaterialPlans
	 * @param dSpecProductPlans
	 * @return
	 */
	private Map<String, Object> processProductPlanFieldData(
			List<DMaterialPlan> dMaterialPlans,
			List<DSpecProductPlan> dSpecProductPlans) {

		Map<String, Object> field = new LinkedHashMap<>();

		for (int i = 1; i <= dMaterialPlans.size(); i++) {

			DMaterialPlan dMaterialPlan = dMaterialPlans.get(i - 1);

			field.put("materialComponentNo" + i, dMaterialPlan.materialComponentNo);
			field.put("materialName" + i, dMaterialPlan.materialName);
			field.put("materialUnit" + i, dMaterialPlan.materialUnit);

		}

		for (int i = 1; i <= dSpecProductPlans.size(); i++) {

			DSpecProductPlan dSpecProductPlan = dSpecProductPlans.get(i - 1);

			field.put("specComponentNo" + i, dSpecProductPlan.specComponentNo);
			field.put("specId" + i, dSpecProductPlan.specId);
			field.put("specAttributeId" + i, dSpecProductPlan.DSpecPlan.specAttributeId);
			field.put("specName" + i, dSpecProductPlan.DSpecPlan.specName);

			Map<String, String> specParts = createSpecParts(dSpecProductPlan.DSpecPlan);
			field.put("specParts" + i, JqgridUtil.toSelect(specParts));
		}

		return field;
	}

	/**
	 * 工程内製品単位実績データをMapへ格納する
	 *
	 * @param dProcessProductResults
	 * @param dMaterialPlans
	 * @param dSpecProductPlans
	 * @return
	 */
	private List<Map<String, Object>> processProductResultFieldData(List<DProcessProductResult> dProcessProductResults) {

		List<Map<String, Object>> fields = new ArrayList<>();

		for (DProcessProductResult dProcessProductResult : dProcessProductResults) {
			Map<String, Object> field =
					processProductResultFieldData(dProcessProductResult);
			fields.add(field);
		}

		return fields;
	}

	/**
	 * 工程内製品単位実績データをMapへ格納する
	 *
	 * @param dProcessProductResult
	 * @param dMaterialPlans
	 * @param dSpecProductPlans
	 * @return
	 */
	private Map<String, Object> processProductResultFieldData(DProcessProductResult dProcessProductResult) {

		Map<String, Object> field = new LinkedHashMap<>();
		if(dProcessProductResult.processComponentNo < 5 )
		{	DBarcodeLabelManagement dBarcode = dBarcodeLabelManagementService.findByProductPlanNoAndSerialNo(dProcessProductResult.productPlanNo, dProcessProductResult.serialNo);
			if(dBarcode!=null)
				field.put("barcode", dBarcode.barcodeNo);
			if(dProcessProductResult.processComponentNo == 4){
				DQualityLabelManagement dquality = dQualityLabelManagementService.findByProductPlanNoAndSerialNo(dProcessProductResult.productPlanNo, dBarcode.serialNo);
				if(dquality!=null)
					field.put("qualityLabel", dquality.barcodeNo);
			}
		}else{
			DQualityLabelManagement dquality = dQualityLabelManagementService.findByProductPlanNoAndSerialNo(dProcessProductResult.productPlanNo, dProcessProductResult.serialNo);
			if(dquality!=null)
				field.put("barcode", dquality.barcodeNo);
		}
		
		field.put("serialNo", dProcessProductResult.serialNo);
		field.put("revision", dProcessProductResult.revision);
		field.put("startDate", dProcessProductResult.startDate);
		field.put("endDate", dProcessProductResult.endDate);
		field.put("status", dProcessProductResult.status);

		if (!CollectionUtils.isEmpty(dProcessProductResult.DMaterialProductResultList)) {

			for (int i = 1; i <= dProcessProductResult.DMaterialProductResultList.size(); i++) {

				DMaterialProductResult dMaterialProductResult = dProcessProductResult.DMaterialProductResultList.get(i - 1);
				field.put("materialQty" + i, dMaterialProductResult.materialQty);
			}
		}

		if (!CollectionUtils.isEmpty(dProcessProductResult.DSpecProductResultList)) {

			for (int i = 1; i <= dProcessProductResult.DSpecProductResultList.size(); i++) {

				DSpecProductResult dSpecProductResult = dProcessProductResult.DSpecProductResultList.get(i - 1);
				field.put("inputValue" + i, dSpecProductResult.inputValue);
			}
		}

		return field;
	}
	
	private Map<String, Object> processProductResultFieldData(DProcessProductResult dProcessProductResult, String barcode) {

		Map<String, Object> field = new LinkedHashMap<>();
		
		field.put("barcode", barcode);
		field.put("serialNo", dProcessProductResult.serialNo);
		field.put("revision", dProcessProductResult.revision);
		field.put("startDate", dProcessProductResult.startDate);
		field.put("endDate", dProcessProductResult.endDate);
		field.put("status", dProcessProductResult.status);

		if (!CollectionUtils.isEmpty(dProcessProductResult.DMaterialProductResultList)) {

			for (int i = 1; i <= dProcessProductResult.DMaterialProductResultList.size(); i++) {

				DMaterialProductResult dMaterialProductResult = dProcessProductResult.DMaterialProductResultList.get(i - 1);
				field.put("materialQty" + i, dMaterialProductResult.materialQty);
			}
		}

		if (!CollectionUtils.isEmpty(dProcessProductResult.DSpecProductResultList)) {

			for (int i = 1; i <= dProcessProductResult.DSpecProductResultList.size(); i++) {

				DSpecProductResult dSpecProductResult = dProcessProductResult.DSpecProductResultList.get(i - 1);
				field.put("inputValue" + i, dSpecProductResult.inputValue);
			}
		}

		return field;
	}

	/** 工程ステータス変更 */
	@Execute(validator = false)
	public String changeStatus() {

		Map<String, String> json = new HashMap<>();

		boolean isCommit = false;

		try {

			final String productPlanNo = productResultInputForm.productPlanNo;
			final int processComponentNo = NumberUtils.toInt(productResultInputForm.processComponentNo, -1);
			final String statusId = productResultInputForm.statusId;

			if (StringUtil.isBlank(productPlanNo) || processComponentNo == -1 || StringUtil.isBlank(statusId)) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}

			final int updateStatus = convertStatusIdToStatusValue(statusId);
			isCommit = doChangeStatus(productPlanNo, processComponentNo, updateStatus);

			if (isCommit) {
				json.put("result", "OK");
			} else {
				json.put("result", "NG");
				json.put("message", MessageResourcesUtil.getMessage(locale(), "errors.register"));
			}

		} catch (ValidationException ex) {
			json.put("result", "NG");
			json.put("message", ex.getMessage());
		} catch (MesException ex) {
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), ex.getErrorMessageKey()));
		} catch (Exception ex) {
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
	 * ステータス名->ステータス値へ変換
	 *
	 * @param statusId
	 * @return
	 */
	private int convertStatusIdToStatusValue(String statusId) {

		switch (StringUtils.lowerCase(statusId)) {

			case "start":
				return ProcessStatus.START;

			case "complete":
				return ProcessStatus.COMPLETE;
		}

		return ProcessStatus.NONE;
	}

	/**
	 * 状態変更実行
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @param updateStatus
	 */
	private boolean doChangeStatus(String productPlanNo, int processComponentNo, int updateStatus) {

		switch (updateStatus) {
			case ProcessStatus.START:
				return processResultService.processStart(productPlanNo, processComponentNo);

			case ProcessStatus.COMPLETE:
				return processResultService.processComplet(productPlanNo, processComponentNo);
		}

		return false;
	}

	/**
	 * 登録
	 */
	@Execute(validator = false)
	public String register() {

		Map<String, String> json = new HashMap<>();

		boolean isCommit = false;

		try {
			String productPlanNo = productResultInputForm.productPlanNo;
			String processResultJson = productResultInputForm.processResultJson;

			if (StringUtil.isBlank(productPlanNo) || StringUtil.isBlank(processResultJson)) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}

			List<DProcessResultDto> dProcessResults = DProcessResultConvert.toDProcessResultDto(processResultJson);

			validateProcessData(dProcessResults);

			registerProcess(productPlanNo, dProcessResults);

			isCommit = true;
			json.put("result", "OK");

		} catch (ValidationException ex) {
			json.put("result", "NG");
			json.put("message", ex.getMessage());
		} catch (Exception ex) {
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
	 * 入力チェック 工程実績
	 */
	private void validateProcessData(List<DProcessResultDto> processResults) {

		for (DProcessResultDto processResult : processResults) {

			ValidateUtil.dateOrBlank("start.time", processResult.startDatetime);

			ValidateUtil.dateOrBlank("end.time", processResult.endDatetime);

			validateMaterialData(processResult.dMaterialProcessResults);
			validateSpecData(processResult.dSpecProcessResults);

			validateProcessProductData(processResult.dProcessProductResults);
		}
	}

	/**
	 * 入力チェック 工程別製品実績
	 */
	private void validateProcessProductData(List<DProcessProductResultDto> productResults) {

		for (DProcessProductResultDto productResult : productResults) {

			ValidateUtil.dateOrBlank("start.time", productResult.startDate);

			ValidateUtil.dateOrBlank("end.time", productResult.endDate);

			validateMaterialData(productResult.dMaterialProductResults);
			validateSpecData(productResult.dSpecProductResults);
		}
	}

	/**
	 * 入力チェック 部材実績
	 */
	private void validateMaterialData(List<DMaterialResultDto> materialResults) {

		for (DMaterialResultDto materialResult : materialResults) {

			ValidateUtil.integerOrBlank("material.qty", materialResult.materialQty);
			ValidateUtil.maxlength("material.qty", materialResult.materialQty, SystemParameterUtil.materialQuantityMaxLength());
			ValidateUtil.minInteger("material.qty", materialResult.materialQty, 0);
		}
	}

	/**
	 * 入力チェック 仕様実績
	 */
	private void validateSpecData(List<DSpecResultDto> specResults) {

		for (DSpecResultDto specResult : specResults) {

			int specAttributeId = NumberUtils.toInt(specResult.dSpecPlan.specAttributeId);
			int inputValueMax = SystemParameterUtil.specInputvalueMaxLength();

			switch (specAttributeId) {

				case SpecAttribute.NUMERIC:

					ValidateUtil.numericOrBlank("input.value", specResult.inputValue);
					ValidateUtil.maxlength("input.value", specResult.inputValue, inputValueMax);
					break;

				case SpecAttribute.CHARACTER:

					ValidateUtil.maxlength("input.value", specResult.inputValue, inputValueMax);
					break;

				case SpecAttribute.BUTTON:
				case SpecAttribute.PULL_DOWN:
				case SpecAttribute.PASS_OR_FAIL:

					break;
			}
		}
	}

	/** 登録実行(工程実績) */
	private void registerProcess(String productPlanNo, List<DProcessResultDto> dProcessResultDtos) {

		for (DProcessResultDto dProcessResultDto : dProcessResultDtos) {

			DProcessResult dProcessResult =
					DProcessResultConvert.toDProcessResult(productPlanNo, dProcessResultDto);

			// ステータスは更新対象外とする
			dProcessResult.status = null;

			dProcessResultService.insertOrUpdate(dProcessResult);

			registerProcessMaterial(dProcessResult, dProcessResultDto);
			registerProcessSpec(dProcessResult, dProcessResultDto);

			registerProduct(dProcessResult, dProcessResultDto.dProcessProductResults);
		}
	}

	/** 登録実行(製品実績) */
	private void registerProduct(DProcessResult dProcessResult, List<DProcessProductResultDto> dProductResults) {

		boolean existUpdateProduct = false;

		for (DProcessProductResultDto dProductResultDto : dProductResults) {

			if (StringUtils.equals(dProductResultDto.crud, "R")) {
				continue;
			}

			existUpdateProduct = true;

			DProcessProductResult dProductResult =
					DProcessProductResultConvert.toDProcessProductResult(dProcessResult, dProductResultDto);

			// ステータスは更新対象外とする
			dProductResult.status = null;

			dProcessProductResultService.insertOrUpdate(dProductResult);

			registerProductMaterial(dProductResult, dProductResultDto);
			registerProductSpec(dProductResult, dProductResultDto);
		}

		if (existUpdateProduct) {
			reculcResultCount(dProcessResult.productPlanNo, dProcessResult.processComponentNo);
		}
	}

	/** 登録実行(工程単位部材実績) */
	private void registerProcessMaterial(DProcessResult dProcessResult, DProcessResultDto dProcessResultDto) {

		List<DMaterialProcessResult> dMaterialProcessResults =
				DMaterialResultConvert.toDMaterialProcessResult(dProcessResult, dProcessResultDto.dMaterialProcessResults);
		dMaterialProcessResultService.insertOrUpdate(dMaterialProcessResults);
	}

	/** 登録実行(工程単位仕様実績) */
	private void registerProcessSpec(DProcessResult dProcessResult, DProcessResultDto dProcessResultDto) {

		List<DSpecProcessResult> dSpecProcessResults =
				DSpecResultConvert.toDSpecProcessResult(dProcessResult, dProcessResultDto.dSpecProcessResults);
		dSpecProcessResultService.insertOrUpdate(dSpecProcessResults);
	}

	/** 登録実行(製品単位部材実績) */
	private void registerProductMaterial(DProcessProductResult dProductResult, DProcessProductResultDto dProductResultDto) {

		List<DMaterialProductResult> dMaterialProductResults =
				DMaterialResultConvert.toDMaterialProductResult(dProductResult, dProductResultDto.dMaterialProductResults);
		dMaterialProductResultService.insertOrUpdate(dMaterialProductResults);
	}

	/** 登録実行(製品単位仕様実績) */
	private void registerProductSpec(DProcessProductResult dProductResult, DProcessProductResultDto dProductResultDto) {

		List<DSpecProductResult> dSpecProductResults =
				DSpecResultConvert.toDSpecProductResult(dProductResult, dProductResultDto.dSpecProductResults);
		dSpecProductResultService.insertOrUpdate(dSpecProductResults);
	}

	/** 仕様/部材実績再計算 */
	private void reculcResultCount(String productPlanNo, Integer processComponentNo) {

        processProductResultService.updateQuality(productPlanNo, processComponentNo);
		processProductResultService.calculateResultCount(productPlanNo, processComponentNo);
	}

	/**
	 * 製品一覧新規行データ作成
	 */
	@Execute(validator = false)
	public String createProductNewRowData() {
		Map<String, String> json = new HashMap<>();
		try {

			final String productPlanNo = request.getParameter("productPlanNo");
			final int processComponentNo = NumberUtils.toInt(request.getParameter("processComponentNo"), -1);
			String barcode = StringUtils.trim(request.getParameter("barcode"));
			String serialNo = "";
			
			if (StringUtil.isBlank(barcode) || StringUtil.isBlank(productPlanNo) || processComponentNo == -1) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}
			if (processComponentNo < SystemParameterUtil.pkcqcValue()) {
				int barcodeMaxLength = SystemParameterUtil.productBarcodeMaxLength();
				ValidateUtil.length("product.barcode", barcode, barcodeMaxLength);
			}else{
				int barcodeMaxLength = SystemParameterUtil.productQuantityLabelMaxLength();
				ValidateUtil.length("product.barcode", barcode, barcodeMaxLength);
			}
			if(processComponentNo == SystemParameterUtil.deburringValue())
				validateBarcodeNo(productPlanNo, barcode);
			if(processComponentNo == SystemParameterUtil.pkcqcValue())
				validateQualityLabel(productPlanNo, barcode);
			VDBarcodeAssociationDbDto vDBarcodeAssociation  = vDBarcodeAssociationService.findByBarcode(barcode, productPlanNo);
			if(vDBarcodeAssociation != null){
				serialNo = vDBarcodeAssociation.serialNo;
			}
			
			if(processComponentNo > SystemParameterUtil.deburringValue()){
				Integer revision = dProcessProductResultService.getIncompleteMinRevision(productPlanNo, processComponentNo-1, serialNo);
				if(processComponentNo == SystemParameterUtil.pkcqcValue()){
					DBarcodeLabelManagement dbarcode = dBarcodeLabelManagementService.findByProductPlanNoAndSerialNo(productPlanNo, serialNo);
					if(dbarcode != null)
						revision = dProcessProductResultService.getMaxRevision(productPlanNo, processComponentNo-1, dbarcode.serialNo);
				}
				if (revision == null) {
					// 未完了の計画が無ければ、最終履歴を取得
					revision = dProcessProductResultService.getMaxRevision(productPlanNo, processComponentNo-1, serialNo);
					if(revision == null){
						throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.product.processed.notstarted"));
					}
				}
				DProcessProductResult dProcessProductResultCheck = dProcessProductResultService.findById(productPlanNo, processComponentNo-1, serialNo, revision);
				if (dProcessProductResultCheck.status != ProcessProductStatus.COMPLETE) {
					throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.product.processed.notfinished"));
				}
			}
			
			boolean useAutoNumberSerial = serialNoService.useAutoNumberSerial(productPlanNo, processComponentNo, serialNo);

			DProcessProductResult dProcessProductResult = new DProcessProductResult();
			dProcessProductResult.serialNo = serialNo;
			dProcessProductResult.status = ProcessProductStatus.NONE;
			
			if (useAutoNumberSerial) {

				dProcessProductResult.serialNo = serialNoService.createSerialNo();

			} else {

				validateSerialNo(productPlanNo, serialNo);
				DProcessProductResult dProcessProductResultRegistered = readLatestProductResult(productPlanNo, processComponentNo, serialNo);

				if (dProcessProductResultRegistered != null) {
					Beans.copy(dProcessProductResultRegistered, dProcessProductResult).execute();
				}
			}

			Map<String, Object> field = processProductResultFieldData(dProcessProductResult,barcode);
			
			json.put("result", "OK");
			json.put("newRowData", JSONUtil.encode(field));
		} catch (IllegalStateException ex) {
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", ex.getMessage());
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
	 * シリアルNo入力チェック
	 *
	 * @param productPlanNo
	 * @param productResults
	 * @throws ValidationException
	 */
	private void validateSerialNo(String productPlanNo, List<DProcessProductResultDto> productResults) throws ValidationException {

		for (DProcessProductResultDto productResult : productResults) {

			validateSerialNo(productPlanNo, productResult.serialNo);
		}

		// 入力されたシリアルNoの中に重複しているものが無いかチェックする
		List<String> duplicateSerials = productResults.stream().map(e -> StringUtils.trim(e.serialNo)).distinct().collect(Collectors.toList());

		if (duplicateSerials.size() != productResults.size()) {

			String serialName = MessageResourcesUtil.getMessage(locale(), "product.serialno");
			throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.duplicate", serialName));
		}
	}

	/**
	 * シリアルNo入力チェック
	 *
	 * @param productPlanNo
	 * @param serialNo
	 * @throws ValidationException
	 */
	private void validateSerialNo(String productPlanNo, String serialNo) throws ValidationException {

		SerialNoValidation.validate(serialNo);

		boolean isDuplicate = serialNoService.isDuplicate(productPlanNo, serialNo);

		if (isDuplicate) {

			String serialName = MessageResourcesUtil.getMessage(locale(), "product.serialno");
			throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.duplicate", serialName));
		}
	}
	/**
	 * シリアルNo入力チェック
	 *
	 * @param productPlanNo
	 * @param barcode
	 * @throws ValidationException
	 */
	private void validateBarcodeNo(String productPlanNo, String barcode) throws ValidationException {

		boolean isDuplicate = dBarcodeLabelManagementService.isDuplicate(productPlanNo, barcode);

		if (isDuplicate) {

			String barcodeName = MessageResourcesUtil.getMessage(locale(), "product.barcode");
			throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.duplicate", barcodeName));
		}
	}
	/**
	 * シリアルNo入力チェック
	 *
	 * @param productPlanNo
	 * @param barcode
	 * @throws ValidationException
	 */
	private void validateQualityLabel(String productPlanNo, String barcode) throws ValidationException {

		boolean checkQualityLabel = dQualityLabelManagementService.checkQualityLabel(productPlanNo, barcode);

		if (checkQualityLabel) {

			String barcodeName = MessageResourcesUtil.getMessage(locale(), "product.barcode");
			throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.not.quality.label", barcodeName));
		}
	}
	
	/**
	 * 最新の製品実績データを取得
	 *
	 * @return エンティティ
	 */
	private DProcessProductResult readLatestProductResult(String productPlanNo, int processComponentNo, String serialNo) {

		Integer revision = dProcessProductResultService.getIncompleteMinRevision(productPlanNo, processComponentNo, serialNo);

		if (revision == null) {
			// 未完了の計画が無ければ、最終履歴を取得
			revision = dProcessProductResultService.getMaxRevision(productPlanNo, processComponentNo, serialNo);
		}

		DProcessProductResult dProcessProductResultRegistered =
				dProcessProductResultService.findProductResultsById(productPlanNo, processComponentNo, serialNo, revision);

		return dProcessProductResultRegistered;
	}

	/**
	 * 製品開始時検証処理
	 */
	@Execute(validator = false)
	public String productsValidationAtStart() {

		Map<String, String> json = new HashMap<>();

		try {

			final String productPlanNo = request.getParameter("productPlanNo");
			final int processNo = NumberUtils.toInt(request.getParameter("processComponentNo"), -1);
			final String productResultJson = StringUtils.trim(request.getParameter("updateData"));

			if (StringUtil.isBlank(productPlanNo) || processNo == -1 || StringUtil.isBlank(productResultJson)) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}

			dProductResultService.processStartPossible(productPlanNo);
			dProcessResultService.productStartPossible(productPlanNo, processNo);
			
			List<DProcessProductResultDto> productResults = DProcessProductResultConvert.toDProcessProductResultDto(productResultJson);
			
			validateSerialNo(productPlanNo, productResults);
			validateProcessProductData(productResults);

			if (SystemParameterUtil.productResultTracking()) {

				boolean isExistsPreviousResult = isExistsPreviousResult(productPlanNo, processNo, productResults);

				if (!isExistsPreviousResult) {
					json.put("result", "WARN");
					json.put("message", MessageResourcesUtil.getMessage(locale(), "info.product.result.previous.notexists"));
					return null;
				}
			}

			json.put("result", "OK");

		} catch (ValidationException ex) {
			json.put("result", "NG");
			json.put("message", ex.getMessage());
		} catch (MesException ex) {
			json.put("result", "NG");
			json.put("message", MessageResourcesUtil.getMessage(locale(), ex.getErrorMessageKey()));
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
	 * 製品終了時検証処理
	 */
	@Execute(validator = false)
	public String productsValidationAtEnd() {

		Map<String, String> json = new HashMap<>();

		try {

			final String productPlanNo = request.getParameter("productPlanNo");
			final int processNo = NumberUtils.toInt(request.getParameter("processComponentNo"), -1);
			final String productResultJson = StringUtils.trim(request.getParameter("updateData"));

			if (StringUtil.isBlank(productPlanNo) || processNo == -1 || StringUtil.isBlank(productResultJson)) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}

			List<DProcessProductResultDto> productResults = DProcessProductResultConvert.toDProcessProductResultDto(productResultJson);

			validateProcessProductData(productResults);

			boolean isAllProductsStarted = isAllProductsStarted(productPlanNo, processNo, productResults);
			if (!isAllProductsStarted) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.product.result.notstarted"));
			}

			json.put("result", "OK");

		} catch (IllegalStateException ex) {
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", ex.getMessage());
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

	/** 製品 開始 */
	@Execute(validator = false)
	public String productsStart() {

		boolean isCommit = false;

		try {

			final String productPlanNo = productResultInputForm.productPlanNo;
			final int processNo = NumberUtils.toInt(productResultInputForm.processComponentNo, -1);
			final String productResultJson = productResultInputForm.processProductResultJson;

			if (StringUtil.isBlank(productPlanNo) || processNo == -1 || StringUtil.isBlank(productResultJson)) {

				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}

			List<DProcessProductResultDto> productResults = DProcessProductResultConvert.toDProcessProductResultDto(productResultJson);
			
			validateSerialNo(productPlanNo, productResults);
			validateProcessProductData(productResults);
			dProcessResultService.processStart(productPlanNo, processNo);
			for (DProcessProductResultDto productResult : productResults) {

				processProductResultService.workStart(productPlanNo, processNo, productResult.serialNo);

				// 開始したデータのrevisionを取得
				Integer revision = dProcessProductResultService.getIncompleteMinRevision(productPlanNo, processNo, productResult.serialNo);
				DProcessProductResult dProductResult =
						DProcessProductResultConvert.toDProcessProductResult(productPlanNo, processNo, productResult);
				dProductResult.revision = revision;

				registerProductMaterial(dProductResult, productResult);
				registerProductSpec(dProductResult, productResult);
			}

			isCommit = true;

		} catch (ValidationException ex) {
			BrowserMsg.toAlert(ex.getMessage());
		} catch (MesException ex) {
			BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), ex.getErrorMessageKey()));
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

		return "/product/productResultInput/";
	}

	/** 前工程実績存在チェック */
	private boolean isExistsPreviousResult(String productPlanNo, int processComponentNo, List<DProcessProductResultDto> productResults) {

		for (DProcessProductResultDto productResult : productResults) {

			boolean isExistsPreviousResult =
					dProcessProductResultService.isExistsPreviousResult(productPlanNo, processComponentNo, productResult.serialNo);

			if (!isExistsPreviousResult) {
				return false;
			}
		}

		return true;
	}

	/** 製品 完了 */
	@Execute(validator = false)
	public String productsEnd() {

		boolean isCommit = false;

		try {

			final String productPlanNo = productResultInputForm.productPlanNo;
			final int processNo = NumberUtils.toInt(productResultInputForm.processComponentNo, -1);
			final String productResultJson = productResultInputForm.processProductResultJson;

			if (StringUtil.isBlank(productPlanNo) || processNo == -1 || StringUtil.isBlank(productResultJson)) {

				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}

			List<DProcessProductResultDto> dProductResultDtos = DProcessProductResultConvert.toDProcessProductResultDto(productResultJson);

			validateProcessProductData(dProductResultDtos);

			boolean isAllProductsStarted = isAllProductsStarted(productPlanNo, processNo, dProductResultDtos);

			if (!isAllProductsStarted) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.product.result.notstarted"));
			}

			for (DProcessProductResultDto dProductResultDto : dProductResultDtos) {
				if(processNo == SystemParameterUtil.platingValue())
				{
					DQualityLabelManagement dQualityLabelManagement = new DQualityLabelManagement();
					dQualityLabelManagement.barcodeNo = qualityBarcodeService.getQualityBarcode();
					dQualityLabelManagement.serialNo = dProductResultDto.serialNo;
					dQualityLabelManagement.productPlanNo = productPlanNo;
					dQualityLabelManagementService.insert(dQualityLabelManagement);
					qualityBarcodeService.printQualityBarcode(dQualityLabelManagement.barcodeNo, dProductResultDto.barcode);
				}
				// 開始中のrevisionを取得
				Integer revision = dProcessProductResultService.getIncompleteMinRevision(productPlanNo, processNo, dProductResultDto.serialNo);

				DProcessProductResult dProductResult = DProcessProductResultConvert.toDProcessProductResult(productPlanNo, processNo, dProductResultDto);
				dProductResult.revision = revision;

				registerProductMaterial(dProductResult, dProductResultDto);
				registerProductSpec(dProductResult, dProductResultDto);

				processProductResultService.workEnd(productPlanNo, processNo, dProductResultDto.serialNo, userDto.getUserId(), userDto.getUserName());
			}

			isCommit = true;

		} catch (IllegalStateException ex) {
			logger.error("Exception", ex);
			BrowserMsg.toAlert(ex.getMessage());
		} catch (ValidationException ex) {
			BrowserMsg.toAlert(ex.getMessage());
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

		return "/product/productResultInput/";
	}

	/** 製品開始済みチェック */
	private boolean isAllProductsStarted(String productPlanNo, int processComponentNo, List<DProcessProductResultDto> productResults) {

		for (DProcessProductResultDto productResult : productResults) {

			Integer revision = dProcessProductResultService.getIncompleteMinRevision(productPlanNo, processComponentNo, productResult.serialNo);

			if (revision == null) {
				return false;
			}
		}

		return true;
	}

	/** 製品実績削除 */
	@Execute(validator = false)
	public String productResultDelete() {

		boolean isCommit = false;

		try {

			final String productPlanNo = productResultInputForm.productPlanNo;
			final int processNo = NumberUtils.toInt(productResultInputForm.processComponentNo, -1);
			final String serialNo = productResultInputForm.serialNo;
			
			final int revision = NumberUtils.toInt(productResultInputForm.revision, -1);

			if (StringUtil.isBlank(productPlanNo) || processNo == -1 || StringUtil.isBlank(serialNo) || revision == -1) {

				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}
			if(processNo == SystemParameterUtil.deburringValue())
				dBarcodeLabelManagementService.deleteById(productPlanNo,serialNo);
			if(processNo == SystemParameterUtil.platingValue())
				dQualityLabelManagementService.deleteById(productPlanNo,serialNo);
			dProductResultService.deleteById(productPlanNo, processNo, serialNo, revision);

			reculcResultCount(productPlanNo, processNo);

			isCommit = true;

		} catch (ValidationException ex) {
			BrowserMsg.toAlert(ex.getMessage());
		} catch (Exception ex) {
			logger.error("Exception", ex);
			BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), "errors.delete"));
		} finally {

			if (!isCommit) {
				try {
					userTransaction.setRollbackOnly();
				} catch (IllegalStateException | SystemException ex) {
					logger.error("Exception", ex);
				}
			}
		}

		return "/product/productResultInput/";
	}
	
	@Execute(validator = false)
	public String productsEnter() {

		boolean isCommit = false;

		try {

			final String productPlanNo = productResultInputForm.productPlanNo;
			final int processNo = NumberUtils.toInt(productResultInputForm.processComponentNo, -1);
			final String productResultJson = productResultInputForm.processProductResultJson;

			if (StringUtil.isBlank(productPlanNo) || processNo == -1 || StringUtil.isBlank(productResultJson)) {

				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}
			
			List<DProcessProductResultDto> productResults = DProcessProductResultConvert.toDProcessProductResultDto(productResultJson);

			validateSerialNo(productPlanNo, productResults);
			validateProcessProductData(productResults);
			dProcessResultService.processStart(productPlanNo, processNo);
			for (DProcessProductResultDto productResult : productResults) {
				DBarcodeLabelManagement dBarcodeLabelManagement = new DBarcodeLabelManagement();
				dBarcodeLabelManagement.barcodeNo = productResult.barcode;
				dBarcodeLabelManagement.serialNo = productResult.serialNo;
				dBarcodeLabelManagement.productPlanNo = productPlanNo;
				dBarcodeLabelManagementService.insert(dBarcodeLabelManagement);
				processProductResultService.workStart(productPlanNo, processNo, productResult.serialNo);
				processProductResultService.workEnd(productPlanNo, processNo, productResult.serialNo, userDto.getUserId(), userDto.getUserName());
			}

			isCommit = true;

		} catch (ValidationException ex) {
			BrowserMsg.toAlert(ex.getMessage());
		} catch (MesException ex) {
			BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), ex.getErrorMessageKey()));
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

		return "/product/productResultInput/";
	}
	
	
	@Execute(validator = false)
	public String printQualityLabel() {

		Map<String, String> json = new HashMap<>();
		try {	
			final String productPlanNo = request.getParameter("productPlanNo");
			String qcBarcode = StringUtils.trim(request.getParameter("barcode"));
			String printerId = StringUtils.trim(request.getParameter("printId")); 
			String serialNo = "";
			if (StringUtil.isBlank(productPlanNo) || StringUtil.isBlank(qcBarcode)) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}
			VDBarcodeAssociationDbDto vDBarcodeAssociation  = vDBarcodeAssociationService.findByBarcode(qcBarcode, productPlanNo);
			if(vDBarcodeAssociation != null){
				serialNo = vDBarcodeAssociation.serialNo;
			}else{
				String barcodeName = MessageResourcesUtil.getMessage(locale(), "product.barcode");
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.not.quality.label", barcodeName));
			}
			validateQualityLabel(productPlanNo,qcBarcode);
			DBarcodeLabelManagement dBarcodeLabelManagement = dBarcodeLabelManagementService.findByProductPlanNoAndSerialNo(productPlanNo, serialNo);
			MPrinter printer = mPrinterService.findById(printerId);
			dQualtyLabelPrintQueueService.addQueueQualityLabel(printer.printerId, printer.printerType, qcBarcode, dBarcodeLabelManagement.barcodeNo);

			json.put("result", "OK");
		} catch (IllegalStateException ex) {
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", ex.getMessage());
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
	
	@Execute(validator = false)
	public String printShippingTicket(){
		Map<String, String> json = new HashMap<>();
		try {	
			String printerId = StringUtils.trim(request.getParameter("printId")); 
			String customerName =request.getParameter("customerName");
			String partNo = request.getParameter("partNo");
			String partName = request.getParameter("partName");
			String model = request.getParameter("model");
			String mfgDate = request.getParameter("mfgDate");
			String planCode = request.getParameter("planCode");
			String lotNo = request.getParameter("lotNo");
			String timeOfDelivery = request.getParameter("timeOfDelivery");
			String checker = request.getParameter("checker");
			String packingDate = request.getParameter("packingDate");
			String color = request.getParameter("color");
			String serialNo = request.getParameter("serialNo");
			String quantity = request.getParameter("quantity");
			String location = request.getParameter("location");
			String qualityRank = request.getParameter("qualityRank");
			
			MPrinter printer = mPrinterService.findById(printerId);
			dShippingTicketPrintQueueService.addQueueShippingTicket(printerId, printer.printerType,customerName,partNo,partName,model,mfgDate,planCode,
					lotNo,timeOfDelivery,checker,packingDate,color,serialNo,quantity,location,qualityRank);
			
			json.put("result", "OK");
		} catch (IllegalStateException ex) {
			logger.error("Exception", ex);
			json.put("result", "NG");
			json.put("message", ex.getMessage());
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

	@Execute(validator = false)
	public String productsCheck() {
		boolean isCommit = false;
		try {

			final String productPlanNo = productResultInputForm.productPlanNo;
			final int processNo = NumberUtils.toInt(productResultInputForm.processComponentNo, -1);
			final String productResultJson = productResultInputForm.processProductResultJson;

			if (StringUtil.isBlank(productPlanNo) || processNo == -1 || StringUtil.isBlank(productResultJson)) {
				throw new ValidationException(MessageResourcesUtil.getMessage(locale(), "errors.invalid.parameter"));
			}
			
			List<DProcessProductResultDto> productResults = DProcessProductResultConvert.toDProcessProductResultDto(productResultJson);

			validateSerialNo(productPlanNo, productResults);
		
			for (DProcessProductResultDto productResult : productResults) {
				processProductResultService.checkTime(productPlanNo, processNo, productResult.serialNo);
			}

			isCommit = true;

		} catch (ValidationException ex) {
			BrowserMsg.toAlert(ex.getMessage());
		} catch (MesException ex) {
			BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), ex.getErrorMessageKey()));
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

		return "/product/productResultInput/";
	}
}
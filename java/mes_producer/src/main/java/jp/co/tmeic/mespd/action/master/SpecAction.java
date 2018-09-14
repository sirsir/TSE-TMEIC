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
package jp.co.tmeic.mespd.action.master;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import jp.co.tmeic.mespd.action.AbstractAction;
import jp.co.tmeic.mespd.constant.ErrorForeignKey;
import jp.co.tmeic.mespd.dto.jqgrid.master.MSpecDto;
import jp.co.tmeic.mespd.entity.MSpec;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.master.SpecForm;
import jp.co.tmeic.mespd.service.MSpecAttributeService;
import jp.co.tmeic.mespd.service.MSpecService;
import jp.co.tmeic.mespd.utils.ArrayUtil;
import jp.co.tmeic.mespd.utils.JSONUtil;
import jp.co.tmeic.mespd.utils.JqgridUtil;
import jp.co.tmeic.mespd.utils.JqueryUtil;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth(authorityId = "operation")
public class SpecAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** UserTransaction */
	public UserTransaction userTransaction;

	/** ActionForm */
	@Resource
	@ActionForm
	protected SpecForm specForm;

	@Resource
	protected MSpecService mSpecService;

	@Resource
	protected MSpecAttributeService mSpecAttributeService;

	/** 検索 */
	@Execute(validator = false)
	public String index() {

		try {

			specForm.mAttributes = mSpecAttributeService.findAllOrderById();

			List<MSpecDto> specs = ArrayUtil.copy(MSpecDto.class, mSpecService.findAllOrderById());

			specForm.specJson = JSONUtil.encode(JqgridUtil.init(specs));

		} catch (Exception ex) {
			logger.error("Exception", ex);
		}

		return "index.jsp";
	}

	/** 登録 */
	@Execute(validator = false)
	public String register() {

		Map<String, String> json = new HashMap<>();

		boolean isCommit = false;

		try {
			List<MSpecDto> specs = JSONUtil.decode(specForm.specJson, MSpecDto[].class);

			registerValidate(specs);
			registerExe(specs);

			json.put("result", "OK");

			isCommit = true;

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
	 * 登録可能か検証を行う。
	 *
	 * @param specs
	 */
	private void registerValidate(List<MSpecDto> specs) {

		for (MSpecDto spec : specs) {

			ValidateUtil.required("spec.code", spec.specId);
			ValidateUtil.maxlength("spec.code", spec.specId, 10);

			ValidateUtil.required("spec.name", spec.specName);
			ValidateUtil.maxlength("spec.name", spec.specName, 30);

			ValidateUtil.required("attribute", spec.specAttributeId);
			ValidateUtil.integer("attribute", spec.specAttributeId);

			// 属性ID有無
			if (!mSpecAttributeService.isExist(NumberUtils.toInt(spec.specAttributeId))) {
				ValidateUtil.invalid("attribute");
			}

			validateMaxLengthOfSpecParts(
					spec.specParts01,
					spec.specParts02,
					spec.specParts03,
					spec.specParts04,
					spec.specParts05,
					spec.specParts06,
					spec.specParts07,
					spec.specParts08,
					spec.specParts09,
					spec.specParts10);
		}
	}

	/**
	 * 仕様入力値が登録可能か検証する。
	 *
	 * @param specPartses
	 */
	private void validateMaxLengthOfSpecParts(String... specPartses) {

		for (String specParts : specPartses) {
			ValidateUtil.maxlength("parts", specParts, SystemParameterUtil.specInputvalueMaxLength());
		}
	}

	/**
	 * 登録実行
	 *
	 * @param specs
	 */
	private void registerExe(List<MSpecDto> specs) {

		for (MSpecDto spec : specs) {

			MSpec mSpec = Beans.createAndCopy(MSpec.class, spec).execute();

			switch (spec.crud) {
				case "C":
					mSpecService.insert(mSpec);
					break;

				case "U":
					mSpecService.update(mSpec);
					break;
			}
		}
	}

	/** 削除 */
	@Execute(validator = false)
	public String delete() {

		Map<String, String> json = new HashMap<>();

		boolean isCommit = false;

		try {
			String specId = request.getParameter("deleteSpecId");

			mSpecService.deleteById(specId);

			json.put("result", "OK");

			isCommit = true;

		} catch (SQLRuntimeException ex) {

			String message = ErrorForeignKey.getMessage(locale(), ex);

			if (message == null) {
				message = MessageResourcesUtil.getMessage(locale(), "errors.delete");
			}

			logger.warn(message, ex);
			json.put("result", "NG");
			json.put("message", message);

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

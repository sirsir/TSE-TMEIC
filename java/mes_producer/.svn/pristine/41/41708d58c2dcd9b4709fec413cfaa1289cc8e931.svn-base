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
import jp.co.tmeic.mespd.dto.jqgrid.master.MMaterialDto;
import jp.co.tmeic.mespd.entity.MMaterial;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.master.MaterialForm;
import jp.co.tmeic.mespd.service.MMaterialService;
import jp.co.tmeic.mespd.utils.ArrayUtil;
import jp.co.tmeic.mespd.utils.JSONUtil;
import jp.co.tmeic.mespd.utils.JqgridUtil;
import jp.co.tmeic.mespd.utils.JqueryUtil;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;

import org.apache.log4j.Logger;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth(authorityId = "operation")
public class MaterialAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** UserTransaction */
	public UserTransaction userTransaction;

	/** ActionForm */
	@Resource
	@ActionForm
	protected MaterialForm materialForm;

	@Resource
	protected MMaterialService mMaterialService;

	/** 検索 */
	@Execute(validator = false)
	public String index() {

		try {

			List<MMaterialDto> materials = ArrayUtil.copy(MMaterialDto.class, mMaterialService.findAllOrderByIdOfNotProduct());

			materialForm.materialJson = JSONUtil.encode(JqgridUtil.init(materials));

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
			List<MMaterialDto> materials = JSONUtil.decode(materialForm.materialJson, MMaterialDto[].class);

			registerValidate(materials);
			registerExe(materials);

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
	 * @param mMaterialDtos
	 */
	private void registerValidate(List<MMaterialDto> materials) {

		for (MMaterialDto material : materials) {

			ValidateUtil.required("material.code", material.materialId);
			ValidateUtil.maxlength("material.code", material.materialId, SystemParameterUtil.materialIdMaxLength());

			ValidateUtil.required("material.name", material.materialName);
			ValidateUtil.maxlength("material.name", material.materialName, SystemParameterUtil.materialNameMaxLength());
		}
	}

	/**
	 * 登録実行
	 *
	 * @param mMaterialDtos
	 */
	private void registerExe(List<MMaterialDto> materials) {

		for (MMaterialDto material : materials) {

			MMaterial mMaterial = Beans.createAndCopy(MMaterial.class, material).execute();

			switch (material.crud) {
				case "C":
					mMaterialService.insert(mMaterial);
					break;

				case "U":
					mMaterialService.update(mMaterial);
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
			String materialId = request.getParameter("deleteMaterialId");

			mMaterialService.deleteById(materialId);

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

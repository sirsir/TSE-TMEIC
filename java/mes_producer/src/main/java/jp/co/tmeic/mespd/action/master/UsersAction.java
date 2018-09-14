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
import jp.co.tmeic.mespd.dto.jqgrid.master.MUsersRoleDto;
import jp.co.tmeic.mespd.entity.MUsers;
import jp.co.tmeic.mespd.entity.MUsersRole;
import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.form.master.UsersForm;
import jp.co.tmeic.mespd.service.MRoleService;
import jp.co.tmeic.mespd.service.MUsersRoleService;
import jp.co.tmeic.mespd.service.MUsersService;
import jp.co.tmeic.mespd.utils.JSONUtil;
import jp.co.tmeic.mespd.utils.JqgridUtil;
import jp.co.tmeic.mespd.utils.JqueryUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;

import org.apache.log4j.Logger;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.Beans;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth(authorityId = "operation")
public class UsersAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** UserTransaction */
	public UserTransaction userTransaction;

	/** ActionForm */
	@Resource
	@ActionForm
	protected UsersForm usersForm;

	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected MUsersService mUsersService;

	@Resource
	protected MUsersRoleService mUsersRoleService;

	@Resource
	protected MRoleService mRoleService;

	/** 検索 */
	@Execute(validator = false)
	public String index() {

		try {

			usersForm.mRoles = mRoleService.findAllOrderById();
			usersForm.usersJson = JSONUtil.encode(JqgridUtil.init(mUsersService.findVisibleOrderById()));

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
			List<MUsersRoleDto> users = JSONUtil.decode(usersForm.usersJson, MUsersRoleDto[].class);

			registerValidate(users);
			registerExe(users);

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
	 * @param users
	 */
	private void registerValidate(List<MUsersRoleDto> users) {

		for (MUsersRoleDto user : users) {

			ValidateUtil.required("user.code", user.userId);
			ValidateUtil.maxlength("user.code", user.userId, 10);

			ValidateUtil.required("user.name", user.userName);
			ValidateUtil.maxlength("user.name", user.userName, 50);

			ValidateUtil.required("password", user.userPassword);
			ValidateUtil.maxlength("password", user.userPassword, 10);

			ValidateUtil.required("authority", user.roleId);
		}
	}

	/** 登録実行 */
	private void registerExe(List<MUsersRoleDto> usersRoles) {

		for (MUsersRoleDto usersRole : usersRoles) {
			MUsers mUsers = Beans.createAndCopy(MUsers.class, usersRole).execute();

			switch (usersRole.crud) {
				case "C":
					mUsers.visibleFlag = true;

					mUsersService.insert(mUsers);

					insertOrUpdateMUsersRole(mUsers.userId, usersRole.roleId);

					break;

				case "U":

					mUsersService.update(mUsers);

					insertOrUpdateMUsersRole(mUsers.userId, usersRole.roleId);

					break;
			}
		}
	}

	/**
	 * 役割マスタを追加・更新する。
	 *
	 * @param userId
	 * @param roleId
	 */
	private void insertOrUpdateMUsersRole(String userId, String roleId) {

		MUsersRole usersRole = new MUsersRole();

		List<MUsersRole> mUsersRoles = mUsersRoleService.findByUserId(userId);

		if (mUsersRoles.size() > 0) {
			jdbcManager.deleteBatch(mUsersRoles).execute();
		}

		usersRole.userId = userId;
		usersRole.roleId = roleId;

		mUsersRoleService.insert(usersRole);
	}

	/** 削除 */
	@Execute(validator = false)
	public String delete() {

		Map<String, String> json = new HashMap<>();

		boolean isCommit = false;

		try {
			String userId = request.getParameter("deleteUserId");

			mUsersService.deleteById(userId);

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

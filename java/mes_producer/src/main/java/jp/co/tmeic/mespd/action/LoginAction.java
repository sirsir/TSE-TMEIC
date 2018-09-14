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
package jp.co.tmeic.mespd.action;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.dto.UserDto;
import jp.co.tmeic.mespd.form.LoginForm;
import jp.co.tmeic.mespd.service.MUsersService;
import jp.co.tmeic.mespd.utils.BrowserMsg;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

public class LoginAction extends AbstractAction {

	/** ActionForm */
	@Resource
	@ActionForm
	protected LoginForm loginForm;

	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected MUsersService mUsersService;

	@Resource
	protected UserDto userDto;

	@Execute(validator = false)
	public String index() {
		return "index.jsp";
	}

	/** ユーザ認証 */
	@Execute(validator = true, input = "index.jsp")
	public String login() {

		boolean isAuth = mUsersService.isAuth(loginForm.userCode, loginForm.password);

		if (!isAuth) {
			BrowserMsg.toAlert(MessageResourcesUtil.getMessage(locale(), "errors.login"));

			return "index.jsp";
		}

		userDto.mUsers = mUsersService.findByUserId(loginForm.userCode);

		return "/product/productResult/";
	}
}

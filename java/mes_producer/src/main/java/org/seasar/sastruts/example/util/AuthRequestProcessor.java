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
package org.seasar.sastruts.example.util;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tmeic.mespd.dto.UserDto;
import jp.co.tmeic.mespd.entity.MUsers;
import jp.co.tmeic.mespd.utils.BrowserMsg;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.action.S2RequestProcessor;
import org.seasar.struts.config.S2ExecuteConfig;
import org.seasar.struts.util.MessageResourcesUtil;
import org.seasar.struts.util.S2ExecuteConfigUtil;

/** 認証機能 */
public class AuthRequestProcessor extends S2RequestProcessor {

	@Override
	protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {

		S2ExecuteConfig executeConfig = S2ExecuteConfigUtil.getExecuteConfig();
		Annotation annotation = executeConfig.getMethod().getDeclaringClass().getAnnotation(Auth.class);

		if (annotation != null) {

			if (annotation.annotationType().getName().equals("org.seasar.sastruts.example.annotation.Auth")) {

				Auth auth = (Auth) annotation;

				UserDto user = SingletonS2Container.getComponent(UserDto.class);
				
				boolean isLogin = false;
	
				if(mapping.getName().equals("product_productProgressActionForm")&&user.mUsers==null)
				{   MUsers mUser = new MUsers();
					mUser.userId = "Progress";
					mUser.userName = "Progress";
					user.mUsers = mUser;
				}else if(user.mUsers!=null && user.mUsers.userId.equals("Progress") && !mapping.getName().equals("product_productProgressActionForm") ){
					user.mUsers = null;
				}
				
				if (user.mUsers != null) {
					isLogin = (user.mUsers.userId != null);
				}

				if (!isLogin) {

					((HttpServletResponse) response).sendRedirect(request.getContextPath() + "/login");

					return false;
				}

				if (StringUtils.isNotBlank(auth.authorityId())) {
					if (!user.authority(auth.authorityId())) {

						((HttpServletResponse) response).sendRedirect(request.getContextPath() + "/product/productResult");

						BrowserMsg.toAlert(MessageResourcesUtil.getMessage(request.getLocale(), "error.authority"));

						return false;
					}
				}
			}
		}

		return super.processRoles(request, response, mapping);
	}

}

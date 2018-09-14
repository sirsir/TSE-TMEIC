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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.action.AbstractAction;
import jp.co.tmeic.mespd.form.master.SystemManagerForm;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

@Auth(authorityId = "operation")
public class SystemManageAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/** ActionForm */
	@Resource
	@ActionForm
	protected SystemManagerForm systemManagerForm;

	@Execute(validator = false)
	public String index() {

		try {

			List<LabelValueBean> reportOption = new ArrayList<>();
			reportOption.add(new LabelValueBean(MessageResourcesUtil.getMessage(locale(), "product.report"), "1"));
			reportOption.add(new LabelValueBean(MessageResourcesUtil.getMessage(locale(), "material.report"), "2"));

			systemManagerForm.reportOptionItems = reportOption;
			systemManagerForm.outputReportId = "1";
			systemManagerForm.outputReportKind = "1";

		} catch (Exception ex) {
			logger.error("Exception", ex);
		}

		return "index.jsp";
	}
}

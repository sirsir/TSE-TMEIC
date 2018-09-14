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

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import jp.co.tmeic.mespd.action.AbstractAction;
import jp.co.tmeic.mespd.utils.DateUtil;
import jp.co.tmeic.mespd.utils.FileConvertUtil;
import jp.co.tmeic.mespd.utils.HttpDownloadFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.seasar.sastruts.example.annotation.Auth;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

@Auth
public abstract class AbstractReportAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 帳票の印刷要求を振り分けます。<br>
	 *
	 * @return
	 */
	@Execute(validator = false)
	public String index() {

		try {
			String kind = StringUtils.trim(request.getParameter("outputReportKind"));
			String date = request.getParameter("outputReportDate");

			if (!DateUtil.isDate(date)) {
				return null;
			}

			switch (kind) {
				case "1":
					monthlyReport(DateUtil.toDate(date));
					break;

				case "2":
					dailyReport(DateUtil.toDate(date));
					break;
			}

		} catch (Exception ex) {
			logger.error("Exception", ex);
		}

		return null;
	}

	/**
	 * テンプレートファイルをPDFに変換後、ダウンロードさせる。
	 *
	 * @param template
	 *            テンプレートファイルパス
	 * @param prames
	 *            テンプレートに書き込むデータ
	 * @param downloadFileName
	 *            ダウンロードファイル名
	 */
	protected void downloadReport(URL template, Map<String, Object> prames, String downloadFileName) {

		try {

			File pdfFile = FileConvertUtil.excelTemplateToPdf(template.getPath(), prames);

			if (pdfFile != null) {

				String fileName = HttpDownloadFile.fileNameEncode(downloadFileName);

				ResponseUtil.download(fileName, FileUtils.openInputStream(pdfFile));
				pdfFile.delete();
			}

		} catch (FileNotFoundException ex) {
			logger.error("FileNotFoundException", ex);
		} catch (Exception ex) {
			logger.error("Exception", ex);
		}
	}

	protected abstract void monthlyReport(Date date);

	protected abstract void dailyReport(Date date);
}

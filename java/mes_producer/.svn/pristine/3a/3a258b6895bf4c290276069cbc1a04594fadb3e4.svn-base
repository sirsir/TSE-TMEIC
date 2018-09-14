package jp.co.tmeic.mespd.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import net.sf.jett.transform.ExcelTransformer;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.seasar.struts.util.ServletContextUtil;

/** FileConvertUtil */
public final class FileConvertUtil {

	/** コンストラクタ */
	private FileConvertUtil() {
	}

	public static File excelTemplateToPdf(String templateFilePath, Map<String, Object> params)
			throws InvalidFormatException, IOException, InterruptedException {

		ServletContext context = ServletContextUtil.getServletContext();

		String workDirPath = context.getRealPath("/WEB-INF/work");

		File workDir = new File(workDirPath);
		workDir.mkdirs();

		ExcelTransformer transformer = new ExcelTransformer();
		transformer.setSilent(true);

		UUID uuid = UUID.randomUUID();
		String temporaryFileName = context.getRealPath("/WEB-INF/work/" + uuid.toString() + ".xls");

		transformer.transform(templateFilePath, temporaryFileName, params);

		File temporaryFile = new File(temporaryFileName);

		if (!temporaryFile.exists()) {
			return null;
		}

		String pdfFilePath = temporaryFileName.replace(".xls", ".pdf");

		excelToPdf(temporaryFileName, pdfFilePath);

		temporaryFile.delete();

		File pdfFile = new File(pdfFilePath);

		if (!pdfFile.exists()) {
			return null;
		}

		return pdfFile;
	}

	private static boolean excelToPdf(String srcFilePath, String distFilePath) throws IOException, InterruptedException {

		String script = ServletContextUtil.getServletContext().getRealPath("/WEB-INF/wsf/ExcelToPdf.wsf");

		ProcessBuilder processBuilder =
				new ProcessBuilder(
						"cmd",
						"/c",
						script,
						"/src:" + srcFilePath,
						"/dist:" + distFilePath);

		processBuilder.redirectErrorStream(true);

		Process process = processBuilder.start();
		InputStream is = process.getInputStream();

		try {
			while (true) {
				if (!(is.read() >= 0)) {
					break;
				}
			}
		} finally {
			IOUtils.closeQuietly(is);
		}

		File file = new File(distFilePath);

		return file.exists();
	}

}
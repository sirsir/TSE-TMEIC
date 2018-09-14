package jp.co.tmeic.mespd.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.struts.util.RequestUtil;
import org.seasar.struts.util.ResponseUtil;

/** jQueryUtil */
public final class JqueryUtil {

	/** コンストラクタ */
	private JqueryUtil() {
	}

	public static void callback(String data) {

		HttpServletRequest request = RequestUtil.getRequest();
		HttpServletResponse response = ResponseUtil.getResponse();

		String callback = request.getParameter("callback");

		response.setContentType("application/json; charset=utf-8");

		ResponseUtil.write(String.format("%s(%s)", callback, data));
	}
}

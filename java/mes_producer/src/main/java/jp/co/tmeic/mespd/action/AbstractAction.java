package jp.co.tmeic.mespd.action;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.tmeic.mespd.dto.UserDto;

public abstract class AbstractAction {

	/** request */
	@Resource
	protected HttpServletRequest request;

	/** response */
	@Resource
	protected HttpServletResponse response;

	/** session */
	@Resource
	protected HttpSession session;

	@Resource
	protected UserDto userDto;

	protected Locale locale() {
		return request.getLocale();
	}
}

package jp.co.tmeic.mespd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.seasar.sastruts.example.util.NoLoginException;
import org.seasar.struts.util.ServletContextUtil;

/** 認証エラーフィルタ */
public class AuthFilter implements Filter {

	/** フィルタ設定 */
	@SuppressWarnings("unused")
	private FilterConfig filterConfig;

	/** フィルタ初期化 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/** フィルタ破棄 */
	public void destroy() {
		this.filterConfig = null;
	}

	/** フィルタ */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		try {
			chain.doFilter(req, res);

		} catch (NoLoginException ex) {

			ServletContext context = ServletContextUtil.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/login");
			dispatcher.forward(req, res);
		}
	}
}

package jp.co.tmeic.mespd.utils;

import javax.servlet.http.HttpSession;

import org.seasar.struts.util.MessageResourcesUtil;
import org.seasar.struts.util.RequestUtil;

/** ブラウザ側への出力クラス */
public final class BrowserMsg {

	/** コンストラクター */
	private BrowserMsg() {
	}

	/**
	 * ブラウザ側にメッセージを出力する。
	 *
	 * @param dialog
	 *            ダイアログクラス
	 */
	public static void toMessage(DialogBean dialog) {
		HttpSession session = RequestUtil.getRequest().getSession();

		// セッションにエラーメッセージをセットする(Dialogタグで出力)
		session.setAttribute("dialogInfo", JSONUtil.encode(dialog));
	}

	/**
	 * ブラウザ側に情報を出力する。
	 *
	 * @param title
	 *            タイトル名
	 * @param msg
	 *            メッセージ
	 */
	public static void toInfo(String title, String msg) {
		toMessage(new DialogBean(DialogBean.ICON_INFO, title, msg));
	}

	/**
	 * ブラウザ側に警告を出力する。
	 *
	 * @param msg
	 *            メッセージ
	 */
	public static void toAlert(String msg) {

		String title = MessageResourcesUtil.getMessage(RequestUtil.getRequest().getLocale(), "errors.alert");

		toAlert(title, msg);
	}

	/**
	 * ブラウザ側に警告を出力する。
	 *
	 * @param title
	 *            タイトル名
	 * @param msg
	 *            メッセージ
	 */
	public static void toAlert(String title, String msg) {
		toMessage(new DialogBean(DialogBean.ICON_ERR, title, msg));
	}

	/**
	 * ブラウザ側に警告を出力する。
	 *
	 * @param title
	 *            タイトル名
	 * @param msg
	 *            メッセージ
	 * @param width
	 *            ウィンドウ幅
	 * @param height
	 *            ウィンドウ高さ
	 */
	public static void toAlert(String title, String msg, int width, int height) {
		toMessage(new DialogBean(DialogBean.ICON_ERR, title, msg, width, height));
	}
}

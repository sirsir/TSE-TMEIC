/*
 * COPYRIGHT (C) HarborSoftWare
 */

package jp.co.tmeic.mespd.utils;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * ファイルダウンロード機能
 *
 * @author 小野(HSC)
 * @version 2010.12.1
 * @since 2010.12.1
 */
public final class HttpDownloadFile {

	/** エンコーティング */
	private static String encode = "Shift_JIS";

	/** コンストラクタ */
	private HttpDownloadFile() {

	}

	/**
	 * 対象のバイト配列をファイルの保存ダイアログで保存させる。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param strFileName
	 *            デフォルトの保存ファイル名
	 * @param buffer
	 *            保存するバイト配列 想定外の例外
	 * @throws Exception
	 *             想定外の例外
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String strFileName, byte[] buffer) throws Exception {

		// リクエストの文字エンコードを取得
		String encoding = request.getCharacterEncoding();
		if ((encoding != null) && (encoding.equalsIgnoreCase(encode))) {
			response.setContentType("text/html; charset=" + encode);
		}

		// 応答にファイルを設定
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-disposition", "attachment; filename=" + new String(strFileName.getBytes(encode), "iso8859-1"));
		// ファイル文字コードを設定
		response.setCharacterEncoding(encode);

		// 出力ストリーム
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

		// 文字列をバイナリで送信
		bos.write(buffer);

		// 出力ストリームを閉じる
		bos.close();
	}

	/**
	 * 対象のExcelWorkbookをファイルの保存ダイアログで保存させる。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param strFileName
	 *            デフォルトの保存ファイル名
	 * @param workbook
	 *            保存するExcelファイル
	 * @throws Exception
	 *             想定外の例外
	 */
	public static void downloadExcelFile(
			HttpServletRequest request, HttpServletResponse response, String strFileName, Workbook workbook) throws Exception {

		// リクエストの文字エンコードを取得
		String encoding = request.getCharacterEncoding();
		if ((encoding != null) && (encoding.equalsIgnoreCase(encode))) {
			response.setContentType("text/html; charset=" + encode);
		}

		// 応答にファイルを設定
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-disposition", "attachment; filename=" + new String(strFileName.getBytes(encode), "iso8859-1"));

		// ファイル文字コードを設定
		response.setCharacterEncoding(encode);

		OutputStream outStream = response.getOutputStream();
		workbook.write(outStream);
	}

	/**
	 * ファイル名をエンコードする
	 *
	 * @param fileName
	 * @throws UnsupportedEncodingException
	 */
	public static String fileNameEncode(String fileName) throws UnsupportedEncodingException {
		return new String(fileName.getBytes(encode), "iso8859-1");
	}

}

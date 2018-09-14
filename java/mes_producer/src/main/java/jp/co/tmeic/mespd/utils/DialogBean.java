/*
 * COPYRIGHT (C) HarborSoftWare
 */
package jp.co.tmeic.mespd.utils;

import java.io.Serializable;

/**
 * ダイアログ情報
 *
 */
public class DialogBean implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** アイコン(情報) */
	public static final String ICON_INFO = "ui-icon-info";

	/** アイコン(異常) */
	public static final String ICON_ERR = "ui-icon-alert";

	/** 自動表示 */
	public boolean autoOpen = true;

	/** ボタン */
	public String buttons = "{}";

	/** Escキークローズ */
	public boolean closeOnEscape = true;

	/** クローズテキスト */
	public String closeText = "close";

	/** ダイアログクラス */
	public String dialogClass = "";

	/** 無効化 */
	public boolean disabled = false;

	/** ドラッグ有効 */
	public boolean draggable = true;

	/** 縦幅 */
	public String height = "auto";

	/** クローズエフェクト */
	public String hide = null;

	/** 最大縦幅 */
	public int maxHeight = 0;

	/** 最大横幅 */
	public int maxWidth = 0;

	/** 最小縦幅 */
	public int minHeight = 150;

	/** 最小横幅 */
	public int minWidth = 150;

	/** モーダル */
	public boolean modal = false;

	/** 表示位置 */
	public String position = "{ my: 'center', at: 'center', of: window }";

	/** リサイズ有効 */
	public boolean resizable = true;

	/** オープンエフェクト */
	public String show = null;

	/** 重ね表示 */
	public boolean stack = true;

	/** タイトル */
	public String title;

	/** 横幅 */
	public String width = "auto";

	/** 奥行き */
	public int zIndex;

	/** アイコン */
	private String icon;

	/** メッセージ */
	private String message;

	/**
	 * コンストラクター
	 *
	 * @param icon
	 *            アイコン
	 * @param title
	 *            タイトル
	 * @param message
	 *            メッセージ
	 * @param width
	 *            ダイアログ横幅
	 * @param height
	 *            ダイアログ縦幅
	 */
	public DialogBean(String icon, String title, String message, int width, int height) {
		this.icon = icon;
		this.title = title;
		this.message = message;
		this.width = String.valueOf(width);
		this.height = String.valueOf(height);
	}

	/**
	 * コンストラクター
	 *
	 * @param icon
	 *            アイコン
	 * @param title
	 *            タイトル
	 * @param message
	 *            メッセージ
	 */
	public DialogBean(String icon, String title, String message) {
		this.icon = icon;
		this.title = title;
		this.message = message;
	}

	/** アイコンを取得します。 */
	public String getIcon() {
		return icon;
	}

	/** アイコンを設定します。 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/** タイトル文言を取得します。 */
	public String getTitle() {
		return title;
	}

	/** タイトル文言を設定します。 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** メッセージを取得します。 */
	public String getMessage() {
		return message;
	}

	/** メッセージを設定します。 */
	public void setMessage(String message) {
		this.message = message;
	}
}

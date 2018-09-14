package jp.co.tmeic.mespd.constant;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.framework.util.tiger.Maps;
import org.seasar.struts.util.MessageResourcesUtil;

public final class ErrorForeignKey {

	/** コンストラクタ */
	private ErrorForeignKey() {
	}

	/** 外部キー(部材構成マスタ) */
	public static final String FKEY_M_MATERIAL_COMPONENT_MATERIAL_ID = "m_material_component_material_id_fkey";

	/** 外部キー一覧 */
	private static Map<String, String> foreignKeys =
			Maps.hashMap(FKEY_M_MATERIAL_COMPONENT_MATERIAL_ID, "errors.fkey.m_material_component_material_id")
					.$("m_spec_process_component_spec_id_fkey", "errors.fkey.m_spec_process_component_spec_id")
					.$("m_spec_product_component_spec_id_fkey", "errors.fkey.m_spec_product_component_spec_id")
					.$();

	/**
	 * 外部キーによるエラーの場合、メッセージを返却する。
	 *
	 * @param locale
	 * @param ex
	 * @return メッセージ
	 */
	public static String getMessage(Locale locale, SQLRuntimeException ex) {

		String foreignKey = getForeignKey(ex);

		if (foreignKey == null) {
			return null;
		}

		return MessageResourcesUtil.getMessage(locale, foreignKeys.get(foreignKey));
	}

	/**
	 * 外部キーによるエラーの場合、外部キーを返却する。
	 *
	 * @param ex
	 * @return 外部キー
	 */
	public static String getForeignKey(SQLRuntimeException ex) {

		for (String key : foreignKeys.keySet()) {

			if (StringUtils.indexOf(ex.getMessage(), key) >= 0) {
				return key;
			}
		}

		return null;
	}

}

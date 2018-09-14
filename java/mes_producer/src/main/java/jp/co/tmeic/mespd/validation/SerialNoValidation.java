package jp.co.tmeic.mespd.validation;

import jp.co.tmeic.mespd.exception.ValidationException;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;
import jp.co.tmeic.mespd.utils.ValidateUtil;

public final class SerialNoValidation {

	/** コンストラクタ */
	private SerialNoValidation() {
	}

	public static void validate(String serialNo) throws ValidationException {

		ValidateUtil.required("product.serialno", serialNo);

		int serialNoMaxLength = SystemParameterUtil.productSerialnoMaxLength();
		ValidateUtil.maxlength("product.serialno", serialNo, serialNoMaxLength);
	}
}

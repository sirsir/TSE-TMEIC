package jp.co.tmeic.mespd.utils;

import java.sql.Time;
import java.util.Properties;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.TimeConversionUtil;

/** SystemParameterUtil */
public final class SystemParameterUtil {

	/** コンストラクタ */
	private SystemParameterUtil() {
	}

	private static String getProperty(String propertyName) {

		Properties properties = ResourceUtil.getProperties("system.properties");
		return properties.getProperty(propertyName);
	}

	/** 工程数 */
	public static Properties getProperty() {
		return ResourceUtil.getProperties("system.properties");
	}

	/** 製品単位仕様数 */
	public static int processMaxSize() {
		return NumberUtils.toInt(getProperty("process.max.size"), 10);
	}

	/** 工程単位仕様数 */
	public static int productSpecMaxSize() {
		return NumberUtils.toInt(getProperty("product.spec.max.size"), 10);
	}

	/** 工程単位仕様数 */
	public static int processSpecMaxSize() {
		return NumberUtils.toInt(getProperty("process.spec.max.size"), 10);
	}

	/** 使用部材数 */
	public static int materialMaxSize() {
		return NumberUtils.toInt(getProperty("material.max.size"), 5);
	}

	/** 部材ID最大桁数 */
	public static int materialIdMaxLength() {
		return NumberUtils.toInt(getProperty("material.id.max.length"), 10);
	}

	/** 部材名最大桁数 */
	public static int materialNameMaxLength() {
		return NumberUtils.toInt(getProperty("material.name.max.length"), 30);
	}

	/** 部材数量最大桁数 */
	public static int materialQuantityMaxLength() {
		return NumberUtils.toInt(getProperty("material.quantity.max.length"), 4);
	}

	/** 製造計画表示日数 */
	public static int productDisplayDays() {
		return NumberUtils.toInt(getProperty("product.display.days"), 7);
	}

	/** 製造計画一覧自動更新有無初期値 */
	public static boolean productAutoupdate() {
		return BooleanUtils.toBoolean(getProperty("product.autoupdate"));
	}

	/** 製造計画一覧自動更新周期(秒) */
	public static int productAutoupdateInterval() {

		int interval = NumberUtils.toInt(getProperty("product.autoupdate.interval"), 0);

		if (interval <= 0) {
			return 10 * 1000;
		}

		return interval * 1000;
	}
	
	public static int arrivalNoNumberingMethod() {
		return NumberUtils.toInt(getProperty("arrival.no.numbering.method"), 1);
	}
	
	public static int shippingNoNumberingMethod() {
		return NumberUtils.toInt(getProperty("shipping.no.numbering.method"), 1);
	}

	/** シリアルNo最大桁数 */
	public static int productSerialnoMaxLength() {
		return NumberUtils.toInt(getProperty("product.serialno.max.length"), 12);
	}
	
	/** シリアルNo最大桁数 */
	public static int productBarcodeMaxLength() {
		return NumberUtils.toInt(getProperty("product.barcode.max.length"), 6);
	}
	
	/** 数量最大桁数 */
	public static int productQuantityLabelMaxLength() {
		return NumberUtils.toInt(getProperty("product.quantity.label.max.length"), 5);
	}

	/** 数量最大桁数 */
	public static int productQuantityMaxLength() {
		return NumberUtils.toInt(getProperty("product.quantity.max.length"), 4);
	}

	/** 製造計画No採番方式 */
	public static int productPlanNoNumberingMethod() {
		return NumberUtils.toInt(getProperty("product.plan.no.numbering.method"), 1);
	}

	/** シリアルNo採番方式 */
	public static int serialNoNumberingMethod() {
		return NumberUtils.toInt(getProperty("serial.no.numbering.method"), 1);
	}

	/** 実績トレース */
	public static boolean productResultTracking() {

		String value = StringUtils.lowerCase(getProperty("product.result.tracking"));

		Boolean doTracking = BooleanUtils.toBooleanObject(value);

		if (doTracking == null) {
			return true;
		}

		return doTracking.booleanValue();
	}

	/** 進捗状況自動更新有無初期値 */
	public static boolean progressAutoupdate() {
		return BooleanUtils.toBoolean(getProperty("progress.autoupdate"));
	}

	/** 進捗状況自動更新周期 */
	public static int progressUpdateInterval() {
		return NumberUtils.toInt(getProperty("progress.autoupdate.interval"), 60) * 1000;
	}

	/** 進捗状況自動更新表示日数 */
	public static int progressAutoupdateDisplayDates() {
		return NumberUtils.toInt(getProperty("progress.autoupdate.display.dates"), 1);
	}

	/** 進捗状況始点時刻 */
	public static Time progressGanttStartingPointTime() {

		String time = getProperty("progress.gantt.starting.point.time");

		try {
			return TimeConversionUtil.toTime(time, "HH:mm");
		} catch (Exception ex) {
			return TimeConversionUtil.toTime("00:00:00", "HH:mm:ss");
		}
	}

	/** 進捗状況表示制限日数 */
	public static int progressGanttDisplayLimitDays() {
		return NumberUtils.toInt(getProperty("progress.gantt.display.limit.days"), 31);
	}

	/** 仕様入力値最大桁数 */
	public static int specInputvalueMaxLength() {
		return NumberUtils.toInt(getProperty("spec.inputvalue.max.length"), 30);
	}

	/** ハンディパスワード有無 */
	public static boolean passwordRequired() {

		String value = StringUtils.lowerCase(getProperty("password.required"));

		Boolean required = BooleanUtils.toBooleanObject(value);

		if (required == null) {
			return true;
		}

		return required.booleanValue();
	}

	public static int deburringValue(){
		return 1;
	}
	
	public static int platingValue(){
		return 4;
	}
	
	public static int pkcqcValue(){
		return 5;
	}
}

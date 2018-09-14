package jp.co.tmeic.mespd.utils;

import java.util.Date;
import java.util.Locale;

import jp.co.tmeic.mespd.exception.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.seasar.struts.util.MessageResourcesUtil;
import org.seasar.struts.util.RequestUtil;

public final class ValidateUtil {

	private ValidateUtil() {
	}

	/**
	 * @param resourceName
	 * @param value
	 * @throws ValidationException
	 */
	public static void required(String resourceName, String value) {

		Locale locale = RequestUtil.getRequest().getLocale();

		if (StringUtils.isNotBlank(value)) {
			return;
		}

		throw new ValidationException(
				MessageResourcesUtil.getMessage(locale, "errors.required", MessageResourcesUtil.getMessage(locale, resourceName)));
	}

	/**
	 * @param resourceName
	 * @param value
	 * @param length
	 * @throws ValidationException
	 */
	public static void maxlength(String resourceName, String value, int length) {

		Locale locale = RequestUtil.getRequest().getLocale();

		if (StringUtils.length(value) <= length) {
			return;
		}

		throw new ValidationException(
				MessageResourcesUtil.getMessage(locale, "errors.maxlength", MessageResourcesUtil.getMessage(locale, resourceName), length));
	}

	/**
	 * @param resourceName
	 * @param value
	 * @param length
	 * @throws ValidationException
	 */
	public static void length(String resourceName, String value, int length) {

		Locale locale = RequestUtil.getRequest().getLocale();

		if (StringUtils.length(value) == length) {
			return;
		}

		throw new ValidationException(
				MessageResourcesUtil.getMessage(locale, "errors.length", MessageResourcesUtil.getMessage(locale, resourceName), length));
	}

	/**
	 * @param resourceName
	 * @param value
	 * @throws ValidationException
	 */
	public static void integer(String resourceName, String value) {

		if (StringUtils.isBlank(value)) {
			return;
		}

		Locale locale = RequestUtil.getRequest().getLocale();

		try {
			Integer.parseInt(value);
		} catch (Exception ex) {
			throw new ValidationException(
					MessageResourcesUtil.getMessage(locale, "errors.integer", MessageResourcesUtil.getMessage(locale, resourceName)));
		}

		return;
	}

	/**
	 * @param resourceName
	 * @param value
	 * @throws ValidationException
	 */
	public static void isNumeric(String resourceName, String value) {

		Locale locale = RequestUtil.getRequest().getLocale();

		if (!NumberUtils.isNumber(value)) {
			throw new ValidationException(
					MessageResourcesUtil.getMessage(locale, "errors.integer", MessageResourcesUtil.getMessage(locale, resourceName)));
		}

		return;
	}

	/**
	 * @param resourceName
	 * @param value
	 * @param minValue
	 * @throws ValidationException
	 */
	public static void minInteger(String resourceName, String value, int minValue) {

		if (StringUtils.isBlank(value)) {
			return;
		}

		integer(resourceName, value);
		Locale locale = RequestUtil.getRequest().getLocale();

		if (Integer.parseInt(value) < minValue) {
			throw new ValidationException(
					MessageResourcesUtil.getMessage(locale, "errors.integer.min", MessageResourcesUtil.getMessage(locale, resourceName), minValue));
		}
	}

	/**
	 * @param resourceName
	 * @param value
	 * @throws ValidationException
	 */
	public static void integerOrBlank(String resourceName, String value) {

		if (StringUtils.isBlank(value)) {
			return;
		}

		integer(resourceName, value);
	}

	/**
	 * @param resourceName
	 * @param value
	 * @throws ValidationException
	 */
	public static void numericOrBlank(String resourceName, String value) {

		if (StringUtils.isBlank(value)) {
			return;
		}

		isNumeric(resourceName, value);
	}

	/**
	 * @param resourceName
	 * @param value
	 * @throws ValidationException
	 */
	public static void date(String resourceName, String value) {

		Locale locale = RequestUtil.getRequest().getLocale();

		if (DateUtil.isDate(value)) {
			return;
		}

		throw new ValidationException(
				MessageResourcesUtil.getMessage(locale, "errors.date", MessageResourcesUtil.getMessage(locale, resourceName)));
	}

	/**
	 * @param resourceName
	 * @param value
	 * @throws ValidationException
	 */
	public static void dateOrBlank(String resourceName, String value) {

		if (StringUtils.isBlank(value)) {
			return;
		}

		date(resourceName, value);
	}

	/**
	 * @param resourceName
	 * @param startDate
	 * @param endDate
	 * @param targetDate
	 * @throws ValidationException
	 */
	public static void outOfRange(String resourceName, Date startDate, Date endDate, Date targetDate) {

		Locale locale = RequestUtil.getRequest().getLocale();

		if (DateUtil.ge(targetDate, startDate) && DateUtil.le(targetDate, endDate)) {
			return;
		}

		throw new ValidationException(
				MessageResourcesUtil.getMessage(locale, "errors.out.of.range", MessageResourcesUtil.getMessage(locale, resourceName)));
	}

	/**
	 * @param resourceName
	 * @param limit
	 *            表示可能日数
	 * @param value
	 *            表示日数
	 * @throws ValidationException
	 */
	public static void displayLimitDays(String resourceName, long limit, long value) {

		Locale locale = RequestUtil.getRequest().getLocale();

		if (value >= limit) {
			throw new ValidationException(MessageResourcesUtil.getMessage(locale, "errors.display.limit.days", limit));
		}
	}

	/**
	 * @param arg
	 * @throws ValidationException
	 */
	public static void invalid(String arg) {

		Locale locale = RequestUtil.getRequest().getLocale();

		throw new ValidationException(
				MessageResourcesUtil.getMessage(locale, "errors.invalid", MessageResourcesUtil.getMessage(locale, arg)));
	}

	/**
	 * @param arg
	 * @throws ValidationException
	 */
	public static void duplicate(String arg) {

		Locale locale = RequestUtil.getRequest().getLocale();

		throw new ValidationException(
				MessageResourcesUtil.getMessage(locale, "errors.duplicate", MessageResourcesUtil.getMessage(locale, arg)));
	}
}

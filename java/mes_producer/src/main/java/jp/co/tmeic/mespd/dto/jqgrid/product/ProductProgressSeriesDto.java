package jp.co.tmeic.mespd.dto.jqgrid.product;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 全体進捗状況表示
 *
 */
public class ProductProgressSeriesDto {

	public String id;

	public String name;

	public String text;

	public String getStart() {

		if (startDate == null) {
			return StringUtils.EMPTY;
		}

		return DateFormatUtils.format(startDate, "dd/MM/yyyy HH:mm:ss");
	}

	public String getEnd() {

		if (endDate == null) {
			return StringUtils.EMPTY;
		}

		return DateFormatUtils.format(endDate, "dd/MM/yyyy HH:mm:ss");
	}

	public String color;

	public Date startDate;

	public Date endDate;

	public boolean getReadOnly() {
		return true;
	}
}

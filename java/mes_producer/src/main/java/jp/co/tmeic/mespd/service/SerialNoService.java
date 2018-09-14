package jp.co.tmeic.mespd.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.constant.NoNumberingMethod;
import jp.co.tmeic.mespd.entity.DSerialNo;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.util.StringUtil;

/**
 * SerialNoServiceのサービスクラスです。
 *
 */
public class SerialNoService {

	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected DSerialNoService dSerialNoService;

	@Resource
	protected DSerialNoCountService dSerialNoCountService;

	@Resource
	protected DProcessProductResultService dProcessProductResultService;

	/**
	 * シリアルNoを生成します。
	 *
	 * @return 生成したシリアルNo
	 */
	public String createSerialNo() {

		String headerId = DateFormatUtils.format(new Date(), "yyMMdd");

		while (true) {

			long count = dSerialNoCountService.countUp(headerId);
			String serialNo = String.format("%s%06d", headerId, count);

			DSerialNo dSerialNo = dSerialNoService.findById(serialNo);

			if (dSerialNo == null) {
				dSerialNo = new DSerialNo();
				dSerialNo.serialNo = serialNo;
				dSerialNoService.insert(dSerialNo);

				return serialNo;
			}
		}
	}

	/**
	 * 製造計画内でシリアル番号が重複しているか確認します。
	 *
	 * @param productPlanNo
	 * @param serialNo
	 * @return
	 */
	public boolean isDuplicate(String productPlanNo, String serialNo) {

		Map<String, Object> param = new HashMap<>();

		param.put("productPlanNo", productPlanNo);
		param.put("serialNo", serialNo);

		long duplicateCount =
				jdbcManager.getCountBySqlFile(
						"jp/co/tmeic/mespd/service/SerialNoService_isDuplicate.sql",
						param);

		return duplicateCount != 0;
	}

	/**
	 * シリアル自動採番要否判定
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @param serialNo
	 * @return True:シリアル自動採番が必要 False:シリアル自動採番は不要
	 */
	public boolean useAutoNumberSerial(String productPlanNo, int processComponentNo, String serialNo) {

		Integer previousProcessNo = dProcessProductResultService.getPreviousProcessComponentNo(productPlanNo, processComponentNo);

		boolean isTopProcess = (previousProcessNo == null);
		boolean isResultTracking = SystemParameterUtil.productResultTracking();
		boolean isAutoNumberSerial = (SystemParameterUtil.serialNoNumberingMethod() == NoNumberingMethod.AUTO);

		return useAutoNumberSerial(isTopProcess, isResultTracking, isAutoNumberSerial, serialNo);
	}

	/**
	 * シリアル自動採番要否判定
	 *
	 * @param isResultTracking
	 * @param isAutoNumberSerial
	 * @param serialNo
	 * @return True:シリアル自動採番が必要 False:シリアル自動採番は不要
	 */
	private boolean useAutoNumberSerial(boolean isTopProcess, boolean isResultTracking, boolean isAutoNumberSerial, String serialNo) {

		if (!isResultTracking) {
			return (StringUtil.isBlank(serialNo));
		}

		if (isAutoNumberSerial) {

			if (isTopProcess && StringUtil.isBlank(serialNo)) {
				return true;
			}
		}

		return false;
	}

}
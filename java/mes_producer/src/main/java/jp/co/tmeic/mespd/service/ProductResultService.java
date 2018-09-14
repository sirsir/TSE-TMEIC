package jp.co.tmeic.mespd.service;

import java.sql.Timestamp;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.entity.DProcessResult;

import org.seasar.extension.jdbc.JdbcManager;

/**
 * 製造実績のサービスクラスです。
 *
 */
public class ProductResultService {

	/** jdbcManager */
	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected DProcessPlanService dProcessPlanService;

	@Resource
	protected DProcessResultService dProcessResultService;

	/**
	 * 製造計画Noの実績数を取得します。<br>
	 * ※最終工程の実績数が対象です。
	 *
	 * @param productPlanNo
	 * @return
	 */
	public int resultQty(String productPlanNo) {

		Integer processesNo = dProcessPlanService.findLastProcessesComponentNo(productPlanNo);

		if (processesNo == null) {
			return 0;
		}

		DProcessResult dProcessResult = dProcessResultService.findById(productPlanNo, processesNo);

		if (dProcessResult == null) {
			return 0;
		} else if (dProcessResult.resultQty == null) {
			return 0;
		}

		return dProcessResult.resultQty;
	}

	/**
	 * 製造計画の最終実績日時を取得します。
	 *
	 * @param productPlanNo
	 *            製造計画No
	 * @return 製造計画の最終実績日時
	 */
	public Timestamp resultLastdate(String productPlanNo) {

		return jdbcManager
				.selectBySql(
						Timestamp.class,
						"SELECT MAX(product_result_lastdate) FROM d_process_result WHERE product_plan_no = ?",
						productPlanNo)
				.getSingleResult();
	}
}
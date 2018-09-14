package jp.co.tmeic.mespd.service;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.exception.MesException;

/**
 * ProcessResultServiceのサービスクラスです。
 *
 */
public class ProcessResultService {

	@Resource
	protected DProductResultService dProductResultService;

	@Resource
	protected DProcessResultService dProcessResultService;

	/**
	 * 指定の製造計画No、工程の状態を開始に変更する。<br>
	 * ※製造計画が開始されていない場合は、例外を発生させる。
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @return 状態の変更結果
	 * @exception MesException
	 */
	public boolean processStart(String productPlanNo, Integer processComponentNo) throws MesException {

		dProductResultService.processStartPossible(productPlanNo);
		return dProcessResultService.processStart(productPlanNo, processComponentNo);
	}

	/**
	 * 指定の製造計画No、工程の状態を終了に変更する。
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @return 状態の変更結果
	 */
	public boolean processComplet(String productPlanNo, Integer processComponentNo) {

		return dProcessResultService.processComplet(productPlanNo, processComponentNo);
	}

}
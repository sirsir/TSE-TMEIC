package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DProcessResultNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.constant.MesErrorMessageKey;
import jp.co.tmeic.mespd.constant.ProcessStatus;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.exception.MesException;
import jp.co.tmeic.mespd.utils.TimestampUtil;

/**
 * {@link DProcessResult}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DProcessResultService extends AbstractService<DProcessResult> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @return エンティティ
	 */
	public DProcessResult findById(String productPlanNo, Integer processComponentNo) {
		return select().id(productPlanNo, processComponentNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DProcessResult> findAllOrderById() {
		return select().orderBy(asc(productPlanNo()), asc(processComponentNo())).getResultList();
	}

	/**
	 * 指定製造計画No、工程の状態を取得します。
	 *
	 * @param productPlanNo
	 *            製造計画No
	 * @param processComponentNo
	 *            工程構成No
	 * @return 状態
	 */
	public int getStatus(String productPlanNo, Integer processComponentNo) {

		DProcessResult dProcessResult = select().id(productPlanNo, processComponentNo).getSingleResult();

		if (dProcessResult == null) {
			return ProcessStatus.NONE;
		}

		return dProcessResult.status;
	}

	/**
	 * 製品単位の作業が開始可能か確認します。開始できない場合は、例外を発生させる。
	 *
	 * @param productPlanNo
	 *            製造計画No
	 * @param processComponentNo
	 *            工程構成No
	 * @exception MesException
	 */
	public void productStartPossible(String productPlanNo, Integer processComponentNo) throws MesException {

		int status = getStatus(productPlanNo, processComponentNo);

		switch (status) {
			case ProcessStatus.COMPLETE:
				throw new MesException("Process planning, it has been finished.", MesErrorMessageKey.PROCESS_PLAN_FINISHED);
		}
	}

	/**
	 * 指定の製造計画No、工程の状態を開始に変更する。
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @return 状態の変更結果
	 */
	public boolean processStart(String productPlanNo, Integer processComponentNo) {

		DProcessResult dProcessResult = findById(productPlanNo, processComponentNo);

		boolean isNewRow = false;

		if (dProcessResult == null) {

			isNewRow = true;

			dProcessResult = new DProcessResult();
			dProcessResult.productPlanNo = productPlanNo;
			dProcessResult.processComponentNo = processComponentNo;
			dProcessResult.status = ProcessStatus.NONE;
		}

		switch (dProcessResult.status) {

			case ProcessStatus.START:
				return true;

			case ProcessStatus.NONE:
			case ProcessStatus.COMPLETE:

				dProcessResult.status = ProcessStatus.START;

				if (dProcessResult.startDatetime == null) {
					dProcessResult.startDatetime = TimestampUtil.now();
				}

				if (isNewRow) {
					insert(dProcessResult);
				} else {
					update(dProcessResult);
				}

				return true;
		}

		return false;
	}

	/**
	 * 指定の製造計画No、工程の状態を終了に変更する。
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @return 状態の変更結果
	 */
	public boolean processComplet(String productPlanNo, Integer processComponentNo) {
		
		DProcessResult dProcessResult = findById(productPlanNo, processComponentNo);
		
		switch (dProcessResult.status) {

			case ProcessStatus.START:
				dProcessResult.status = ProcessStatus.COMPLETE;
				
				
				
				dProcessResult.endDatetime = TimestampUtil.now();

				update(dProcessResult);

				return true;

			case ProcessStatus.COMPLETE:
				return true;
		}

		return false;
	}
	
	
	

	/**
	 * データの挿入、または更新を行います。
	 *
	 * @param entity
	 */
	public void insertOrUpdate(DProcessResult entity) {

		boolean isExist = isExist(entity.productPlanNo, entity.processComponentNo);

		if (isExist) {
			update(entity);
		} else {
			insert(entity);
		}
	}

	/**
	 * データの挿入、または更新を行います。
	 *
	 * @param entities
	 */
	public void insertOrUpdate(List<DProcessResult> entities) {

		for (DProcessResult entity : entities) {
			insertOrUpdate(entity);
		}
	}
	
	


	
}
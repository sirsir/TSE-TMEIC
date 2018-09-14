package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DProcessPlanNames.processComponentNo;
import static jp.co.tmeic.mespd.entity.DProcessPlanNames.productPlanNo;
import static jp.co.tmeic.mespd.entity.MProcessNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import jp.co.tmeic.mespd.entity.DProcessPlan;
import jp.co.tmeic.mespd.entity.MProcess;

/**
 * {@link MProcess}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MProcessService extends AbstractService<MProcess> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param processId
	 *            識別子
	 * @return エンティティ
	 */
	public MProcess findById(String processId) {
		return select().id(processId).getSingleResult();
	}
	
	
	/**
	 * 工程名の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public MProcess findByProcessName(String processName) {
		//return select().processName(processName).getSingleList();
		return select()
				.where(new SimpleWhere().eq(processName(), processName))
				.getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MProcess> findAllOrderById() {
		return select().orderBy(asc(processId())).getResultList();
	}
	
	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MProcess> findAllHandyProcess() {
		//return select()..orderBy(asc(productPlanNo()), asc(processComponentNo())).getResultList();

		return select()
				.where(new SimpleWhere().eq(handyManaged(), true))
				.orderBy(asc(processId()))
				.getResultList();
	}


	/**
	 * 工程名の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MProcess> findAllOrderByProcessName() {
		return select().orderBy(asc(processName())).getResultList();
	}

	/**
	 * 工程マスタの名称一覧を取得します。
	 *
	 * @return
	 */
	public Map<String, String> getLabelValue() {

		Map<String, String> map = new LinkedHashMap<>();
		List<MProcess> mProcesses = findAllOrderByProcessName();

		for (MProcess mProcess : mProcesses) {
			map.put(mProcess.processId, mProcess.processName);
		}

		return map;
	}
}
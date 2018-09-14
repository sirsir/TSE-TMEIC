package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MSpecNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MSpec;

/**
 * {@link MSpec}のサービスクラスです。
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MSpecService extends AbstractService<MSpec> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param specId
	 *            識別子
	 * @return エンティティ
	 */
	public MSpec findById(String specId) {
		return select().id(specId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MSpec> findAllOrderById() {
		return select().orderBy(asc(specId())).getResultList();
	}

	/**
	 * 仕様マスタの名称一覧を取得します。
	 *
	 * @return
	 */
	public Map<String, String> getLabelValue() {

		Map<String, String> map = new LinkedHashMap<>();
		List<MSpec> mSpecs = findAllOrderById();

		for (MSpec mSpec : mSpecs) {
			map.put(mSpec.specId, mSpec.specName);
		}

		return map;
	}

	/**
	 * 識別子を条件に削除します。
	 *
	 * @param specId
	 * @return 削除件数
	 */
	public int deleteById(String specId) {
		return jdbcManager
				.updateBySql("DELETE FROM m_spec WHERE spec_id=?", String.class)
				.params(specId)
				.execute();
	}

	/**
	 * 部分的なNo
	 *
	 * @param partNo
	 * @return エンティティ
	 */
	public List<MSpec> findByPartNo(String partNo) {

		Map<String, Object> param = new HashMap<>();
		param.put("partNo", partNo);

		return jdbcManager.selectBySqlFile(
				MSpec.class,
				"jp/co/tmeic/mespd/service/MSpecService_findByPartNo.sql",
				param)
				.getResultList();
	}

}
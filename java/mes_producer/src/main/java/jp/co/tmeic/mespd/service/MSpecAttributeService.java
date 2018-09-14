package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MSpecAttributeNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MSpecAttribute;

/**
 * {@link MSpecAttribute}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MSpecAttributeService extends AbstractService<MSpecAttribute> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param specAttributeId
	 *            識別子
	 * @return エンティティ
	 */
	public MSpecAttribute findById(Integer specAttributeId) {
		return select().id(specAttributeId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MSpecAttribute> findAllOrderById() {
		return select().orderBy(asc(specAttributeId())).getResultList();
	}

	/**
	 * 部分的なNo
	 *
	 * @param partNo
	 * @return エンティティ
	 */
	public List<MSpecAttribute> findByPartNo(String partNo) {

		Map<String, Object> param = new HashMap<>();
		param.put("partNo", partNo);

		return jdbcManager.selectBySqlFile(
				MSpecAttribute.class,
				"jp/co/tmeic/mespd/service/MSpecAttributeService_findByPartNo.sql",
				param)
				.getResultList();
	}
}
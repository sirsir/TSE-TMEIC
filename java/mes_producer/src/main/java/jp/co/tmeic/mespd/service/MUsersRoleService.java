package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MUsersRoleNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MUsersRole;

import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link MUsersRole}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MUsersRoleService extends AbstractService<MUsersRole> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param userId
	 *            識別子
	 * @param roleId
	 *            識別子
	 * @return エンティティ
	 */
	public MUsersRole findById(String userId, String roleId) {
		return select().id(userId, roleId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MUsersRole> findAllOrderById() {
		return select().orderBy(asc(userId()), asc(roleId())).getResultList();
	}

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param userId
	 *            識別子
	 * @return エンティティ
	 */
	public List<MUsersRole> findByUserId(String userId) {
		return select()
				.where(new SimpleWhere()
						.eq(userId(), userId))
				.orderBy(asc(userId()))
				.getResultList();
	}
}
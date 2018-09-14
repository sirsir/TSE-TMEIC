package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MProductNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MProduct;

import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link MProduct}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MProductService extends AbstractService<MProduct> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param partNo
	 *            識別子
	 * @return エンティティ
	 */
	public MProduct findById(String partNo) {
		return select().id(partNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MProduct> findAllOrderById() {
		return select().orderBy(asc(partNo())).getResultList();
	}

	/**
	 * 製品IDが登録済みかチェックします。
	 *
	 * @param partNo
	 *            製品ID
	 * @return 登録有無
	 */
	public boolean isExist(String partNo) {
		return (select().id(partNo).getSingleResult() != null);
	}

	/**
	 * 製品IDでエンティティを検索します。
	 *
	 * @param partNo
	 *            部分的なNo
	 * @return エンティティのリスト
	 */
	public MProduct findByPartNo(String partNo) {
		return select()
				.leftOuterJoin(MProcessComponentList())
				.leftOuterJoin(MProcessComponentList().MProcess())
				.leftOuterJoin(MProcessComponentList().MSpecProductComponentList())
				.leftOuterJoin(MProcessComponentList().MSpecProductComponentList().MSpec())
				.leftOuterJoin(MProcessComponentList().MSpecProductComponentList().MSpec().MSpecAttribute())
				.leftOuterJoin(MProcessComponentList().MSpecProcessComponentList())
				.leftOuterJoin(MProcessComponentList().MSpecProcessComponentList().MSpec())
				.leftOuterJoin(MProcessComponentList().MSpecProcessComponentList().MSpec().MSpecAttribute())
				.leftOuterJoin(MProcessComponentList().MMaterialComponentList())
				.leftOuterJoin(MProcessComponentList().MMaterialComponentList().MMaterial())
				.leftOuterJoin(MProcessComponentList().MBeforeProcessList())
				.where(new SimpleWhere()
						.eq(partNo(), partNo))
				.orderBy(asc(MProcessComponentList().processComponentNo()))
				.getSingleResult();
	}

	/**
	 * 指定の製造計画Noで計画データを削除します。
	 *
	 * @param partNo
	 * @return
	 */
	public int deleteById(String partNo) {

		jdbcManager
				.updateBySql("DELETE FROM m_before_process WHERE part_no=?", String.class)
				.params(partNo)
				.execute();

		jdbcManager
				.updateBySql("DELETE FROM m_material_component WHERE part_no=?", String.class)
				.params(partNo)
				.execute();

		jdbcManager
				.updateBySql("DELETE FROM m_spec_product_component WHERE part_no=?", String.class)
				.params(partNo)
				.execute();

		jdbcManager
				.updateBySql("DELETE FROM m_spec_process_component WHERE part_no=?", String.class)
				.params(partNo)
				.execute();

		jdbcManager
				.updateBySql("DELETE FROM m_process_component WHERE part_no=?", String.class)
				.params(partNo)
				.execute();

		jdbcManager
				.updateBySql("DELETE FROM m_material WHERE material_id=?", String.class)
				.params(partNo)
				.execute();

		return jdbcManager
				.updateBySql("DELETE FROM m_product WHERE part_no=?", String.class)
				.params(partNo)
				.execute();
	}
}
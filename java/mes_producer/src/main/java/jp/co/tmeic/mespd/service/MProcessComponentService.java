package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MProcessComponentNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MProcessComponent;

import org.apache.commons.lang3.StringUtils;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link MProcessComponent}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MProcessComponentService extends AbstractService<MProcessComponent> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param partNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @return エンティティ
	 */
	public MProcessComponent findById(String partNo, Integer processComponentNo) {
		return select().id(partNo, processComponentNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MProcessComponent> findAllOrderById() {
		return select().orderBy(asc(partNo()), asc(processComponentNo())).getResultList();
	}

	/**
	 * 製品マスタに工程、仕様、部材を連結する検索条件を返す。
	 *
	 * @return 製品マスタの関連テーブル連結条件
	 */
	private AutoSelect<MProcessComponent> product() {
		return select()
				.leftOuterJoin(MSpecProductComponentList())
				.leftOuterJoin(MSpecProductComponentList().MSpec())
				.leftOuterJoin(MSpecProcessComponentList())
				.leftOuterJoin(MSpecProcessComponentList().MSpec())
				.leftOuterJoin(MMaterialComponentList())
				.leftOuterJoin(MMaterialComponentList().MMaterial());
	}

	/**
	 * 製品IDでエンティティを検索します。
	 *
	 * @param partNo
	 *            部分的なNo
	 * @return エンティティのリスト
	 */
	public List<MProcessComponent> findByPartNo(String partNo) {
		return product()
				.where(new SimpleWhere()
						.eq(partNo(), partNo))
				.orderBy(
						asc(processComponentNo()),
						asc(MSpecProductComponentList().displayOrder()),
						asc(MSpecProcessComponentList().displayOrder()),
						asc(MMaterialComponentList().displayOrder()))
				.getResultList();
	}

	/**
	 * 製品IDでエンティティを検索し、工程順で並び替えて取得します。
	 *
	 * @param partNo
	 *            製品ID
	 * @return エンティティのリスト
	 */
	public List<MProcessComponent> findByPartNoOrderByProcessOrder(String partNo) {
		return product()
				.where(new SimpleWhere()
						.eq(partNo(), partNo))
				.orderBy(
						asc(processOrder()),
						asc(MSpecProductComponentList().displayOrder()),
						asc(MSpecProcessComponentList().displayOrder()),
						asc(MMaterialComponentList().displayOrder()))
				.getResultList();
	}

	/**
	 * @param mProcessComponent
	 * @return
	 */
	public int register(MProcessComponent mProcessComponent) {

		if (StringUtils.isBlank(mProcessComponent.processId)) {
			deleteById(mProcessComponent.partNo, mProcessComponent.processComponentNo);
			return 0;
		}

		if (mProcessComponent.processComponentNo == null) {
			mProcessComponent.processComponentNo = getNewProcessComponentNo(mProcessComponent.partNo);
		}

		boolean isExist = isExist(mProcessComponent.partNo, mProcessComponent.processComponentNo);

		if (!isExist) {
			return insert(mProcessComponent);
		}

		return update(mProcessComponent);
	}

	/**
	 * @param partNo
	 * @return
	 */
	private int getNewProcessComponentNo(String partNo) {

		Integer componentNo = max("SELECT MAX(process_component_no) FROM m_process_component WHERE part_no=?", partNo);

		return componentNo + 1;
	}

	/**
	 * 指定の製造計画No、工程構成Noで工程データを削除します。
	 *
	 * @param partNo
	 * @param processComponentNo
	 * @return
	 */
	public int deleteById(String partNo, Integer processComponentNo) {

		jdbcManager
				.updateBySql("DELETE FROM m_material_component WHERE part_no=? AND process_component_no=?", String.class, Integer.class)
				.params(partNo, processComponentNo)
				.execute();

		jdbcManager
				.updateBySql("DELETE FROM m_spec_product_component WHERE part_no=? AND process_component_no=?", String.class, Integer.class)
				.params(partNo, processComponentNo)
				.execute();

		jdbcManager
				.updateBySql("DELETE FROM m_spec_process_component WHERE part_no=? AND process_component_no=?", String.class, Integer.class)
				.params(partNo, processComponentNo)
				.execute();

		return jdbcManager
				.updateBySql("DELETE FROM m_process_component WHERE part_no	=? AND process_component_no=?", String.class, Integer.class)
				.params(partNo, processComponentNo)
				.execute();
	}

}
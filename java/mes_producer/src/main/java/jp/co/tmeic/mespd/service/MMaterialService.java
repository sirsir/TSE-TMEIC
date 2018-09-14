package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MMaterialNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MMaterial;

/**
 * {@link MMaterial}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MMaterialService extends AbstractService<MMaterial> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param materialId
	 *            識別子
	 * @return エンティティ
	 */
	public MMaterial findById(String materialId) {
		return select().id(materialId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MMaterial> findAllOrderById() {
		return select().orderBy(asc(materialId())).getResultList();
	}

	/**
	 * 部材マスタの名称一覧を取得します。
	 *
	 * @return
	 */
	public Map<String, String> getLabelValue() {

		Map<String, String> map = new LinkedHashMap<>();
		List<MMaterial> mMaterials = findAllOrderById();

		for (MMaterial mMaterial : mMaterials) {
			map.put(mMaterial.materialId, mMaterial.materialName);
		}

		return map;
	}

	/**
	 * 識別子を条件に削除します。
	 *
	 * @param materialId
	 * @return 削除件数
	 */
	public int deleteById(String materialId) {
		return jdbcManager
				.updateBySql("DELETE FROM m_material WHERE material_id=?", String.class)
				.params(materialId)
				.execute();
	}

	/**
	 * 部材マスタの名称一覧を取得します。
	 *
	 * @return エンティティリスト
	 */
	public List<MMaterial> findAllOrderByIdOfNotProduct() {
		String sql = "SELECT * FROM m_material WHERE NOT EXISTS(SELECT * FROM m_product WHERE m_product.part_no = m_material.material_id)"
				+ " ORDER BY m_material.material_id ASC";

		return jdbcManager
				.selectBySql(MMaterial.class, sql).getResultList();
	}
}
package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MMaterialComponentNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MMaterialComponent;

import org.apache.commons.lang3.StringUtils;

/**
 * {@link MMaterialComponent}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MMaterialComponentService extends AbstractService<MMaterialComponent> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param partNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @param materialComponentNo
	 *            識別子
	 * @return エンティティ
	 */
	public MMaterialComponent findById(String partNo, Integer processComponentNo, Integer materialComponentNo) {
		return select().id(partNo, processComponentNo, materialComponentNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MMaterialComponent> findAllOrderById() {
		return select().orderBy(asc(partNo()), asc(processComponentNo()), asc(materialComponentNo())).getResultList();
	}

	/**
	 * @param mMaterialComponent
	 * @return
	 */
	public int register(MMaterialComponent mMaterialComponent) {

		if (StringUtils.isBlank(mMaterialComponent.materialId)) {
			delete(mMaterialComponent);
			return 0;
		}

		if (mMaterialComponent.materialComponentNo == null) {
			mMaterialComponent.materialComponentNo = getNewSpecComponentNo(mMaterialComponent.partNo, mMaterialComponent.processComponentNo);
			mMaterialComponent.displayOrder = getMaxDisplayOrder(mMaterialComponent.partNo, mMaterialComponent.processComponentNo);
		}

		boolean isExist = isExist(mMaterialComponent.partNo, mMaterialComponent.processComponentNo, mMaterialComponent.materialComponentNo);

		if (!isExist) {
			return insert(mMaterialComponent);
		}

		return update(mMaterialComponent);
	}

	/**
	 * @param partNo
	 * @param materialComponentNo
	 * @return
	 */
	private int getNewSpecComponentNo(String partNo, Integer materialComponentNo) {

		Integer componentNo = max(
				"SELECT MAX(material_component_no) FROM m_material_component WHERE part_no=? AND process_component_no=?",
				partNo, materialComponentNo);

		return componentNo + 1;
	}

	/**
	 * @param partNo
	 * @param materialComponentNo
	 * @return
	 */
	private int getMaxDisplayOrder(String partNo, Integer materialComponentNo) {

		Integer displayOrder = max(
				"SELECT MAX(display_order) FROM m_material_component WHERE part_no=? AND process_component_no=?",
				partNo, materialComponentNo);

		return displayOrder + 1;
	}
}
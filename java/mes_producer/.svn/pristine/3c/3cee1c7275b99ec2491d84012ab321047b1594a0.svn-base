package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MSpecProcessComponentNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MSpecProcessComponent;

import org.apache.commons.lang3.StringUtils;

/**
 * {@link MSpecProcessComponent}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MSpecProcessComponentService extends AbstractService<MSpecProcessComponent> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param partNo
	 *            識別子
	 * @param processComponentNo
	 *            識別子
	 * @param specComponentNo
	 *            識別子
	 * @return エンティティ
	 */
	public MSpecProcessComponent findById(String partNo, Integer processComponentNo, Integer specComponentNo) {
		return select().id(partNo, processComponentNo, specComponentNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MSpecProcessComponent> findAllOrderById() {
		return select().orderBy(asc(partNo()), asc(processComponentNo()), asc(specComponentNo())).getResultList();
	}

	/**
	 * @param mSpecProcessComponent
	 * @return
	 */
	public int register(MSpecProcessComponent mSpecProcessComponent) {

		if (StringUtils.isBlank(mSpecProcessComponent.specId)) {
			delete(mSpecProcessComponent);
			return 0;
		}

		if (mSpecProcessComponent.specComponentNo == null) {
			mSpecProcessComponent.specComponentNo = getNewSpecComponentNo(mSpecProcessComponent.partNo, mSpecProcessComponent.processComponentNo);
			mSpecProcessComponent.displayOrder = getMaxDisplayOrder(mSpecProcessComponent.partNo, mSpecProcessComponent.processComponentNo);
		}

		boolean isExist = isExist(mSpecProcessComponent.partNo, mSpecProcessComponent.processComponentNo, mSpecProcessComponent.specComponentNo);

		if (!isExist) {
			return insert(mSpecProcessComponent);
		}

		return update(mSpecProcessComponent);
	}

	/**
	 * @param partNo
	 * @param processComponentNo
	 * @return
	 */
	private int getNewSpecComponentNo(String partNo, Integer processComponentNo) {

		Integer componentNo = max(
				"SELECT MAX(spec_component_no) FROM m_spec_process_component WHERE part_no=? AND process_component_no=?",
				partNo, processComponentNo);

		return componentNo + 1;
	}

	/**
	 * @param partNo
	 * @param processComponentNo
	 * @return
	 */
	private int getMaxDisplayOrder(String partNo, Integer processComponentNo) {

		Integer displayOrder = max(
				"SELECT MAX(display_order) FROM m_spec_process_component WHERE part_no=? AND process_component_no=?",
				partNo, processComponentNo);

		return displayOrder + 1;
	}
}
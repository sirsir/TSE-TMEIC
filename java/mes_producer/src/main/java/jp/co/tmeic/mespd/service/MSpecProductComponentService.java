package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.MSpecProductComponentNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MSpecProductComponent;

import org.apache.commons.lang3.StringUtils;
import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link MSpecProductComponent}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class MSpecProductComponentService extends AbstractService<MSpecProductComponent> {

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
	public MSpecProductComponent findById(String partNo, Integer processComponentNo, Integer specComponentNo) {
		return select().id(partNo, processComponentNo, specComponentNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<MSpecProductComponent> findAllOrderById() {
		return select().orderBy(asc(partNo()), asc(processComponentNo()), asc(specComponentNo())).getResultList();
	}

	/**
	 * @param partNo
	 * @param processComponentNo
	 * @return
	 */
	public List<MSpecProductComponent> findByProcessComponentNo(String partNo, Integer processComponentNo) {
		return select()
				.where(new SimpleWhere()
						.eq(partNo(), partNo)
						.eq(processComponentNo(), processComponentNo))
				.orderBy(asc(specComponentNo()))
				.getResultList();
	}

	/**
	 * @param mSpecProductComponent
	 * @return
	 */
	public int register(MSpecProductComponent mSpecProductComponent) {

		if (StringUtils.isBlank(mSpecProductComponent.specId)) {
			delete(mSpecProductComponent);
			return 0;
		}

		if (mSpecProductComponent.specComponentNo == null) {
			mSpecProductComponent.specComponentNo = getNewSpecComponentNo(mSpecProductComponent.partNo, mSpecProductComponent.processComponentNo);
			mSpecProductComponent.displayOrder = getMaxDisplayOrder(mSpecProductComponent.partNo, mSpecProductComponent.processComponentNo);
		}

		boolean isExist = isExist(mSpecProductComponent.partNo, mSpecProductComponent.processComponentNo, mSpecProductComponent.specComponentNo);

		if (!isExist) {
			return insert(mSpecProductComponent);
		}

		return update(mSpecProductComponent);
	}

	/**
	 * @param partNo
	 * @param processComponentNo
	 * @return
	 */
	private int getNewSpecComponentNo(String partNo, Integer processComponentNo) {

		Integer componentNo = max(
				"SELECT MAX(spec_component_no) FROM m_spec_product_component WHERE part_no=? AND process_component_no=?",
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
				"SELECT MAX(display_order) FROM m_spec_product_component WHERE part_no=? AND process_component_no=?",
				partNo, processComponentNo);

		return displayOrder + 1;
	}
}
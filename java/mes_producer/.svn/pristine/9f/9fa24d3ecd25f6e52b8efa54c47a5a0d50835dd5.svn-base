package jp.co.tmeic.mespd.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.annotation.Resource;

import jp.co.tmeic.mespd.dto.jqgrid.product.DShippingDto;
import jp.co.tmeic.mespd.entity.DShippingPlan;
import jp.co.tmeic.mespd.entity.DSpecAttributePlan;
import jp.co.tmeic.mespd.entity.DSpecPlan;
import jp.co.tmeic.mespd.entity.MSpec;
import jp.co.tmeic.mespd.entity.MSpecAttribute;
import jp.co.tmeic.mespd.utils.ArrayUtil;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.Beans;

/**
 * {@link DShippingPlan}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class ShippingService {

	/** jdbcManager */
	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected MProductService mProductService;

	@Resource
	protected MSpecService mSpecService;

	@Resource
	protected MSpecAttributeService mSpecAttributeService;

	@Resource
	protected DShippingService dShippingService;

	@Resource
	protected DSpecPlanService dSpecPlanService;

	@Resource
	protected DSpecAttributePlanService dSpecAttributePlanService;


	/**
	 * 私は生産の日付に出荷計画を検索する。
	 *
	 * @param startTime
	 *            検索開始日
	 * @param endTime
	 *            検索終了日
	 * @return 製造計画
	 */
	public List<DShippingDto> findByShippingDate(Date startTime, Date endTime) {

		return ArrayUtil.copy(
				DShippingDto.class,
				dShippingService.findByShippingDate(startTime, endTime));
	}

	/**
	 * 出荷計画の計画データはいいえ明示して作られる。
	 *
	 * @param shippingNo
	 */
	public void createPlanData(String shippingNo) {

		DShippingPlan dShipping = dShippingService.findById(shippingNo);
		//MProduct mProduct = mProductService.findByPartNo(dShipping.partNo);
		//dShipping.productName = mProduct.productName;
		//dShipping.productKind = mProduct.productKind;

		dShippingService.update(dShipping);

		// 仕様属性計画
		List<MSpecAttribute> mSpecAttributes = mSpecAttributeService.findByPartNo(dShipping.partNo);

		for (MSpecAttribute mSpecAttribute : mSpecAttributes) {

			DSpecAttributePlan dSpecAttributePlan = Beans.createAndCopy(DSpecAttributePlan.class, mSpecAttribute).execute();
			dSpecAttributePlan.productPlanNo = shippingNo;

			dSpecAttributePlanService.insert(dSpecAttributePlan);
		}

		// 仕様計画
		List<MSpec> mSpecs = mSpecService.findByPartNo(dShipping.partNo);

		for (MSpec mSpec : mSpecs) {

			DSpecPlan dSpecPlan = Beans.createAndCopy(DSpecPlan.class, mSpec).execute();
			dSpecPlan.productPlanNo = shippingNo;

			dSpecPlanService.insert(dSpecPlan);
		}
	}

}
package jp.co.tmeic.mespd.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.annotation.Resource;

import jp.co.tmeic.mespd.dto.jqgrid.product.DArrivalDto;
import jp.co.tmeic.mespd.entity.DArrivalPlan;
import jp.co.tmeic.mespd.entity.DSpecAttributePlan;
import jp.co.tmeic.mespd.entity.DSpecPlan;
import jp.co.tmeic.mespd.entity.MSpec;
import jp.co.tmeic.mespd.entity.MSpecAttribute;
import jp.co.tmeic.mespd.utils.ArrayUtil;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.Beans;

/**
 * {@link DArrivalPlan}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class ArrivalService {

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
	protected DArrivalService dArrivalService;

	@Resource
	protected DSpecPlanService dSpecPlanService;

	@Resource
	protected DSpecAttributePlanService dSpecAttributePlanService;


	/**
	 * 私は生産の日付に到着の計画を検索する。
	 *
	 * @param startTime
	 *            検索開始日
	 * @param endTime
	 *            検索終了日
	 * @return 製造計画
	 */
	public List<DArrivalDto> findByArrivalDate(Date startTime, Date endTime) {

		return ArrayUtil.copy(
				DArrivalDto.class,
				dArrivalService.findByArrivalDate(startTime, endTime));
	}

	/**
	 * 到着の計画の計画データはいいえ明示して作られる。
	 *
	 * @param arrivalNo
	 */
	public void createPlanData(String arrivalNo) {

		DArrivalPlan dArrival = dArrivalService.findById(arrivalNo);
		//MProduct mProduct = mProductService.findByPartNo(dArrival.productId);
		//dArrival.productName = mProduct.productName;
		//dArrival.productKind = mProduct.productKind;

		dArrivalService.update(dArrival);

		// 仕様属性計画
		List<MSpecAttribute> mSpecAttributes = mSpecAttributeService.findByPartNo(dArrival.partNo);

		for (MSpecAttribute mSpecAttribute : mSpecAttributes) {

			DSpecAttributePlan dSpecAttributePlan = Beans.createAndCopy(DSpecAttributePlan.class, mSpecAttribute).execute();
			dSpecAttributePlan.productPlanNo = arrivalNo;

			dSpecAttributePlanService.insert(dSpecAttributePlan);
		}

		// 仕様計画
		List<MSpec> mSpecs = mSpecService.findByPartNo(dArrival.partNo);

		for (MSpec mSpec : mSpecs) {

			DSpecPlan dSpecPlan = Beans.createAndCopy(DSpecPlan.class, mSpec).execute();
			dSpecPlan.productPlanNo = arrivalNo;

			dSpecPlanService.insert(dSpecPlan);
		}
	}

}
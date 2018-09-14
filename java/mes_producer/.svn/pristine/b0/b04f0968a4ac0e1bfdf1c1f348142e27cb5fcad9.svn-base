package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DProductPlanNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.constant.ProductStatus;
import jp.co.tmeic.mespd.dto.jqgrid.product.DProductPlanDbDto;
import jp.co.tmeic.mespd.entity.DProductPlan;
import jp.co.tmeic.mespd.entity.DProductResult;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link DProductPlan}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DProductPlanService extends AbstractService<DProductPlan> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @return エンティティ
	 */
	public DProductPlan findById(String productPlanNo) {
		return select().id(productPlanNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DProductPlan> findAllOrderById() {
		return select().orderBy(asc(productPlanNo())).getResultList();
	}

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param productPlanNo
	 *            識別子
	 * @return エンティティ
	 */
	public DProductPlan findByProductPlanNo(String productPlanNo) {
		return select()
				.id(productPlanNo)
				.leftOuterJoin(DProcessPlanList())
				.leftOuterJoin(DProcessPlanList().DMaterialPlanList())
				.orderBy(
						asc(productPlanNo()),
						asc(DProcessPlanList().processComponentNo()),
						asc(DProcessPlanList().DMaterialPlanList().materialId()))
				.getSingleResult();
	}

	/**
	 * 製造が開始されていない計画を検索します。
	 *
	 * @return 製造計画一覧
	 */
	private AutoSelect<DProductPlan> findNotStart(String partNo) {
		return select()
				.leftOuterJoin(DProductResult())
				.where(new SimpleWhere().eq(partNo(), partNo),
						or(
								new SimpleWhere().eq(DProductResult().status(), ProductStatus.NONE),
								new SimpleWhere().isNull(DProductResult().status(), true)
						)
				)
				.orderBy(asc(productPlanNo()));
	}

	/**
	 * 製造が開始されていない計画を検索します。
	 *
	 * @return 製造計画一覧
	 */
	public List<DProductPlan> findNotStart() {
		return findNotStart(null).getResultList();
	}

	/**
	 * 製品IDで製造が開始されていない計画を検索します。
	 *
	 * @param partNo
	 *            製品ID
	 * @return 製造計画一覧
	 */
	public List<DProductPlan> findNotStartByPartNo(String partNo) {
		return findNotStart(partNo).getResultList();
	}

	/**
	 * 製造日でエンティティを検索します。
	 *
	 * @param manufactureStartDate
	 *            検索開始日
	 * @param manufactureEndDate
	 *            検索終了日
	 * @return エンティティのリスト
	 */
	public List<DProductPlanDbDto> findByManufactureDate(Date manufactureStartDate, Date manufactureEndDate) {

		Map<String, Object> param = new HashMap<>();
		param.put("manufactureStartDate", manufactureStartDate);
		param.put("manufactureEndDate", manufactureEndDate);

		return jdbcManager.selectBySqlFile(
				DProductPlanDbDto.class,
				"/jp/co/tmeic/mespd/service/DProductPlanService_findByManufactureDate.sql",
				param)
				.getResultList();
	}

	/**
	 * 識別子を条件に削除します。
	 *
	 * @param productPlanNo
	 * @return 削除件数
	 */
	public int deleteById(String productPlanNo) {
		return jdbcManager
				.updateBySql("DELETE FROM d_product_plan WHERE product_plan_no=?", String.class)
				.params(productPlanNo)
				.execute();
	}

	/**
	 * 更新可能かどうかを確認します。
	 *
	 * @param productPlanNo
	 * @return 更新可否
	 */
	public boolean isUpdatable(String productPlanNo) {

		DProductResult dProductResult =
				jdbcManager
						.selectBySql(
								DProductResult.class,
								"select * from d_product_result where product_plan_no = ?",
								productPlanNo)
						.getSingleResult();

		if (dProductResult == null) {
			return true;
		}

		if (dProductResult.status == 0) {
			return true;
		}

		return false;
	}

	/**
	 * 削除可能かどうかを確認します。
	 *
	 * @param productPlanNo
	 * @return 削除可否
	 */
	public boolean isDeletable(String productPlanNo) {
		return isUpdatable(productPlanNo);
	}

	/**
	 * 製造計画Noを生成します。
	 *
	 * @param date
	 * @return 製造計画No(yyMMddXXXX)
	 */
	public String createProductPlanNo(Date date) {

		String prefix = DateFormatUtils.format(date, "yyMMdd");
		String productPlanNoFormat = "%s%04d";

		List<DProductPlan> dProductPlans =
				select()
						.where(new SimpleWhere()
								.starts(productPlanNo(), prefix))
						.orderBy(desc(productPlanNo()))
						.getResultList();

		if (dProductPlans.isEmpty()) {
			return String.format(productPlanNoFormat, prefix, 1);
		}

		Set<Integer> seqNoes =
				dProductPlans.stream()
						.filter(e -> {
							return NumberUtils.isNumber(e.productPlanNo.replaceFirst(prefix, ""));
						})
						.flatMapToInt(e -> IntStream.of(Integer.parseInt(e.productPlanNo.replaceFirst(prefix, ""))))
						.distinct()
						.boxed()
						.collect(Collectors.toSet());

		for (int i = 1; i < 9999; i++) {
			if (!seqNoes.contains(i)) {
				return String.format(productPlanNoFormat, prefix, i);
			}
		}

		return null;
	}

	/**
	 * 製品IDでエンティティを検索します。
	 *
	 * @param partNo
	 *            部分的なNo
	 * @return エンティティのリスト
	 */
	public List<DProductPlan> findByPartNo(String partNo) {
		return select()
				.where(new SimpleWhere()
						.eq(partNo(), partNo))
				.getResultList();
	}
}
package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DShippingPlanNames.partNo;
import static jp.co.tmeic.mespd.entity.DShippingPlanNames.shippingNo;
import static org.seasar.extension.jdbc.operation.Operations.asc;
import static org.seasar.extension.jdbc.operation.Operations.desc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.dto.jqgrid.product.DShippingDbDto;
import jp.co.tmeic.mespd.entity.DShippingPlan;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link DShippingPlan}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DShippingService extends AbstractService<DShippingPlan> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param shippingNo
	 *            識別子
	 * @return エンティティ
	 */
	public DShippingPlan findById(String shippingNo) {
		return select().id(shippingNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DShippingPlan> findAllOrderById() {
		return select().orderBy(asc(shippingNo())).getResultList();
	}

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param shippingNo
	 *            識別子
	 * @return エンティティ
	 */
	public DShippingPlan findByShippingNo(String shippingNo) {
		return select()
				.id(shippingNo)
				.orderBy(asc(shippingNo()))
				.getSingleResult();
	}

	/**
	 * 製造日でエンティティを検索します。
	 *
	 * @param shippingStartDate
	 *            検索開始日
	 * @param shippingEndDate
	 *            検索終了日
	 * @return エンティティのリスト
	 */
	public List<DShippingDbDto> findByShippingDate(Date shippingStartDate, Date shippingEndDate) {

		Map<String, Object> param = new HashMap<>();
		param.put("shippingStartDate", shippingStartDate);
		param.put("shippingEndDate", shippingEndDate);

		return jdbcManager.selectBySqlFile(
				DShippingDbDto.class,
				"/jp/co/tmeic/mespd/service/DShippingService_findByShippingDate.sql",
				param)
				.getResultList();
	}
	
	/**
	 * 挿入到着結果列。
	 *
	 * @param shippingNo
	 * @return 削除件数
	 */
	public int insertShippingResult(String shippingNo, String resultQty) {
		Integer iResultQty = null;
		try {iResultQty = Integer.valueOf(resultQty);} catch(NumberFormatException e) {iResultQty = 0;}
		return jdbcManager
				.updateBySql("INSERT INTO d_shipping_result values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)", String.class, Integer.class)
				.params(shippingNo, iResultQty)
				.execute();
	}
	
	
	
	/**
	 * 挿入到着結果列。
	 *
	 * @param shippingNo
	 * @param resultQty
	 * @return 削除件数
	 */
	public int updateShippingResult(String shippingNo, String resultQty) {
		Integer iResultQty = null;
		try {iResultQty = Integer.valueOf(resultQty);} catch(NumberFormatException e) {iResultQty = 0;}
		return jdbcManager
				.updateBySql("update d_shipping_result set update_date=CURRENT_TIMESTAMP, result_qty=? where shipping_no=?", Integer.class, String.class)
				.params(iResultQty, shippingNo)
				.execute();
	}

	/**
	 * 識別子を条件に削除します。
	 *
	 * @param shippingNo
	 * @return 削除件数
	 */
	public int deleteById(String shippingNo) {
		jdbcManager
		.updateBySql("DELETE FROM d_shipping_result WHERE shipping_no=?", String.class)
		.params(shippingNo)
		.execute();
		
		return jdbcManager
				.updateBySql("DELETE FROM d_shipping_plan WHERE shipping_no=?", String.class)
				.params(shippingNo)
				.execute();
	}

	/**
	 * 更新可能かどうかを確認します。
	 *
	 * @param shippingNo
	 * @return 更新可否
	 */
	/*public boolean isUpdatable(String shippingNo) {

		DProductResult dProductResult =
				jdbcManager
						.selectBySql(
								DProductResult.class,
								"select * from d_product_result where shipping_no = ?",
								shippingNo)
						.getSingleResult();

		if (dProductResult == null) {
			return true;
		}

		if (dProductResult.status == 0) {
			return true;
		}

		return false;
	}*/

	/**
	 * 削除可能かどうかを確認します。
	 *
	 * @param shippingNo
	 * @return 削除可否
	 */
	/*public boolean isDeletable(String shippingNo) {
		return isUpdatable(shippingNo);
	}*/

	/**
	 * 製造計画Noを生成します。
	 *
	 * @param date
	 * @return 製造計画No(yyMMddXXXX)
	 */
	public String createShippingNo(Date date) {

		String prefix = DateFormatUtils.format(date, "yyMMdd");
		String shippingNoFormat = "%s%04d";

		List<DShippingPlan> dShippingLst =
				select()
						.where(new SimpleWhere()
								.starts(shippingNo(), prefix))
						.orderBy(desc(shippingNo()))
						.getResultList();

		if (dShippingLst.isEmpty()) {
			return String.format(shippingNoFormat, prefix, 1);
		}

		Set<Integer> seqNoes =
				dShippingLst.stream()
						.filter(e -> {
							return NumberUtils.isNumber(e.shippingNo.replaceFirst(prefix, ""));
						})
						.flatMapToInt(e -> IntStream.of(Integer.parseInt(e.shippingNo.replaceFirst(prefix, ""))))
						.distinct()
						.boxed()
						.collect(Collectors.toSet());

		for (int i = 1; i < 9999; i++) {
			if (!seqNoes.contains(i)) {
				return String.format(shippingNoFormat, prefix, i);
			}
		}

		return null;
	}

	/**
	 * 製品IDでエンティティを検索します。
	 *
	 * @param partNo
	 *            製品ID
	 * @return エンティティのリスト
	 */
	public List<DShippingPlan> findByPartNo(String partNo) {
		return select()
				.where(new SimpleWhere()
						.eq(partNo(), partNo))
				.getResultList();
	}
}
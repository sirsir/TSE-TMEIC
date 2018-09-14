package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DArrivalPlanNames.arrivalNo;
import static jp.co.tmeic.mespd.entity.DArrivalPlanNames.partNo;
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

import jp.co.tmeic.mespd.dto.jqgrid.product.DArrivalDbDto;
import jp.co.tmeic.mespd.entity.DArrivalPlan;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link DArrivalPlan}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DArrivalService extends AbstractService<DArrivalPlan> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param arrivalNo
	 *            識別子
	 * @return エンティティ
	 */
	public DArrivalPlan findById(String arrivalNo) {
		return select().id(arrivalNo).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DArrivalPlan> findAllOrderById() {
		return select().orderBy(asc(arrivalNo())).getResultList();
	}

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param arrivalNo
	 *            識別子
	 * @return エンティティ
	 */
	public DArrivalPlan findByArrivalNo(String arrivalNo) {
		return select()
				.id(arrivalNo)
				.orderBy(asc(arrivalNo()))
				.getSingleResult();
	}

	/**
	 * 製造日でエンティティを検索します。
	 *
	 * @param arrivalStartDate
	 *            検索開始日
	 * @param arrivalEndDate
	 *            検索終了日
	 * @return エンティティのリスト
	 */
	public List<DArrivalDbDto> findByArrivalDate(Date arrivalStartDate, Date arrivalEndDate) {

		Map<String, Object> param = new HashMap<>();
		param.put("arrivalStartDate", arrivalStartDate);
		param.put("arrivalEndDate", arrivalEndDate);

		return jdbcManager.selectBySqlFile(
				DArrivalDbDto.class,
				"/jp/co/tmeic/mespd/service/DArrivalService_findByArrivalDate.sql",
				param)
				.getResultList();
	}

	/**
	 * 識別子を条件に削除します。
	 *
	 * @param arrivalNo
	 * @return 削除件数
	 */
	public int deleteById(String arrivalNo) {
		jdbcManager
		.updateBySql("DELETE FROM d_arrival_result WHERE arrival_no=?", String.class)
		.params(arrivalNo)
		.execute();
		return jdbcManager
				.updateBySql("DELETE FROM d_arrival_plan WHERE arrival_no=?", String.class)
				.params(arrivalNo)
				.execute();
	}
	
	/**
	 * 挿入到着結果列。
	 *
	 * @param arrivalNo
	 * @return 削除件数
	 */
	public int insertArrivalResult(String arrivalNo, String resultQty) {
		Integer iResultQty = null;
		try {iResultQty = Integer.valueOf(resultQty);} catch(NumberFormatException e) {iResultQty = 0;}
		return jdbcManager
				.updateBySql("INSERT INTO d_arrival_result values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)", String.class, Integer.class)
				.params(arrivalNo, iResultQty)
				.execute();
	}
	
	
	
	/**
	 * 挿入到着結果列。
	 *
	 * @param arrivalNo
	 * @param resultQty
	 * @return 削除件数
	 */
	public int updateArrivalResult(String arrivalNo, String resultQty) {
		Integer iResultQty = null;
		try {iResultQty = Integer.valueOf(resultQty);} catch(NumberFormatException e) {iResultQty = 0;}
		return jdbcManager
				.updateBySql("update d_arrival_result set update_date=CURRENT_TIMESTAMP, result_qty=? where arrival_no=?", Integer.class, String.class)
				.params(iResultQty, arrivalNo)
				.execute();
	}

	/**
	 * 更新可能かどうかを確認します。
	 *
	 * @param arrivalNo
	 * @return 更新可否
	 */
	/*public boolean isUpdatable(String arrivalNo) {

		DProductResult dProductResult =
				jdbcManager
						.selectBySql(
								DProductResult.class,
								"select * from d_product_result where arrival_no = ?",
								arrivalNo)
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
	 * @param arrivalNo
	 * @return 削除可否
	 */
	/*public boolean isDeletable(String arrivalNo) {
		return isUpdatable(arrivalNo);
	}*/

	/**
	 * 製造計画Noを生成します。
	 *
	 * @param date
	 * @return 製造計画No(yyMMddXXXX)
	 */
	public String createArrivalNo(Date date) {

		String prefix = DateFormatUtils.format(date, "yyMMdd");
		String arrivalNoFormat = "%s%04d";

		List<DArrivalPlan> dArrivals =
				select()
						.where(new SimpleWhere()
								.starts(arrivalNo(), prefix))
						.orderBy(desc(arrivalNo()))
						.getResultList();

		if (dArrivals.isEmpty()) {
			return String.format(arrivalNoFormat, prefix, 1);
		}

		Set<Integer> seqNoes =
				dArrivals.stream()
						.filter(e -> {
							return NumberUtils.isNumber(e.arrivalNo.replaceFirst(prefix, ""));
						})
						.flatMapToInt(e -> IntStream.of(Integer.parseInt(e.arrivalNo.replaceFirst(prefix, ""))))
						.distinct()
						.boxed()
						.collect(Collectors.toSet());

		for (int i = 1; i < 9999; i++) {
			if (!seqNoes.contains(i)) {
				return String.format(arrivalNoFormat, prefix, i);
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
	public List<DArrivalPlan> findByPartNo(String partNo) {
		return select()
				.where(new SimpleWhere()
						.eq(partNo(), partNo))
				.getResultList();
	}
}
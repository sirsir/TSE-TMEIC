package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DSerialNoCountNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DSerialNoCount;

/**
 * {@link DSerialNoCount}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" })
public class DSerialNoCountService extends AbstractService<DSerialNoCount> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param headerId
	 *            識別子
	 * @return エンティティ
	 */
	public DSerialNoCount findById(String headerId) {
		return select().id(headerId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<DSerialNoCount> findAllOrderById() {
		return select().orderBy(asc(headerId())).getResultList();
	}

	/**
	 * 指定のシリアルヘッダのカウントをアップします。
	 *
	 * @param headerId
	 * @return
	 */
	public long countUp(String headerId) {

		DSerialNoCount dSerialNoCount = findById(headerId);

		boolean isNewRow = false;

		if (dSerialNoCount == null) {
			isNewRow = true;
			dSerialNoCount = new DSerialNoCount();
			dSerialNoCount.headerId = headerId;
			dSerialNoCount.headerCount = 0L;
		}

		dSerialNoCount.headerCount++;

		if (isNewRow) {
			insert(dSerialNoCount);
		} else {
			update(dSerialNoCount);
		}

		return dSerialNoCount.headerCount;
	}
}
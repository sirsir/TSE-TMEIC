package jp.co.tmeic.mespd.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.DSerialNo;

import static jp.co.tmeic.mespd.entity.DSerialNoNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link DSerialNo}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class DSerialNoService extends AbstractService<DSerialNo> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param serialNo
     *            識別子
     * @return エンティティ
     */
    public DSerialNo findById(String serialNo) {
        return select().id(serialNo).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<DSerialNo> findAllOrderById() {
        return select().orderBy(asc(serialNo())).getResultList();
    }
}
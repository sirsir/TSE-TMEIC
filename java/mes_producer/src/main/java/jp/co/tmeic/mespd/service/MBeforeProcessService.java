package jp.co.tmeic.mespd.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MBeforeProcess;

import static jp.co.tmeic.mespd.entity.MBeforeProcessNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MBeforeProcess}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class MBeforeProcessService extends AbstractService<MBeforeProcess> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param partNo
     *            識別子
     * @param processComponentNo
     *            識別子
     * @param beforeProcessComponentNo
     *            識別子
     * @return エンティティ
     */
    public MBeforeProcess findById(String partNo, Integer processComponentNo, Integer beforeProcessComponentNo) {
        return select().id(partNo, processComponentNo, beforeProcessComponentNo).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MBeforeProcess> findAllOrderById() {
        return select().orderBy(asc(partNo()), asc(processComponentNo()), asc(beforeProcessComponentNo())).getResultList();
    }
}
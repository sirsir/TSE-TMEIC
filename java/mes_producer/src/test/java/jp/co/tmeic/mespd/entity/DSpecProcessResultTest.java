package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.DSpecProcessResultNames.*;

/**
 * {@link DSpecProcessResult}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class DSpecProcessResultTest extends S2TestCase {

    private JdbcManager jdbcManager;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("s2jdbc.dicon");
    }

    /**
     * 識別子による取得をテストします。
     * 
     * @throws Exception
     */
    public void testFindById() throws Exception {
        jdbcManager.from(DSpecProcessResult.class).id("aaa", 1, 1).getSingleResult();
    }

    /**
     * DSpecProcessPlanとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DSpecProcessPlan() throws Exception {
        jdbcManager.from(DSpecProcessResult.class).leftOuterJoin(DSpecProcessPlan()).id("aaa", 1, 1).getSingleResult();
    }
}
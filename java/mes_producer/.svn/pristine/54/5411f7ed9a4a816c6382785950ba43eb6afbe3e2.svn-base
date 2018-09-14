package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.DMaterialPlanNames.*;

/**
 * {@link DMaterialPlan}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class DMaterialPlanTest extends S2TestCase {

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
        jdbcManager.from(DMaterialPlan.class).id("aaa", 1, 1).getSingleResult();
    }

    /**
     * DProcessPlanとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DProcessPlan() throws Exception {
        jdbcManager.from(DMaterialPlan.class).leftOuterJoin(DProcessPlan()).id("aaa", 1, 1).getSingleResult();
    }

    /**
     * DMaterialProcessResultとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DMaterialProcessResult() throws Exception {
        jdbcManager.from(DMaterialPlan.class).leftOuterJoin(DMaterialProcessResult()).id("aaa", 1, 1).getSingleResult();
    }

    /**
     * DMaterialProductResultListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DMaterialProductResultList() throws Exception {
        jdbcManager.from(DMaterialPlan.class).leftOuterJoin(DMaterialProductResultList()).id("aaa", 1, 1).getSingleResult();
    }
}
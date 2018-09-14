package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.DProcessPlanNames.*;

/**
 * {@link DProcessPlan}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class DProcessPlanTest extends S2TestCase {

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
        jdbcManager.from(DProcessPlan.class).id("aaa", 1).getSingleResult();
    }

    /**
     * DMaterialPlanListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DMaterialPlanList() throws Exception {
        jdbcManager.from(DProcessPlan.class).leftOuterJoin(DMaterialPlanList()).id("aaa", 1).getSingleResult();
    }

    /**
     * DProductPlanとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DProductPlan() throws Exception {
        jdbcManager.from(DProcessPlan.class).leftOuterJoin(DProductPlan()).id("aaa", 1).getSingleResult();
    }

    /**
     * DProcessProductResultListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DProcessProductResultList() throws Exception {
        jdbcManager.from(DProcessPlan.class).leftOuterJoin(DProcessProductResultList()).id("aaa", 1).getSingleResult();
    }

    /**
     * DProcessResultとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DProcessResult() throws Exception {
        jdbcManager.from(DProcessPlan.class).leftOuterJoin(DProcessResult()).id("aaa", 1).getSingleResult();
    }

    /**
     * DSpecProcessPlanListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DSpecProcessPlanList() throws Exception {
        jdbcManager.from(DProcessPlan.class).leftOuterJoin(DSpecProcessPlanList()).id("aaa", 1).getSingleResult();
    }

    /**
     * DSpecProductPlanListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DSpecProductPlanList() throws Exception {
        jdbcManager.from(DProcessPlan.class).leftOuterJoin(DSpecProductPlanList()).id("aaa", 1).getSingleResult();
    }
}
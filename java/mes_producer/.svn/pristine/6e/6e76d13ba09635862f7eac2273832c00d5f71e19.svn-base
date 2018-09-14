package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.MMaterialComponentNames.*;

/**
 * {@link MMaterialComponent}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class MMaterialComponentTest extends S2TestCase {

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
        jdbcManager.from(MMaterialComponent.class).id("aaa", 1, 1).getSingleResult();
    }

    /**
     * MMaterialとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MMaterial() throws Exception {
        jdbcManager.from(MMaterialComponent.class).leftOuterJoin(MMaterial()).id("aaa", 1, 1).getSingleResult();
    }

    /**
     * MProcessComponentとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MProcessComponent() throws Exception {
        jdbcManager.from(MMaterialComponent.class).leftOuterJoin(MProcessComponent()).id("aaa", 1, 1).getSingleResult();
    }
}
package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.MAuthorityNames.*;

/**
 * {@link MAuthority}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class MAuthorityTest extends S2TestCase {

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
        jdbcManager.from(MAuthority.class).id("aaa").getSingleResult();
    }

    /**
     * MRoleAuthorityListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MRoleAuthorityList() throws Exception {
        jdbcManager.from(MAuthority.class).leftOuterJoin(MRoleAuthorityList()).id("aaa").getSingleResult();
    }
}
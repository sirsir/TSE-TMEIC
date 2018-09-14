package jp.co.tmeic.mespd.service;

import org.junit.Test;

public class MaterialPlanServiceTest extends AbstructServiceTest {

	private MaterialPlanService materialPlanService;

	/**
	 * 事前処理をします。
	 *
	 * @throws Exception
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("app.dicon");
	}

	@Test
	public void testFindBySerialNo() {
		materialPlanService.findBySerialNo("", 1, "", 0);
	}

}

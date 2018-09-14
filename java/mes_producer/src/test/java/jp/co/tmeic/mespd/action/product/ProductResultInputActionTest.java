package jp.co.tmeic.mespd.action.product;

import java.util.Date;
import java.util.List;

import jp.co.tmeic.mespd.dto.report.MaterialPlanDto;
import jp.co.tmeic.mespd.dto.report.MaterialPlanMonthlyDto;
import jp.co.tmeic.mespd.service.ProcessProductResultService;
import jp.co.tmeic.mespd.utils.DateUtil;

import org.junit.runner.RunWith;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.unit.Seasar2;

@RunWith(Seasar2.class)
public class ProductResultInputActionTest extends S2TestCase {

    private ProcessProductResultService processProductResultService;

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

    /**
     * {@link #dProductPlanService}が利用可能であることをテストします。
     *
     * @throws Exception
     */
    public void testAvailable() throws Exception {
        assertNotNull(processProductResultService);
    }

	/**
	 * 部材実績検索機能（対象日） 有効チェック
	 *
	 */
	public void testAvailableOfFindMaterialResultByDayTx() {

		try {

			Date targetDay = DateUtil.toDate("2015/06/01");

			List<MaterialPlanDto> materialPlanDtos = processProductResultService.findMaterialResultByDay(targetDay);

			assertNotNull(materialPlanDtos);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 部材実績検索機能（対象月） 有効チェック
	 *
	 */
	public void testAvailableOfFindMaterialResultByMonthTx() {

		try {

			Date targetDay = DateUtil.toDate("2015/06/01");

			List<MaterialPlanMonthlyDto> materialPlanMonthlyDtos = processProductResultService.findMaterialResultByMonth(targetDay);

			assertNotNull(materialPlanMonthlyDtos);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}

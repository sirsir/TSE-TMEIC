package jp.co.tmeic.mespd.service;

import org.seasar.extension.dataset.DataSet;
import org.seasar.extension.dataset.impl.SqlReader;

public class ProductPlanServiceTest extends AbstructServiceTest {

	private ProductPlanService productPlanService;

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
	 * 計画データ登録テスト01（テーブルごとにマスタデータ1件ずつ）
	 *
	 */
	public void testCreatePlanDataOfOneDataTx() {

		this.allDelete();

		readXlsAllReplaceDb("ProductPlanServiceTest_testCreatePlanDataOfOneDataTx.xls");

		productPlanService.createPlanData("PPNO001");

		DataSet result = getResult();
		DataSet expect = readXls("ProductPlanServiceTest_testCreatePlanDataOfOneDataTx_Expected.xls");

		assertEquals(expect, result);
	}

	/**
	 * 計画データ登録テスト02（構成マスタデータ複数件）
	 *
	 */
	public void testCreatePlanDataOfMultipleDataTx() {

		this.allDelete();

		readXlsAllReplaceDb("ProductPlanServiceTest_testCreatePlanDataOfMultipleDataTx.xls");

		productPlanService.createPlanData("PPNO00000002");

		DataSet result = getResult();
		DataSet expect = readXls("ProductPlanServiceTest_testCreatePlanDataOfMultipleDataTx_Expected.xls");

		assertEquals(expect, result);
	}

	/**
	 * 計画データ登録テスト03（工程計画のみ）
	 *
	 */
	public void testCreatePlanDataOfOnlyProcessPlanTx() {

		this.allDelete();

		readXlsAllReplaceDb("ProductPlanServiceTest_testCreatePlanDataOfOnlyProcessPlanTx.xls");

		productPlanService.createPlanData("PPNO003");

		DataSet result = getResult();
		DataSet expect = readXls("ProductPlanServiceTest_testCreatePlanDataOfOnlyProcessPlanTx_Expected.xls");

		assertEquals(expect, result);
	}

	private DataSet getResult() {

		//更新結果からDataSetを作成
		SqlReader sqlReader = new SqlReader(getDataSource());
		sqlReader.addTable("d_product_plan");
		sqlReader.addTable("d_process_plan");
		sqlReader.addTable("d_spec_attribute_plan");
		sqlReader.addTable("d_spec_plan");
		sqlReader.addTable("d_spec_process_plan");
		sqlReader.addTable("d_spec_product_plan");
		sqlReader.addTable("d_material_plan");
		DataSet result = sqlReader.read();

		return result;
	}

}

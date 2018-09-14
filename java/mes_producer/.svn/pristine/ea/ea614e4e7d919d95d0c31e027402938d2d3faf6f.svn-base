/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.tmeic.mespd.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.entity.DMaterialPlan;
import jp.co.tmeic.mespd.entity.DMaterialProcessResult;
import jp.co.tmeic.mespd.entity.DMaterialProductResult;
import jp.co.tmeic.mespd.entity.DProcessPlan;
import jp.co.tmeic.mespd.entity.DProcessProductResult;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.entity.DProductPlan;
import jp.co.tmeic.mespd.entity.DProductResult;
import jp.co.tmeic.mespd.entity.DSerialNo;
import jp.co.tmeic.mespd.entity.DSpecAttributePlan;
import jp.co.tmeic.mespd.entity.DSpecPlan;
import jp.co.tmeic.mespd.entity.DSpecProcessPlan;
import jp.co.tmeic.mespd.entity.DSpecProcessResult;
import jp.co.tmeic.mespd.entity.DSpecProductPlan;
import jp.co.tmeic.mespd.entity.DSpecProductResult;
import jp.co.tmeic.mespd.utils.DateUtil;
import jp.co.tmeic.mespd.utils.SqlDateUtil;
import jp.co.tmeic.mespd.utils.TimestampUtil;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.struts.annotation.Execute;

public class TestDataAction extends AbstractAction {

	/** Logger */
	private Logger logger = Logger.getLogger(getClass());

	@Resource
	protected JdbcManager jdbcManager;

	@Execute(validator = false)
	public String index() throws ParseException {

		dataDelete();

		Timestamp now = TimestampUtil.now();

		// 10製品 x 365日 x 3年 = 10950件 ※約1万件
		Date startDate = DateUtil.toDate("2013/12/31");

		final int productPlansCnt = 10000;

		List<DProductPlan> dProductPlans = new ArrayList<>(productPlansCnt);
		List<DProductResult> dProductResults = new ArrayList<>(productPlansCnt);

		for (int i = 0; i < productPlansCnt; i++) {

			if (i % 10 == 0) {
				startDate = DateUtils.addDays(startDate, 1);
			}

			DProductPlan dProductPlan = new DProductPlan();

			dProductPlan.createDate = now;
			dProductPlan.updateDate = now;
			dProductPlan.productPlanNo = String.format("TEST%04d", i);
			dProductPlan.manufactureDate = SqlDateUtil.toDate(startDate);
			dProductPlan.partNo = String.format("Test%04d", i);
			dProductPlan.partName = String.format("Test name%04d", i);
			dProductPlan.productKind = (i % 3) + 1;
			dProductPlan.planQty = 100;
			dProductPlan.planStartDate = TimestampUtil.toTimestamp(DateUtil.setTime(startDate, 9, 0));
			dProductPlan.planEndDate = TimestampUtil.toTimestamp(DateUtil.setTime(startDate, 23, 0));

			dProductPlans.add(dProductPlan);

			DProductResult dProductResult = new DProductResult();

			dProductResult.createDate = now;
			dProductResult.updateDate = now;
			dProductResult.productPlanNo = dProductPlan.productPlanNo;
			dProductResult.startDatetime = dProductPlan.planStartDate;
			dProductResult.endDatetime = dProductPlan.planEndDate;
			dProductResult.status = 4;

			dProductResults.add(dProductResult);
		}

		jdbcManager.insertBatch(dProductPlans).execute();
		jdbcManager.insertBatch(dProductResults).execute();

		for (int i = 0; i < productPlansCnt; i++) {

			DProductPlan dProductPlan = dProductPlans.get(i);

			logger.info("作成計画No: " + dProductPlan.productPlanNo);

			createSpec(now, dProductPlan.productPlanNo);
			createProcess(now, dProductPlan.productPlanNo, dProductPlan.planStartDate, dProductPlan.planEndDate, i * 100);
		}

		return null;
	}

	private void createSpec(Timestamp now, String productPlanNo) {

		final int cnt = 5;

		List<DSpecAttributePlan> dSpecAttributePlans = new ArrayList<>();
		List<DSpecPlan> dSpecPlans = new ArrayList<>();

		for (int i = 0; i < cnt; i++) {

			DSpecAttributePlan dSpecAttributePlan = new DSpecAttributePlan();

			dSpecAttributePlan.createDate = now;
			dSpecAttributePlan.updateDate = now;
			dSpecAttributePlan.productPlanNo = productPlanNo;
			dSpecAttributePlan.specAttributeId = i + 1;
			dSpecAttributePlan.specAttributeName = "ARRT" + (i + 1);

			dSpecAttributePlans.add(dSpecAttributePlan);

			DSpecPlan dSpecPlan = new DSpecPlan();

			dSpecPlan.createDate = now;
			dSpecPlan.updateDate = now;
			dSpecPlan.productPlanNo = productPlanNo;
			dSpecPlan.specId = String.format("SPEC%04d", i + 1);
			dSpecPlan.specName = "SPEC" + (i + 1);
			dSpecPlan.specAttributeId = i + 1;
			dSpecPlan.specParts01 = "OK";
			dSpecPlan.specParts02 = "NG";
			dSpecPlan.specParts03 = "Reject";

			dSpecPlans.add(dSpecPlan);
		}

		jdbcManager.insertBatch(dSpecAttributePlans).execute();
		jdbcManager.insertBatch(dSpecPlans).execute();
	}

	private void createProcess(Timestamp now, String productPlanNo, Timestamp planStartDate, Timestamp planEndDate, int serialNoStart) {

		final int cnt = 5;

		List<DProcessPlan> dProcessPlans = new ArrayList<>();
		List<DProcessResult> dProcessResults = new ArrayList<>();

		for (int i = 0; i < cnt; i++) {

			DProcessPlan dProcessPlan = new DProcessPlan();

			dProcessPlan.createDate = now;
			dProcessPlan.updateDate = now;
			dProcessPlan.productPlanNo = productPlanNo;
			dProcessPlan.processComponentNo = i;
			dProcessPlan.processId = null;
			dProcessPlan.processName = "process" + i;
			dProcessPlan.planQty = 100;
			dProcessPlan.processContents = "test";
			dProcessPlan.processTime = 60 * 30;
			dProcessPlan.personnelRequired = 5;
			dProcessPlan.parallelWork = 5;
			dProcessPlan.planStartDate = planStartDate;
			dProcessPlan.planEndDate = planEndDate;

			dProcessPlans.add(dProcessPlan);

			DProcessResult dProcessResult = new DProcessResult();

			dProcessResult.createDate = now;
			dProcessResult.updateDate = now;
			dProcessResult.productPlanNo = productPlanNo;
			dProcessResult.processComponentNo = i;
			dProcessResult.startDatetime = planStartDate;
			dProcessResult.endDatetime = planEndDate;
			dProcessResult.resultQty = 90;
			dProcessResult.inferiorQty = 10;
			dProcessResult.status = 2;
			dProcessResult.productResultLastdate = planEndDate;

			dProcessResults.add(dProcessResult);
		}

		jdbcManager.insertBatch(dProcessPlans).execute();
		jdbcManager.insertBatch(dProcessResults).execute();

		for (DProcessPlan dProcessPlan : dProcessPlans) {
			createSpecProcess(now, productPlanNo, dProcessPlan.processComponentNo);
			createSpecProduct(now, productPlanNo, dProcessPlan.processComponentNo);
			createMaterial(now, productPlanNo, dProcessPlan.processComponentNo);
			createProcessProductResults(now, productPlanNo, dProcessPlan.processComponentNo, planStartDate, serialNoStart);
		}
	}

	private void createSpecProcess(Timestamp now, String productPlanNo, Integer processComponentNo) {

		final int cnt = 5;

		List<DSpecProcessPlan> dSpecProcessPlans = new ArrayList<>();
		List<DSpecProcessResult> dSpecProcessResults = new ArrayList<>();

		for (int i = 0; i < cnt; i++) {

			DSpecProcessPlan dSpecProcessPlan = new DSpecProcessPlan();

			dSpecProcessPlan.createDate = now;
			dSpecProcessPlan.updateDate = now;
			dSpecProcessPlan.productPlanNo = productPlanNo;
			dSpecProcessPlan.processComponentNo = processComponentNo;
			dSpecProcessPlan.specComponentNo = i;
			dSpecProcessPlan.specId = String.format("SPEC%04d", i + 1);
			dSpecProcessPlan.displayOrder = i;

			dSpecProcessPlans.add(dSpecProcessPlan);

			DSpecProcessResult dSpecProcessResult = new DSpecProcessResult();

			dSpecProcessResult.createDate = now;
			dSpecProcessResult.updateDate = now;
			dSpecProcessResult.productPlanNo = productPlanNo;
			dSpecProcessResult.processComponentNo = processComponentNo;
			dSpecProcessResult.specComponentNo = i;
			dSpecProcessResult.inputValue = "OK";

			dSpecProcessResults.add(dSpecProcessResult);
		}

		jdbcManager.insertBatch(dSpecProcessPlans).execute();
		jdbcManager.insertBatch(dSpecProcessResults).execute();
	}

	private void createSpecProduct(Timestamp now, String productPlanNo, Integer processComponentNo) {

		final int cnt = 5;

		List<DSpecProductPlan> dSpecProductPlans = new ArrayList<>();

		for (int i = 0; i < cnt; i++) {

			DSpecProductPlan dSpecProductPlan = new DSpecProductPlan();

			dSpecProductPlan.createDate = now;
			dSpecProductPlan.updateDate = now;
			dSpecProductPlan.productPlanNo = productPlanNo;
			dSpecProductPlan.processComponentNo = processComponentNo;
			dSpecProductPlan.specComponentNo = i;
			dSpecProductPlan.specId = String.format("SPEC%04d", i + 1);
			dSpecProductPlan.displayOrder = i;

			dSpecProductPlans.add(dSpecProductPlan);
		}

		jdbcManager.insertBatch(dSpecProductPlans).execute();
	}

	private void createMaterial(Timestamp now, String productPlanNo, Integer processComponentNo) {

		final int cnt = 5;

		List<DMaterialPlan> dMaterialPlans = new ArrayList<>();
		List<DMaterialProcessResult> dMaterialProcessResults = new ArrayList<>();

		for (int i = 0; i < cnt; i++) {

			DMaterialPlan dMaterialPlan = new DMaterialPlan();

			dMaterialPlan.createDate = now;
			dMaterialPlan.updateDate = now;
			dMaterialPlan.productPlanNo = productPlanNo;
			dMaterialPlan.processComponentNo = processComponentNo;
			dMaterialPlan.materialComponentNo = i;
			dMaterialPlan.materialId = String.format("MAT%04d", i + 1);
			dMaterialPlan.materialName = String.format("MAT%04d", i + 1);
			dMaterialPlan.materialQty = i;
			dMaterialPlan.materialUnit = "kg";
			dMaterialPlan.displayOrder = i;

			dMaterialPlans.add(dMaterialPlan);

			DMaterialProcessResult dMaterialProcessResult = new DMaterialProcessResult();

			dMaterialProcessResult.createDate = now;
			dMaterialProcessResult.updateDate = now;
			dMaterialProcessResult.productPlanNo = productPlanNo;
			dMaterialProcessResult.processComponentNo = processComponentNo;
			dMaterialProcessResult.materialComponentNo = i;
			dMaterialProcessResult.materialQty = i;

			dMaterialProcessResults.add(dMaterialProcessResult);
		}

		jdbcManager.insertBatch(dMaterialPlans).execute();
		jdbcManager.insertBatch(dMaterialProcessResults).execute();
	}

	private void createProcessProductResults(
			Timestamp now, String productPlanNo, Integer processComponentNo, Timestamp planStartDate, int serialNoStart) {

		final int productCnt = 100;

		List<DProcessProductResult> dProcessProductResults = new ArrayList<>(productCnt);

		for (int i = 0; i < productCnt; i++) {

			DProcessProductResult dProcessProductResult = new DProcessProductResult();

			dProcessProductResult.createDate = now;
			dProcessProductResult.updateDate = now;
			dProcessProductResult.productPlanNo = productPlanNo;
			dProcessProductResult.processComponentNo = processComponentNo;
			dProcessProductResult.serialNo = String.format("TEST%07d", serialNoStart + i);
			dProcessProductResult.revision = 1;
			dProcessProductResult.startDate = new Timestamp(DateUtils.addMinutes(planStartDate, (i + 1) * 3).getTime());
			dProcessProductResult.endDate = new Timestamp(DateUtils.addMinutes(planStartDate, (i + 2) * 3).getTime());
			dProcessProductResult.userId = "TEST ID";
			dProcessProductResult.userName = "TEST USER";
			dProcessProductResult.status = 2;

			if (i % 10 == 0) {
				dProcessProductResult.quality = 1;
			} else {
				dProcessProductResult.quality = 3;
			}

			dProcessProductResults.add(dProcessProductResult);
		}

		jdbcManager.insertBatch(dProcessProductResults).execute();

		for (DProcessProductResult dProcessProductResult : dProcessProductResults) {
			createSpecProductResult(now, productPlanNo, processComponentNo, planStartDate, dProcessProductResult.serialNo);
			createMaterialProduct(now, productPlanNo, processComponentNo, dProcessProductResult.serialNo);
		}
	}

	private void createSpecProductResult(
			Timestamp now, String productPlanNo, Integer processComponentNo, Timestamp planStartDate, String serialNo) {

		final int cnt = 5;

		List<DSpecProductResult> dSpecProductResults = new ArrayList<>();

		for (int i = 0; i < cnt; i++) {

			DSpecProductResult dSpecProductResult = new DSpecProductResult();

			dSpecProductResult.createDate = now;
			dSpecProductResult.updateDate = now;
			dSpecProductResult.productPlanNo = productPlanNo;
			dSpecProductResult.processComponentNo = processComponentNo;
			dSpecProductResult.serialNo = serialNo;
			dSpecProductResult.revision = 1;
			dSpecProductResult.specComponentNo = i;
			dSpecProductResult.inputValue = "OK";

			dSpecProductResults.add(dSpecProductResult);
		}

		jdbcManager.insertBatch(dSpecProductResults).execute();
	}

	private void createMaterialProduct(
			Timestamp now, String productPlanNo, Integer processComponentNo, String serialNo) {

		final int cnt = 5;

		List<DMaterialProductResult> dMaterialProductResults = new ArrayList<>();

		for (int i = 0; i < cnt; i++) {

			DMaterialProductResult dMaterialProductResult = new DMaterialProductResult();

			dMaterialProductResult.createDate = now;
			dMaterialProductResult.updateDate = now;
			dMaterialProductResult.productPlanNo = productPlanNo;
			dMaterialProductResult.processComponentNo = processComponentNo;
			dMaterialProductResult.serialNo = serialNo;
			dMaterialProductResult.revision = 1;
			dMaterialProductResult.materialComponentNo = i;
			dMaterialProductResult.materialQty = i;

			dMaterialProductResults.add(dMaterialProductResult);
		}

		jdbcManager.insertBatch(dMaterialProductResults).execute();
	}

	private void dataDelete() {

		delete("d_material_process_result");
		delete("d_material_product_result");
		delete("d_spec_product_result");
		delete("d_process_product_result");
		delete("d_spec_process_result");
		delete("d_process_result");
		delete("d_product_result");
		delete("d_spec_process_plan");
		delete("d_spec_product_plan");
		delete("d_material_plan");
		delete("d_spec_plan");
		delete("d_spec_attribute_plan");
		delete("d_process_plan");
		delete("d_product_plan");

	}

	private void delete(String table) {
		jdbcManager.updateBySql("DELETE FROM " + table).execute();
	}

	@Execute(validator = false)
	public String serialNo() {

		Timestamp now = TimestampUtil.now();

		for (int i = 0; i < 5000000; i++) {

			DSerialNo dSerialNo = new DSerialNo();
			dSerialNo.createDate = now;
			dSerialNo.updateDate = now;

			dSerialNo.serialNo = String.format("TEST%07d", i);

			System.out.println(dSerialNo.serialNo);

			jdbcManager.insert(dSerialNo).execute();
		}

		return null;
	}
}

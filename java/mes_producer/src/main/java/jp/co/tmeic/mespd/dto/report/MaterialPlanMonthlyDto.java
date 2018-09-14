package jp.co.tmeic.mespd.dto.report;

public class MaterialPlanMonthlyDto {

	/** 部材ID */
	public String materialId;

	/** 部材名 */
	public String materialName;

	/** 部材単位 */
	public String materialUnit;

	/** 日別実績 */
	public MaterialPlanDto[] materialPlanDtos = new MaterialPlanDto[31];

	public MaterialPlanMonthlyDto() {

		for (int i = 0; i < materialPlanDtos.length; i++) {
			materialPlanDtos[i] = new MaterialPlanDto();
		}
	}

	/** 実績数量 合計 */
	public int getSumMaterialQty() {

		int sum = 0;

		for (int i = 0; i < materialPlanDtos.length; i++) {
			sum += materialPlanDtos[i].materialQty;
		}

		return sum;
	}
}

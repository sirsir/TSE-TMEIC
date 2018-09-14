package jp.co.tmeic.mespd.dto.report;

public class ProductPlanMonthlyDto {

	/** 製品ID */
	public String productId;

	/** 製品名 */
	public String productName;

	/** 日別実績 */
	public ProductPlanDto[] productPlanDtos = new ProductPlanDto[31];

	public ProductPlanMonthlyDto() {

		for (int i = 0; i < productPlanDtos.length; i++) {
			productPlanDtos[i] = new ProductPlanDto();
		}
	}

	/** 実績数量 合計 */
	public int getSumResultQty() {

		int sum = 0;

		for (int i = 0; i < productPlanDtos.length; i++) {
			sum += productPlanDtos[i].resultQty;
		}

		return sum;
	}

	/** 不良数量 合計 */
	public int getSumInferiorQty() {

		int sum = 0;

		for (int i = 0; i < productPlanDtos.length; i++) {
			sum += productPlanDtos[i].inferiorQty;
		}

		return sum;
	}

}

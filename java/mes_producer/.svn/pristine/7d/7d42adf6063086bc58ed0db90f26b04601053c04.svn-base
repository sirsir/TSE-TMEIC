package jp.co.tmeic.mespd.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.tmeic.mespd.dto.jqgrid.product.DProcessResultDto;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.entity.DProductResult;
import jp.co.tmeic.mespd.utils.JSONUtil;
import net.arnx.jsonic.JSON;

import org.apache.commons.lang3.StringUtils;
import org.seasar.framework.beans.util.Beans;

public final class DProcessResultConvert {

	private DProcessResultConvert() {

	}

	/**
	 * JSON文字列から工程実績Mapへ変換
	 *
	 * @param dProcessResultJson
	 * @return
	 */
	public static List<DProcessResultDto> toDProcessResultDto(String dProcessResultJson) {

		List<DProcessResultDto> dProcessResultDtos = new ArrayList<>();
		Map<Integer, Object> processResultsMap = JSONUtil.decodeToMapInt(dProcessResultJson);

		for (Integer index : processResultsMap.keySet()) {

			Map<String, Object> processResult = JSONUtil.decodeToMapString(JSON.encode(processResultsMap.get(index)));
			DProcessResultDto dProcessResultDto = copyProcessResultMapToDto(processResult);

			if (processResult.containsKey("productResults")) {
				String dProcessProductResultJson = JSON.encode(processResult.get("productResults"));
				if (!StringUtils.isBlank(dProcessProductResultJson)) {
					dProcessResultDto.dProcessProductResults =
							DProcessProductResultConvert.toDProcessProductResultDto(dProcessProductResultJson);
				}
			}

			dProcessResultDtos.add(dProcessResultDto);
		}

		return dProcessResultDtos;
	}

	/**
	 * 工程実績Mapから工程実績Dtoへ変換
	 *
	 * @param processResultMap
	 * @return
	 */
	private static DProcessResultDto copyProcessResultMapToDto(Map<String, Object> processResultMap) {

		DProcessResultDto dProcessResultDto =
				Beans.createAndCopy(DProcessResultDto.class, processResultMap).execute();

		dProcessResultDto.dMaterialProcessResults =
				DMaterialResultConvert.toDMaterialResultDto(processResultMap);

		dProcessResultDto.dSpecProcessResults =
				DSpecResultConvert.toDSpecResultDto(processResultMap);

		return dProcessResultDto;
	}

	/**
	 * 工程実績Mapから工程実績Dtoへ変換
	 *
	 * @param productPlanNo
	 * @param dProcessResultDto
	 * @return
	 */
	public static DProcessResult toDProcessResult(String productPlanNo, DProcessResultDto dProcessResultDto) {

		DProcessResult dProcessResult = Beans.createAndCopy(DProcessResult.class, dProcessResultDto).execute();

		dProcessResult.productPlanNo = productPlanNo;

		return dProcessResult;
	}

	/**
	 * 工程実績Mapから工程実績Dtoへ変換
	 *
	 * @param dProductResult
	 * @param dProcessResultDto
	 * @return
	 */
	public static DProcessResult toDProcessResult(DProductResult dProductResult, DProcessResultDto dProcessResultDto) {
		return toDProcessResult(dProductResult.productPlanNo, dProcessResultDto);
	}

}

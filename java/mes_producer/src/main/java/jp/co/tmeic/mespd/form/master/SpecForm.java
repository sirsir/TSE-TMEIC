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
package jp.co.tmeic.mespd.form.master;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.tmeic.mespd.entity.MSpecAttribute;
import jp.co.tmeic.mespd.utils.JqgridUtil;

/** 仕様マスタ */
public class SpecForm {

	/** 仕様マスタ */
	public String specJson;

	/** 仕様属性マスタ */
	public List<MSpecAttribute> mAttributes = new ArrayList<>();

	/** 仕様属性リスト */
	public String getAttributes() {

		Map<String, String> map = new LinkedHashMap<>();

		for (MSpecAttribute mAttribute : mAttributes) {
			map.put(String.valueOf(mAttribute.specAttributeId), mAttribute.specAttributeName);
		}

		return JqgridUtil.toSelect(map);
	}
}

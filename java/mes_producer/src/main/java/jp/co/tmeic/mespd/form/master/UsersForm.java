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

import jp.co.tmeic.mespd.entity.MRole;
import jp.co.tmeic.mespd.utils.JqgridUtil;

/** ユーザマスタ */
public class UsersForm {

	/** ユーザマスタ */
	public String usersJson;

	/** 役割マスタ */
	public List<MRole> mRoles = new ArrayList<>();

	/** 役割リスト */
	public String getRoles() {

		Map<String, String> map = new LinkedHashMap<>();

		for (MRole mRole : mRoles) {
			map.put(mRole.roleId, mRole.roleName);
		}

		return JqgridUtil.toSelect(map);
	}
}

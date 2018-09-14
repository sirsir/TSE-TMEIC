package jp.co.tmeic.mespd.dto;

import java.io.Serializable;

import jp.co.tmeic.mespd.entity.MRoleAuthority;
import jp.co.tmeic.mespd.entity.MUsers;
import jp.co.tmeic.mespd.entity.MUsersRole;

import org.apache.commons.lang3.StringUtils;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ユーザ */
	public MUsers mUsers;

	public String getUserId() {
		return mUsers.userId;
	}

	public String getUserName() {
		return mUsers.userName;
	}

	public boolean authority(String authorityId) {

		for (MUsersRole mUsersRole : mUsers.MUsersRoleList) {

			for (MRoleAuthority mRoleAuthority : mUsersRole.MRole.MRoleAuthorityList) {
				if (StringUtils.equals(authorityId, mRoleAuthority.authorityId)) {
					return true;
				}
			}
		}

		return false;
	}
}

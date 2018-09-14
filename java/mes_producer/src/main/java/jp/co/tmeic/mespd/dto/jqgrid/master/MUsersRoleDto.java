package jp.co.tmeic.mespd.dto.jqgrid.master;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;

/**
 * ユーザマスタ
 *
 */
public class MUsersRoleDto extends JqgridDto {

	/** ユーザID */
	public String userId;

	/** ユーザ名 */
	public String userName;

	/** パスワード */
	public String userPassword;

	/** 役割ID */
	public String roleId;
}
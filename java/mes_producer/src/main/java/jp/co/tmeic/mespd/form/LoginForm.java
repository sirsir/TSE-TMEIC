package jp.co.tmeic.mespd.form;

import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Required;

/** ログイン */
public class LoginForm {

	/** ユーザコード */
	@Required(arg0 = @Arg(key = "user.code", resource = true), target = "login")
	public String userCode;

	/** パスワード */
	@Required(arg0 = @Arg(key = "password", resource = true), target = "login")
	public String password;
}

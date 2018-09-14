package jp.co.tmeic.mespd.interceptor;

import javax.servlet.http.HttpSession;

import jp.co.tmeic.mespd.dto.UserDto;
import jp.co.tmeic.mespd.service.MUsersService;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.Execute;

public class ActionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		if (isExecuteMethod(invocation)) {
			before(invocation);
		}

		Object object = invocation.proceed();

		return object;
	}

	private Boolean isExecuteMethod(MethodInvocation invocation) {

		return invocation.getMethod().isAnnotationPresent(Execute.class);
	}

	private void before(MethodInvocation invocation) {
		
		S2Container container = SingletonS2ContainerFactory.getContainer();
		HttpSession session = (HttpSession) container.getExternalContext().getSession();
		MUsersService mUsersService = (MUsersService) container.getComponent(MUsersService.class);

		UserDto userDto = (UserDto) session.getAttribute("userDto");

		if (userDto != null) {
			if (userDto.mUsers != null) {
				userDto.mUsers = mUsersService.findByUserId(userDto.getUserId());
			}
		}
	}

}
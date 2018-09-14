package jp.co.tmeic.mespd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jp.co.tmeic.mespd.dto.UserDto;
import jp.co.tmeic.mespd.dto.jqgrid.master.MUsersRoleDto;
import jp.co.tmeic.mespd.entity.MUsers;
import jp.co.tmeic.mespd.service.MUsersService;

public class ExcludeHotdeployFilter implements Filter {

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

	@SuppressWarnings("unused")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		Class<UserDto> userDto = UserDto.class;
		Class<MUsers> mUsers = MUsers.class;
		Class<MUsersRoleDto> mUsersRoleDto = MUsersRoleDto.class;
		Class<MUsersService> mUsersService = MUsersService.class;

		chain.doFilter(req, res);
	}
}

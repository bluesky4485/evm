package org.evm.biz.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.evm.biz.sys.SystemConfig;
import org.evm.biz.user.entity.UserVO;
import org.evm.core.consts.SystemConsts;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(false);
		if (request.getRequestURI().trim().contains("/loginController.do")) {
			chain.doFilter(req, resp);
			return;
		}
		try {
			UserVO loginVO = (UserVO) session.getAttribute(SystemConsts.LOGIN_INFO);
			if (loginVO == null) {
				forwardLoginPage(response);
			} else {
				updateVisiteTime(request, loginVO);
				chain.doFilter(req, response);
			}
		} catch (Exception e) {
			forwardLoginPage(response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private void forwardLoginPage(ServletResponse response) throws IOException, ServletException {
		java.io.PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open ('/"+SystemConfig.serverName+"/"+SystemConfig.loginPage+".jsp','_top')");
		out.println("</script>");
		out.println("</html>");
	}

	private void updateVisiteTime(HttpServletRequest request, UserVO loginVO) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		loginVO.setVisitTime(sdf.format(new Date()));
//		MDC.put("IP", loginVO.getUserIp());
//		MDC.put("LOGIN_ID", loginVO.getUserName());
//		request.setAttribute(SystemConsts.LOGIN_INFO, loginVO);
	}
}

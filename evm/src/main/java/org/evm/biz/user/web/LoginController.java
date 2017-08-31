package org.evm.biz.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.log.annotation.SystemControllerLog;
import org.evm.biz.sys.SystemConfig;
import org.evm.biz.user.entity.UserVO;
import org.evm.biz.user.service.ILoginUserService;
import org.evm.core.consts.SystemConsts;
import org.evm.core.util.RandomValidateCode;
import org.evm.core.web.AbstractMultiController;
import org.springframework.web.servlet.ModelAndView;

public class LoginController extends AbstractMultiController {

	private ILoginUserService loginUserService;

	public void setLoginUserService(ILoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SystemControllerLog(description = "用户登录")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String redirectpage = "redirect:" + SystemConfig.loginPage + ".jsp";
		ModelAndView mv = null;
		String userName = request.getParameter("userName");
		String passwd = request.getParameter("userPwd").trim();
		String checkCode = request.getParameter("checkCode").trim().toUpperCase();
		String sessionId = request.getSession().getId();
		String ip = getIpAddr(request).trim();
		// TODO:取消单点登录效果
		String valdiateCode = request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY).toString();
		if (!checkCode.equals(valdiateCode)) {
			mv = new ModelAndView(redirectpage);
			mv.addObject("loginInfo", "checkCodeFalse");
			return mv;
		} else {
			boolean hasFunc = loginUserService.canConnect(SystemConfig.systemId, userName);
			if (!hasFunc) {
				mv = new ModelAndView(redirectpage);
				mv.addObject("loginInfo", "noConnFunc");
				return mv;
			}
			mv = new ModelAndView("redirect:loginController.do?method=mainList");
			UserVO userVo = loginUserService.loginValidate(userName, passwd);
			if (userVo == null) {
				mv = new ModelAndView(redirectpage);
				mv.addObject("loginInfo", "false");
				return mv;
			} else {
				request.getSession().setAttribute(SystemConsts.LOGIN_INFO, userVo);
				request.getSession().setAttribute(SystemConsts.LOGIN_NAME, userVo.getUfullName());
				userVo.caculateJsonString();
				mv.addObject("LOGIN_INFO", userVo);
				return mv;
			}
		}
	}

	/**
	 * 首页刷新
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ModelAndView mainList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView(SystemConfig.mainPage);
		logger.info("mainList=" + SystemConfig.mainPage);
		UserVO loginvo = getCurrentLoginVO(request);
		if (loginvo != null) {
			mv.addObject("LOGIN_INFO", loginvo);
		}
		return mv;
	}

	public ModelAndView signOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String redirectpage = "redirect:" + SystemConfig.loginPage + ".jsp";
		ModelAndView mv = new ModelAndView(redirectpage);
		try {
			String userName = request.getParameter("userName");
			String sessionId = request.getSession().getId();
			request.getSession().removeAttribute(SystemConsts.LOGIN_INFO);
			request.getSession().removeAttribute(SystemConsts.LOGIN_NAME);
			logger.info(userName + "退出系统");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("退出异常", e);
		}
		return mv;
	}
}

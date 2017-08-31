package org.evm.biz.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;
import org.evm.biz.log.annotation.SystemControllerLog;
import org.evm.biz.user.entity.UserVO;
import org.evm.biz.user.service.IUserDbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends AbstractMultiController {
	IUserDbService userDbService;

	public void setUserDbService(IUserDbService userDbService) {
		this.userDbService = userDbService;
	}

	@SystemControllerLog(description = "查询用户")
	public void ajaxFindUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		UserVO whereCause = new UserVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		PageResult pageResult = null;
		int res = 0;
		try {
			pageResult = userDbService.findUsersPage(whereCause);
			List row = pageResult.getRows();
			if (row != null) {
				for (int i = 0; i < row.size(); i++) {
					UserVO user = (UserVO) row.get(i);
					user.setRoleName(user.getRoleListName());
				}
			}
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询用户户信息异常！" + e.getMessage();
			res = -1;
			logger.error(returnMsgContent, e);
		}

		if (res < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.noMessage);
		}
	}

	public void ajaxInsertUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		UserVO whereCause = new UserVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		// 待修改获得方式
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			UserVO vo = userDbService.getUserByName(whereCause.getUname());
			if (vo != null) {
				returnMsgContent = "用户名已经存在，请选择其它用户名！";
				res = -2;
				super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
				return;
			}
			res = userDbService.insertUser(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "插入失败，请联系管理员!" + e.getMessage();
			res = -1;
			logger.error(returnMsgContent, e);
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.noMessage);
		}
	}

	public void ajaxDeleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		UserVO whereCause = new UserVO();
		bindObject(request, whereCause);
		// 待修改 获取方式
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = userDbService.deleteUser(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "刪除用户信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.noMessage);
		}
	}

	public void ajaxUpdateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		UserVO whereCause = new UserVO();
		bindObject(request, whereCause);
		// 待修改 获取方式
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = userDbService.updateUser(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "修改用户信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.noMessage);
		}
	}

	public void ajaxFindUserById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		UserVO whereCause = new UserVO();
		bindObject(request, whereCause);
		UserVO resulst = null;
		try {
			resulst = userDbService.getUserById(whereCause.getUid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询用户异常，请联系管理员！";
			logger.error(returnMsgContent, e);
			res = -1;
		}
		super.ReturnAjaxResult(response, resulst);
	}

	public ModelAndView gotoUpdatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("updUser");
		UserVO whereCause = new UserVO();
		bindObject(request, whereCause);
		mv.addObject("updObj", whereCause);
		return mv;
	}

	public ModelAndView returnManagePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("userManage");
		return mv;
	}
}

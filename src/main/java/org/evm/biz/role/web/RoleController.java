package org.evm.biz.role.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.role.entity.RoleVO;
import org.evm.biz.role.service.IRoleDbService;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;
import org.springframework.web.servlet.ModelAndView;

public class RoleController extends AbstractMultiController {
	private IRoleDbService roleDbService;

	public void setRoleDbService(IRoleDbService roleDbService) {
		this.roleDbService = roleDbService;
	}

	public void ajaxFindRoleAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		List<RoleVO> pageResult = null;
		try {
			pageResult = this.roleDbService.findAllRoleIdName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "角色查询异常";
			logger.error(returnMsgContent, e);
		}
		super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
	}

	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindRolePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		RoleVO whereCause = new RoleVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		PageResult pageResult = null;
		try {
			pageResult = this.roleDbService.findRolePage(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "角色分页查询异常" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxInsertRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int result = 0;
		RoleVO whereCause = new RoleVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			result = this.roleDbService.insertRole(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			result = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = -1;
			returnMsgContent = "插入角色异常";
			logger.error(returnMsgContent, e);
		}
		if (result < 0) {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxDeleteRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int result = 0;
		RoleVO whereCause = new RoleVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			result = this.roleDbService.deleteRole(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			result = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = -1;
			returnMsgContent = "删除角色异常";
			logger.error(returnMsgContent, e);
		}
		if (result < 0) {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxUpdateRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int result = 0;
		RoleVO whereCause = new RoleVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			result = this.roleDbService.updateRole(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			result = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = -1;
			returnMsgContent = "更新角色异常";
			logger.error(returnMsgContent, e);
		}
		if (result < 0) {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindRoleById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		RoleVO whereCause = new RoleVO();
		bindObject(request, whereCause);
		RoleVO resulst = null;
		try {
			resulst = roleDbService.getRoleById(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询用户异常，请联系管理员！";
			logger.error(returnMsgContent, e);
			res = -1;
		}
		super.ReturnAjaxResult(response, resulst);
	}

	public ModelAndView gotoUpdatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("updRole");
		RoleVO whereCause = new RoleVO();
		bindObject(request, whereCause);
		mv.addObject("updObj", whereCause);
		return mv;
	}

	public ModelAndView returnManagePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("roleManage");
		return mv;
	}
}

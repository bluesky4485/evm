package org.evm.biz.custom.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.custom.entity.CustomVO;
import org.evm.biz.custom.service.ICustomDbService;
import org.evm.biz.sys.SysUtil;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author jerry.x update 2016年10月13日 下午12:25:41
 */
public class CustomController extends AbstractMultiController {
	ICustomDbService customDbService;

	public void setCustomDbService(ICustomDbService customDbService) {
		this.customDbService = customDbService;
	}

	/**
	 * 
	 * update by jerry.x 2016年10月10日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindCustom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		CustomVO whereCause = new CustomVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		PageResult pageResult = null;
		try {
			if(whereCause.getStopServiceDays()==30){
				whereCause.setStopServiceDays(SysUtil.getCurrentMonthLastDay());
			}
			pageResult = customDbService.findAllCustomPage(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询客户信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res == -1) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindCustomById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		CustomVO whereCause = new CustomVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		CustomVO pageResult = null;
		try {
			pageResult = this.customDbService.getCustomerById(whereCause.getCno());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询客户信息异常！" + e.getMessage();
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
	 * update by jerry.x 2016年10月13日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxInsertCustom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 1;
		String returnMsgContent = "sucess";
		CustomVO whereCause = new CustomVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res =this.customDbService.insertCustom(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "保存客户信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年10月13日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxUpdateCustom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		CustomVO whereCause = new CustomVO();
		bindObject(request, whereCause);
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			 	res = this.customDbService.updateCustom(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "修改客户信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 逻辑删除 update by jerry.x 2016年10月17日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public void ajaxDeleteCustom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		CustomVO whereCause = new CustomVO();
		bindObject(request, whereCause);
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res =this.customDbService.deleteCustom(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "删除客户信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		if (res == 0) {
			returnMsgContent = "数据已旧，删除失败！";
			res=-2;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}

	}

	/**
	 * 
	 * update by jerry.x 2016年10月21日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public ModelAndView gotoUpdatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("updCustomer");
		CustomVO whereCause = new CustomVO();
		bindObject(request, whereCause);
		mv.addObject("updObj", whereCause);
		return mv;
	}

	/**
	 * 
	 * update by jerry.x 2016年10月21日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public ModelAndView returnManagePage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("customMange");
		return mv;
	}
 
}

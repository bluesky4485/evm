package org.evm.biz.func.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.func.entity.AppServiceVO;
import org.evm.biz.func.entity.FuncVO;
import org.evm.biz.func.service.IFuncDbService;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;

public class FuncController extends AbstractMultiController {
	IFuncDbService funcDbService;

	public void setFuncDbService(IFuncDbService funcDbService) {
		this.funcDbService = funcDbService;
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
	public void ajaxFindFuncPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int result = 0;
		FuncVO whereCause = new FuncVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		PageResult pageResult = null;
		try {
			pageResult = this.funcDbService.findFuncPage(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			result = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "权限分页查询异常";
			logger.error(returnMsgContent, e);
			result = -1;
		}
		if (result < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}
	
	public void ajaxFindServiceFuncPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int result = 0;
		AppServiceVO whereCause = new AppServiceVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		List<AppServiceVO> pageResult = null;
		try {
			pageResult = this.funcDbService.getServiceFuncVO(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			result = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询权限与服务映射关系异常";
			logger.error(returnMsgContent, e);
			result = -1;
		}
		if (result < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.success);
		}
	}
}

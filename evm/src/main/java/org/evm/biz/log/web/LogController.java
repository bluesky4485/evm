package org.evm.biz.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.log.entity.LogVO;
import org.evm.biz.log.service.ILogDbService;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;

public class LogController extends AbstractMultiController {
	ILogDbService logDbService;

	public void setLogDbService(ILogDbService logDbService) {
		this.logDbService = logDbService;
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
	public void ajaxFindLogPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int result = 0;
		LogVO whereCause = new LogVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		PageResult pageResult = null;
		try {
			pageResult = this.logDbService.findLogPage(whereCause);
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
}

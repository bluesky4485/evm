package org.evm.biz.faulttype.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.core.consts.MessageType;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;
import org.evm.biz.faulttype.entity.FaultTypeVO;
import org.evm.biz.faulttype.service.IFaultTypeDbService;

public class FaultTypeController extends AbstractMultiController {
	IFaultTypeDbService faultTypeDbService;

	public void setFaultTypeDbService(IFaultTypeDbService faultTypeDbService) {
		this.faultTypeDbService = faultTypeDbService;
	}

	/**
	 * 
	 * update by jerry.x 2016年10月25日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindFaultType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		FaultTypeVO whereCause = new FaultTypeVO();
		bindObject(request, whereCause);
		List<FaultTypeVO> pageResult = null;
		try {
			pageResult = faultTypeDbService.findAllFaultType(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询故障异常！" + e.getMessage();
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
	 * update by jerry.x 2016年10月25日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxInsertFaultType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		FaultTypeVO whereCause = new FaultTypeVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		// 待修改获得方式
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = faultTypeDbService.insertFaultType(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = e.getMessage();
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
	 * update by jerry.x 2016年10月25日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxDeleteFaultType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		FaultTypeVO whereCause = new FaultTypeVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		// 待修改 获取方式
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = faultTypeDbService.deleteFaultType(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "刪除故障类型信息异常！" + e.getMessage();
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
	 * update by jerry.x 2016年10月25日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxUpdateFaultType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		FaultTypeVO whereCause = new FaultTypeVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = faultTypeDbService.updateFaultType(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "更新信息异常！" + e.getMessage();
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
	 * update by jerry.x 2016年10月25日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	private List<FaultTypeVO> searchFaultTypeVO(FaultTypeVO whereCause) {
		String returnMsgContent = "sucess";
		List<FaultTypeVO> pageResult = null;
		try {
			pageResult = faultTypeDbService.findAllFaultType(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询故障类型信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		return pageResult;
	}
}

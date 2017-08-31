package org.evm.biz.order.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;
import org.evm.biz.order.entity.DeviceItemPropertyVO;
import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderDeviceVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.order.service.IDeviceItemService;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.biz.order.service.IOrderDeviceService;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

public class DeviceItemController extends AbstractMultiController {
	

	private IDeviceItemService deviceItemService;
	private IOrderDeviceService orderDeviceService;
	public void setDeviceItemService(IDeviceItemService deviceItemService) {
		this.deviceItemService = deviceItemService;
	}
	 
	public void setOrderDeviceService(IOrderDeviceService orderDeviceService) {
		this.orderDeviceService = orderDeviceService;
	}

	/**
	 * 
	 * update by jerry.x 2016年11月8日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindDeviceItemPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		DeviceItemVO whereCause = new DeviceItemVO();
		bindObject(request, whereCause);
		PageResult result = null;
		try {
			result = this.deviceItemService.findDeviceItemPage(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询设备信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxDeleteDeviceItemById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		DeviceItemVO whereCause = new DeviceItemVO();
		bindObject(request, whereCause);
		try {
			res = this.deviceItemService.deleteDeviceItemById(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "删除设备信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindDeviceItemById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		DeviceItemVO whereCause = new DeviceItemVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		DeviceItemVO pageResult = null;
		try {
			pageResult = this.deviceItemService.findDeviceItemById(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询订单信息异常！" + e.getMessage();
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
	 * 批量存储属性值
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void ajaxInsertDeviceItemProperty(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String returnMsgContent = "sucess";
		DeviceItemVO whereCause = new DeviceItemVO();
		bindObject(request, whereCause);
		int res = 0;
		List<DeviceItemPropertyVO> list = new ArrayList<DeviceItemPropertyVO>();
		String Str = whereCause.getDeviceItemPropertyStr();
		list = JSON.parseArray(Str, DeviceItemPropertyVO.class);
		String userId = super.getLoginUserId(request);
		for (int i = 0; list != null && i < list.size(); i++) {
			list.get(i).setInsUser(userId);
			list.get(i).setUpdUser(userId);
		}
		try {
			long deviceitemId = whereCause.getDeviceItemId();
			res = deviceItemService.batchInsertDeviceItemProperty(whereCause, list);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "批量存储属性值异常！" + e.getMessage();
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
	 * 增加设备 update by jerry.x 2016年11月9日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxInsertDeviceItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		DeviceItemVO whereCause = new DeviceItemVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		// TODO:增加设备配置之前，需要判断，统计的设备总数是否小于等于（<=）订单中设置的总数量
		OrderDeviceVO orderDeviceVO=	orderDeviceService.getOrderDeviceByCond(whereCause.getOrderId(),whereCause.getDeviceTypeId());
		if(orderDeviceVO!=null&&orderDeviceVO.getDeviceCnt()<=orderDeviceVO.getStoreCnt()){
			super.ReturnAjaxResult(response, -1, "设备【"+orderDeviceVO.getDeviceName()+"】已安装总数已为订单中设置的最大值，不能继续安装，如需安装，请联系管理员！", MessageType.error);
			return ;
		}
		long res = 0;
		try {
			List<DeviceItemPropertyVO> list = new ArrayList<DeviceItemPropertyVO>();
			String Str = whereCause.getDeviceItemPropertyStr();
			list = JSON.parseArray(Str, DeviceItemPropertyVO.class);
			if (list != null && list.size() > 0) {
				whereCause.setDeviceItemPropertyList(list);
			}
			res = deviceItemService.insertDeviceItem(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "存储设备信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	public ModelAndView gotoUpdatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeviceItemVO whereCause = new DeviceItemVO();
		bindObject(request, whereCause);
		ModelAndView mv = new ModelAndView("updDeviceItem");
		mv.addObject("updObj", whereCause);
		return mv;
	}

	public ModelAndView returnManagePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("deviceItemMange");
		return mv;
	}
}

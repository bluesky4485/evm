package org.evm.biz.device.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.device.entity.DeviceTypePropertyVO;
import org.evm.biz.device.entity.DeviceTypeVO;
import org.evm.biz.device.service.IDeviceTypeDbService;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

public class DeviceTypeController extends AbstractMultiController {
	IDeviceTypeDbService deviceTypeDbService;

	public void setDeviceTypeDbService(IDeviceTypeDbService deviceTypeDbService) {
		this.deviceTypeDbService = deviceTypeDbService;
	}

	/**
	 * 分页查询 update by jerry.x 2016年10月31日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindDevicePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		DeviceTypeVO whereCause = new DeviceTypeVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		PageResult pageResult = null;
		try {
			pageResult = deviceTypeDbService.findAllDeviceTypePage(whereCause);
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
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxInsertDevice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		DeviceTypeVO whereCause = new DeviceTypeVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		List<DeviceTypeVO> pageResult = null;
		try {
			if (whereCause != null) {
				String properties = whereCause.getAllPropertys();
				List<DeviceTypePropertyVO> list = JSON.parseArray(properties, DeviceTypePropertyVO.class);
				if (properties != "" && list.size() > 0)
					whereCause.setProperties(list);
			}
			res = deviceTypeDbService.insertDeviceType(whereCause);
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
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxDeleteDevice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		DeviceTypeVO whereCause = new DeviceTypeVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = deviceTypeDbService.deleteDeviceType(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "刪除设备信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 删除属性
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void ajaxDeleteDevicePropertyByID(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		DeviceTypePropertyVO whereCause = new DeviceTypePropertyVO();
		bindObject(request, whereCause);
		try {
			res = deviceTypeDbService.deleteDeviceTypeProperty(Long.parseLong(whereCause.getPropertyId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "刪除设备信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
	}

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */

	public void ajaxUpdateDevice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		DeviceTypeVO whereCause = new DeviceTypeVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			if (whereCause != null) {
				String properties = whereCause.getAllPropertys();
				if (properties != null) {
					List<DeviceTypePropertyVO> list = JSON.parseArray(properties, DeviceTypePropertyVO.class);
					if (list.size() > 0)
						whereCause.setProperties(list);
					for (DeviceTypePropertyVO vo : list) {
						vo.setDeviceTypeId(whereCause.getDeviceId());
					}
				}
			}
			res = deviceTypeDbService.updateDDeviceType(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "更新设备信息异常！" + e.getMessage();
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
	 * 返回管理页 update by jerry.x 2016年10月24日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public ModelAndView returnManagePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("deviceMange");
		return mv;
	}

	/**
	 * 进入更新 update by jerry.x 2016年10月31日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public ModelAndView gotoUpdatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("updDevice");
		DeviceTypeVO whereCause = new DeviceTypeVO();
		bindObject(request, whereCause);
		mv.addObject("updObj", whereCause);
		return mv;
	}

	/**
	 * 
	 * update by jerry.x 2016年10月31日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxGetDeviceById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		DeviceTypeVO whereCause = new DeviceTypeVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		DeviceTypeVO updObj = null;
		try {
			updObj = deviceTypeDbService.findAllDeviceTypeByID(whereCause.getDeviceId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询设备信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		super.ReturnAjaxResult(response, updObj, returnMsgContent, MessageType.info);
	}

	public void ajaxFindDeviceByType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		DeviceTypeVO whereCause = new DeviceTypeVO();
		bindObject(request, whereCause);
		List<DeviceTypeVO> pageResult = null;
		try {
			pageResult = deviceTypeDbService.findAllDeviceTypeByClassType(whereCause);
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
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}
}

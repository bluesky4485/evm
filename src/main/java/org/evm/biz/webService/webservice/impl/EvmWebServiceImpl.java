package org.evm.biz.webService.webservice.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.evm.biz.device.entity.DeviceTypeVO;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.entity.LogVO;
import org.evm.biz.log.service.ILogDbService;
import org.evm.biz.morder.entity.MOrderFileVO;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderDeviceVO;
import org.evm.biz.order.entity.OrderFileVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.entity.RelProjectDeviceVO;
import org.evm.biz.user.entity.UserVO;
import org.evm.biz.webService.entity.ResponseResult;
import org.evm.biz.webService.entity.WsEntity;
import org.evm.biz.webService.service.IAppMorderService;
import org.evm.biz.webService.service.IAppOrderService;
import org.evm.biz.webService.service.IAppProjectService;
import org.evm.biz.webService.service.IAppUserService;
import org.evm.biz.webService.util.WebServiceUtil;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.AbstractEntity;
import org.evm.core.log.DataCenterLogger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

@Path(value = "/evmws")
public class EvmWebServiceImpl {
	protected DataCenterLogger logger = new DataCenterLogger(this.getClass());

	@POST
	@Path("/CodeServices/")
	@Produces({ MediaType.APPLICATION_JSON })
	public String CodeServices(String para) {
		// TODO Auto-generated method stub
		String json = "";
		String[] filterField = new String[] { "projectID", "serviceId", "seq", "systemId", "uid", "token",
				"responseResult", "message", "messageType" };
		// TODO:返回实体
		WsEntity entity = null;
		try {
			entity = WebServiceUtil.decodeWsPara(para);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("解析参数异常！", e);
		}
		if (entity == null) {
			entity = new WsEntity();
			entity.setMessage("参数解析异常，参数应为JSON格式！");
			entity.setMessageType(MessageType.error.toString());
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WsEntity.class, filterField);
			json = JSON.toJSONString(entity, filter);
			return json;
		}
		AbstractEntity returnEntity = null;
		// 登录单走校验：
		if (!entity.getServiceId().equals("300001")) {
			boolean hasFunc = this.appUserService.hasFuncForApp(entity);
			if (!hasFunc) {
				entity.setMessage("用户无操作权限");
				entity.setMessageType(MessageType.error.toString());
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WsEntity.class, filterField);
				json = JSON.toJSONString(entity, filter);
				return json;
			}
		}
		// 获得和ServiceId匹配的数据库操作服务
		if (entity.getServiceId().equals("300001")) {
			// 用户登录
			UserVO paraEntity = (UserVO) (entity.getParaEntity());
			boolean canConn = this.appUserService.canConnect(entity.getSystemId(), paraEntity.getUname());
			if (canConn) {
				// 调用服务
				returnEntity = (UserVO) appUserService.login(paraEntity);
				if (returnEntity == null) {
					entity.setMessage("用户登录失败");
					entity.setMessageType(MessageType.error.toString());
				} else {
					ResponseResult rr = new ResponseResult();
					rr.setPageNum(paraEntity.getPageNum());
					rr.setPageSize(paraEntity.getPageSize());
					rr.setDataResult(returnEntity);
					entity.setResponseResult(rr);
				}
			} else {
				entity.setMessage("用户无登录权限，请联系管理员！");
				entity.setMessageType(MessageType.error.toString());
			}
			if (entity.getSystemId() == "100") {
				recordLog(para, entity, "用户登录", BizOptType.query, FunctionMap.APP_LOGOIN_USR);
			} else if (entity.getSystemId() == "200") {
				recordLog(para, entity, "用户登录", BizOptType.query, FunctionMap.APP_LOGOIN_PROJECT);
			} else if (entity.getSystemId() == "300") {
				recordLog(para, entity, "用户登录", BizOptType.query, FunctionMap.APP_LOGOIN_MORDER);
			}

		} else if (entity.getServiceId().equals("300002")) {
			// 用户密码修改
			UserVO paraEntity = (UserVO) (entity.getParaEntity());
			int res = appUserService.updateUserPwd(paraEntity);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
		} else if (entity.getServiceId().equals("100100")) {
			// 查询全部订单统计
			OrderVO whereCause = (OrderVO) (entity.getParaEntity());
			List<OrderVO> orderList = appOrderService.findOrderAndMorder(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(orderList);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100200")) {
			// 查询全部工程订单信息
			OrderVO whereCause = (OrderVO) (entity.getParaEntity());
			List<OrderVO> orderList = appOrderService.findAllOrder(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(orderList);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100201")) {
			// 模糊查询某一订单的工程信息。
			ProjectVO whereCause = (ProjectVO) (entity.getParaEntity());
			ProjectVO projectVO = appProjectService.getProjectVO(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(projectVO);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100202")) {
			// 查询某一个工程订单的详细信息
			OrderVO whereCause = (OrderVO) (entity.getParaEntity());
			OrderVO orderVO = appOrderService.getOrder(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(orderVO);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100203")) {
			// 查询某一个工程订单的设备信息。
			OrderDeviceVO whereCause = (OrderDeviceVO) (entity.getParaEntity());
			List<OrderDeviceVO> list = appOrderService.findOrderDeviceVO(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(list);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100205")) {
			// 查询某一个工程订单中的设备的属性信息。
			DeviceItemVO whereCause = (DeviceItemVO) (entity.getParaEntity());
			if (whereCause.getOrderId() == 0) {
				entity.setMessage("参数需要存在订单ID");
				entity.setMessageType(MessageType.error.toString());
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WsEntity.class, filterField);
				json = JSON.toJSONString(entity, filter);
				return json;
			}
			List<DeviceItemVO> list = appOrderService.findDeviceItemListByOrder(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(list);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100206")) {
			// 查询某一个工程订单中的设备的属性信息。
			DeviceTypeVO whereCause = (DeviceTypeVO) (entity.getParaEntity());
			if (whereCause.getProjectId() == "") {
				entity.setMessage("参数需要存在项目ID");
				entity.setMessageType(MessageType.error.toString());
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WsEntity.class, filterField);
				json = JSON.toJSONString(entity, filter);
				return json;
			}
			List<DeviceTypeVO> list = appProjectService.findDeviceTypeByProjectId(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(list);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("310205")) {
			// 修改某一工程订单的评价信息。
			OrderVO whereCause = (OrderVO) (entity.getParaEntity());
			int res = appOrderService.updateOrderUserAppraise(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改工程订单的评价信息", BizOptType.update, FunctionMap.APP_UPD_ORDER_USERAPPRISE);
		} else if (entity.getServiceId().equals("310206")) {
			// 修改某一工程订单的施工位置
			OrderVO whereCause = (OrderVO) (entity.getParaEntity());
			int res = appOrderService.updateOrderWorkAddress(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改工程订单的施工位置", BizOptType.update, FunctionMap.APP_UPD_ORDER_USERAPPRISE);
		}else if (entity.getServiceId().equals("100300")) {
			// 查询全部维修/维护订单信息。
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			List<MOrderVO> list = appMorderService.findAllMorderList(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(list);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100301")) {
			// 模糊查询某一订单的维修/维护信息。
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			List<MOrderVO> list = appMorderService.findAllMorderList(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(list);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100302")) {
			// 查询某一个维修/维护订单的详细信息。
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			MOrderVO morderVO = appMorderService.getMOrderVO(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(morderVO);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100303")) {
			// 查询某一个订单的全部维修记录信息。
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			List<MOrderVO> list = appMorderService.findAllMorderList(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(list);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("100312")) {
			// 查询某一个订单的全部维修记录信息。
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			MOrderVO list = appMorderService.getLastMoarder(whereCause);
			ResponseResult rr = new ResponseResult();
			rr.setPageNum(whereCause.getPageNum());
			rr.setPageSize(whereCause.getPageSize());
			rr.setDataResult(list);
			entity.setResponseResult(rr);
		} else if (entity.getServiceId().equals("310305")) {
			// 修改某一维修/维护订单的评价信息
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			int res = appMorderService.updateUserProposal(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改维修订单评价信息", BizOptType.update, FunctionMap.APP_UPD_MORDER_USERAPPRISE);
		} else if (entity.getServiceId().equals("330306")) {
			// 修改维修开工时间。
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			// 330306这个开工时间maintainBeginDate，用后台服务器时间吧
			whereCause.setMaintainBeginDate(sdf.format(dt));
			int res = appMorderService.updateMaintainBeginDate(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改维修订单开工时间", BizOptType.update, FunctionMap.APP_UPD_MORDER);
		} else if (entity.getServiceId().equals("330307")) {
			// 修改维修进度。
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			int res = appMorderService.updateMaintainSpeed(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改维修订单维修进度", BizOptType.update, FunctionMap.APP_UPD_MORDER);
		} else if (entity.getServiceId().equals("330308")) {
			// 修改故障类型
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			int res = appMorderService.updateFaultType(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改故障类型", BizOptType.update, FunctionMap.APP_UPD_MORDER);
		} else if (entity.getServiceId().equals("330309")) {
			// 修改维修结果描述
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			int res = appMorderService.updateMaintainResultdesc(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改维修订单维修结果描述", BizOptType.update, FunctionMap.APP_UPD_MORDER);
		} else if (entity.getServiceId().equals("320206")) {
			// 修改订单施工时间
			OrderVO whereCause = (OrderVO) (entity.getParaEntity());
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			// 330306这个开工时间maintainBeginDate，用后台服务器时间吧
			whereCause.setWorkBeginDate(sdf.format(dt));
			int res = this.appOrderService.updateOrderWorkBeginDate(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改工程开工时间", BizOptType.update, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320207")) {
			// 修改订单施工进度。
			OrderVO whereCause = (OrderVO) (entity.getParaEntity());
			int res = appOrderService.updateOrderWorkProgress(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改工程开工时间", BizOptType.update, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320208")) {
			// 增加工程中某一订单的设备种类
			RelProjectDeviceVO whereCause = (RelProjectDeviceVO) (entity.getParaEntity());
			int res = appProjectService.addRelProjectDeviceVO(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "增加工程中某一设备种类", BizOptType.add, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320209")) {
			// 删除工程中某一订单的设备种类 IAppProjectService
			RelProjectDeviceVO whereCause = (RelProjectDeviceVO) (entity.getParaEntity());
			int res = appProjectService.deleteRelProjectDeviceVO(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "删除工程中某一设备种类", BizOptType.delete, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320210")) {
			// 修改工程中某一订单的设备的数量信息
			RelProjectDeviceVO whereCause = (RelProjectDeviceVO) (entity.getParaEntity());
			int res = appProjectService.updateRelProjectDeviceVOById(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "修改工程中设备的数量信息", BizOptType.update, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320211")) {
			//  320211 修改工程中某一订单的设备的数量信息：

		} else if (entity.getServiceId().equals("320212")) {
			//  320212增加设备
			DeviceItemVO whereCause = (DeviceItemVO) (entity.getParaEntity());
			int res = appOrderService.addDeviceItem(whereCause);
			if (res > 0) {
				entity.setMessage("增加设备成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("增加设备失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "增加设备", BizOptType.add, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320213")) {
			//  320213删除设备
			DeviceItemVO whereCause = (DeviceItemVO) (entity.getParaEntity());
			int res = appOrderService.deleteDeviceItem(whereCause);
			if (res != 0) {
				entity.setMessage("删除成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("删除失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "删除设备", BizOptType.delete, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320214")) {
			//  320214修改设备属性
			DeviceItemVO whereCause = (DeviceItemVO) (entity.getParaEntity());
			int res = appOrderService.updateDeviceItem(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "更新设备", BizOptType.update, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320215")) {
			// 上传施工图纸
			OrderFileVO file = (OrderFileVO) (entity.getParaEntity());
			String fileType=getFileType(file.getFileName());
			file.setFileType(fileType);
			long res = appOrderService.uploadOrderWorkImg(file);
			if (res != 0) {
				entity.setMessage("更新成功!");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "更新订单，上传订单施工资料", BizOptType.update, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("320216")) {
			// 删除施工图纸
			OrderFileVO file = (OrderFileVO) (entity.getParaEntity());
			long res = appOrderService.deleteOrderWorkImg(file);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "更新订单，删除订单施工资料", BizOptType.update, FunctionMap.APP_UPD_PROJECT);
		} else if (entity.getServiceId().equals("330310")) {
			// 上传维修照片
			MOrderFileVO file = (MOrderFileVO) (entity.getParaEntity());
			String fileType=getFileType(file.getFileName());
			file.setFileType(fileType);
			long res = 0;
			try {
				res = appMorderService.uploadMOrderWorkImg(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("上传图片异常" + e.getMessage());
			}
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "更新运维订单，上传施工资料", BizOptType.update, FunctionMap.APP_UPD_PROJECT);

		} else if (entity.getServiceId().equals("330311")) {
			// 删除运维订单文件
			MOrderFileVO file = (MOrderFileVO) (entity.getParaEntity());
			int res = 0;
			try {
				res = appMorderService.deleteMOrderWorkImg(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("删除运维订单文件异常" + e.getMessage());
			}
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "更新运维订单，删除施工资料", BizOptType.update, FunctionMap.APP_UPD_PROJECT);

		}else if(entity.getServiceId().equals("330312")){
			//修改维修人数
			MOrderVO whereCause = (MOrderVO) (entity.getParaEntity());
			int res=appMorderService.updateMorderWorkerCnt(whereCause);
			if (res != 0) {
				entity.setMessage("更新成功");
				entity.setMessageType(MessageType.success.toString());
			} else {
				entity.setMessage("更新失败");
				entity.setMessageType(MessageType.error.toString());
			}
			recordLog(para, entity, "更新运维订单，修改维修人数", BizOptType.update, FunctionMap.APP_UPD_PROJECT);

		}
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WsEntity.class, filterField);
		json = JSON.toJSONString(entity, filter);
		return json;
	}

	private String getFileType(String fileName) {
		String splitReg = "\\.";
		String[] arr = fileName.split(splitReg);
		if (arr.length > 1) {
			return arr[1];
		} else {
			return "jpg";
		}
	}

	private void recordLog(String para, WsEntity entity, String logContent, String optType, String func) {
		if (para != null && para.length() > 600) {
			para = para.substring(0, 599);
		}
		try {
			LogVO whereCause = new LogVO();
			whereCause.setLogPara(para);
			whereCause.setLogContent(logContent);
			whereCause.setLogBizType(optType);
			whereCause.setLogBizId(Long.parseLong(func));
			whereCause.setInsUser(entity.getUid());
			logDbService.insertLog(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("restful记录日志异常!参数=" + para, e);
		}
	}

	private IAppUserService appUserService;
	private IAppProjectService appProjectService;
	private IAppOrderService appOrderService;
	private IAppMorderService appMorderService;
	private ILogDbService logDbService;

	public void setLogDbService(ILogDbService logDbService) {
		this.logDbService = logDbService;
	}

	public void setAppUserService(IAppUserService appUserService) {
		this.appUserService = appUserService;
	}

	public void setAppProjectService(IAppProjectService appProjectService) {
		this.appProjectService = appProjectService;
	}

	public void setAppOrderService(IAppOrderService appOrderService) {
		this.appOrderService = appOrderService;
	}

	public void setAppMorderService(IAppMorderService appMorderService) {
		this.appMorderService = appMorderService;
	}

	// ------------------
	/*
	 * @POST
	 * 
	 * @Produces("application/json")
	 * 
	 * @Path("/upload/{isSafe}") public String uploadFile(@PathParam("isSafe")
	 * String safe, MultipartBody body) { try { long t = new Date().getTime();
	 * for (Attachment a : body.getAllAttachments()) { // save file to location
	 * DataHandler handler = a.getDataHandler(); ContentDisposition cd =
	 * a.getContentDisposition(); String fileName = cd.getParameter("file");
	 * String npath = ""; String dpath = ""; int safeFlag = 1;
	 * 
	 * if (safe.equals("safe")) { npath = path + "\\safe\\"; dpath = dbFilePath
	 * + "safe/" + fileName; } else if (safe.equals("unsafe")) { npath = path +
	 * "\\unsafe\\"; dpath = dbFilePath + "unsafe/" + fileName; safeFlag = 0; }
	 * 
	 * if (npath.equals("")) return Constants.STATUS_FAIL_WITH_NOMSG;
	 * 
	 * File f = new File(npath + fileName); System.out.println("file path:" +
	 * f.getAbsolutePath()); if (handler.getInputStream().available() > 0)
	 * System.out.println("get input stream"); FileHelper.writeFileWithStream(f,
	 * handler.getInputStream());
	 * 
	 * // update db String sql = "insert into file_info values(0,'" + dpath +
	 * "'," + t + "," + safeFlag + ")"; int i =
	 * JDBCHelper.getTemplate().update(sql); if (i < 1) return
	 * Constants.STATUS_FAIL_WITH_NOMSG;
	 * 
	 * return Constants.STATUS_SUCCESS_WITH_NOMSG; } return
	 * Constants.STATUS_FAIL_WITH_NOMSG; } catch (Exception e) {
	 * e.printStackTrace(); return Constants.STATUS_FAIL_WITH_NOMSG; }
	 * 
	 * }
	 */
}

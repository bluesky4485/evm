package org.evm.biz.statics.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.statics.service.IStaticsDbService;
import org.evm.core.consts.MessageType;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.util.ExcelUtil;
import org.evm.core.web.AbstractMultiController;

public class StaticsController extends AbstractMultiController {
	IStaticsDbService staticsDbService;

	public void setStaticsDbService(IStaticsDbService staticsDbService) {
		this.staticsDbService = staticsDbService;
	}

	/**
	 * 项目统计
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void ajaxStaticsProject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		List<ProjectVO> pageResult = null;
		try {
			pageResult = staticsDbService.findProjectStaticsData(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "统计项目信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxStaticsMOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		List<MOrderVO> pageResult = null;
		try {
			pageResult = staticsDbService.findMorderStaticsData(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "统计项目信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxStaticsOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		List<OrderVO> pageResult = null;
		try {
			pageResult = staticsDbService.findOrderStaticsData(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "统计订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	@Override
	protected void doExcelWrite(ExcelUtil util, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String exportMethod = request.getParameter("exportMethod");
		if ("exportProject".equals(exportMethod)) {
			ExprotProject(util, request);
		} else if ("exportMorder".equals(exportMethod)) {
			ExprotMorder(util, request);
		} else if ("exportOrder".equals(exportMethod)) {
			ExprotOrder(util, request);
		} else if ("exportDviceItem".equals(exportMethod)) {
			ExprotDviceItem(util, request);
		}
	}

	private void ExprotMorder(ExcelUtil util, HttpServletRequest request) throws Exception {
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
		List<MOrderVO> pageResult = staticsDbService.findMorderStaticsData(whereCause);

		String[] header = new String[] { "项目编号", "项目名称", "订单编号", "维修订单编号", "运维经理", "派修时间", "故障描述","预约时间", "计划完成时间", "维修人数", "维修开始日期",
				"维修状态", "维修进度", "故障类别", "维修负责人", "验收状态", "验收回访人", "解决方案", "维修结果描述", "备注", "用户评分","用户评价", "最后一次进度更新时间",
				"创建人", "创建日期", "修改人", "修改日期" };
		util.createHeader(header);
		for (int i = 0; i < pageResult.size(); i++) {
			MOrderVO po = pageResult.get(i);
			util.setValue(i + 1, 0, po.getProjectNo()==null?"": po.getProjectNo());
			util.setValue(i + 1, 1, po.getProjectName()==null?"":po.getProjectName());
			util.setValue(i + 1, 2, po.getOrderNo()==null?"":po.getOrderNo());
			util.setValue(i + 1, 3, po.getMorderNo()==null?"":po.getMorderNo());
			util.setValue(i + 1, 4, po.getPmName()==null?"":po.getPmName());
			//
			util.setValue(i + 1, 5, po.getMaintainDate()==null?"":po.getMaintainDate());
			util.setValue(i + 1, 6, po.getFaultDesc()==null?"":po.getFaultDesc());
			//
			util.setValue(i + 1, 7, po.getAppointmentDate()==null?"":po.getAppointmentDate());
			util.setValue(i + 1, 8, po.getPlanEndDate()==null?"":po.getPlanEndDate());
			util.setValue(i + 1, 9, po.getMworkerCnt()==null?"":po.getMworkerCnt());
			util.setValue(i + 1, 10, po.getMaintainBeginDate()==null?"":po.getMaintainBeginDate());
			util.setValue(i + 1, 11, po.getMaintainStatusDesc()==null?"":po.getMaintainStatusDesc());
			util.setValue(i + 1, 12, po.getMaintainSpeed()==null?"":po.getMaintainSpeed());
			util.setValue(i + 1, 13, po.getFaultTypeDesc()==null?"":po.getFaultTypeDesc());
			util.setValue(i + 1, 14, po.getMaintainPmName()==null?"":po.getMaintainPmName());
			
			util.setValue(i + 1, 15, po.getAcceptStatusDesc()==null?"":po.getAcceptStatusDesc());

			util.setValue(i + 1, 16, po.getAcceptecallMan()==null?"":po.getAcceptecallMan());
			util.setValue(i + 1, 17, po.getSolution()==null?"":po.getSolution());
			util.setValue(i + 1, 18, po.getMaintainResultdesc()==null?"":po.getMaintainResultdesc());
			util.setValue(i + 1, 19, po.getMaintainRemark()==null?"":po.getMaintainRemark());
			util.setValue(i + 1, 20, po.getUserScore()==null?"":po.getUserScore());
			util.setValue(i + 1, 21, po.getUserProposal()==null?"":po.getUserProposal());
			util.setValue(i + 1, 22, po.getLastUpdDate()==null?"":po.getLastUpdDate());
			util.setValue(i + 1, 23, po.getInsUser()==null?"":po.getInsUser());
			util.setValue(i + 1, 24, po.getInsDate()==null?"":po.getInsDate());
			util.setValue(i + 1, 25, po.getUpdUser()==null?"":po.getUpdUser());
			util.setValue(i + 1, 26, po.getUpdDate()==null?"":po.getUpdDate());
		}
	}

	
	private void ExprotOrder(ExcelUtil util, HttpServletRequest request) throws Exception {
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		List<OrderVO> pageResult = staticsDbService.findOrderStaticsData(whereCause);

		String[] header = new String[] { "项目编号", "项目名称", "订单编号", "施工负责人", "施工人数","施工地点","施工单位", "单位资质情况", "施工类型", "施工天数", "计划施工时间",
				"出库联系人", "计划出库时间", "施工状态", "实际施工时间", "实际施工完成时间", "施工进度", "最后一次施工更新时间", "内检人员", "施工内检结果", "内检时间", "用户评分","用户评价", "备注", "创建人",
				"创建日期" ,"修改人","修改日期"};
		util.createHeader(header);
		for (int i = 0; i < pageResult.size(); i++) {

			OrderVO po = pageResult.get(i);
			util.setValue(i + 1, 0, po.getProjectNo()==null?"":po.getProjectNo());
			util.setValue(i + 1, 1, po.getProjectName()==null?"":po.getProjectName());
			util.setValue(i + 1, 2, po.getOrderNo()==null?"":po.getOrderNo());
			util.setValue(i + 1, 3, po.getWorkPmName()==null?"":po.getWorkPmName());
			util.setValue(i + 1, 4, po.getWorkCnt()==null?"":po.getWorkCnt());
			util.setValue(i + 1, 5, po.getWorkAddress()==null?"":po.getWorkAddress());
			util.setValue(i + 1, 6, po.getWorkCompany()==null?"":po.getWorkCompany());
			util.setValue(i + 1, 7, po.getWorkCompanyQualified()==null?"":po.getWorkCompanyQualified());
			util.setValue(i + 1, 8, po.getWorkTypeDesc()==null?"":po.getWorkTypeDesc());
			util.setValue(i + 1, 9, po.getWorkDays()==null?"":po.getWorkDays());
			util.setValue(i + 1, 10, po.getPlanBeginDate()==null?"":po.getPlanBeginDate());
			util.setValue(i + 1, 11, po.getStorePmName()==null?"":po.getStorePmName());
			util.setValue(i + 1, 12, po.getPlanOutstoreDate()==null?"":po.getPlanOutstoreDate());
			util.setValue(i + 1, 13, po.getWorkStatusDesc()==null?"":po.getWorkStatusDesc());
			util.setValue(i + 1, 14, po.getWorkBeginDate()==null?"":po.getWorkBeginDate());
			util.setValue(i + 1, 15, po.getWorkEndDate()==null?"":po.getWorkEndDate());
			util.setValue(i + 1, 16, po.getWorkProgress()==null?"":po.getWorkProgress());
			util.setValue(i + 1, 17, po.getLastWorkupdDate()==null?"":po.getLastWorkupdDate());
			util.setValue(i + 1, 18, po.getCheckerName()==null?"":po.getCheckerName());
			util.setValue(i + 1, 19, po.getCheckResultDesc()==null?"":po.getCheckResultDesc());
			util.setValue(i + 1, 20, po.getCheckDate()==null?"":po.getCheckDate()); 
			util.setValue(i + 1, 21, po.getUserRating()==null?"":po.getUserRating());
			util.setValue(i + 1, 22, po.getUserAppraise()==null?"":po.getUserAppraise());
			util.setValue(i + 1, 23, po.getWorkRemark()==null?"":po.getWorkRemark());
			util.setValue(i + 1, 24, po.getInsUser()==null?"":po.getInsUser());
			util.setValue(i + 1, 25, po.getInsDate()==null?"":po.getInsDate());
			util.setValue(i + 1, 26, po.getUpdUser()==null?"":po.getUpdUser());
			util.setValue(i + 1, 27, po.getUpdDate()==null?"":po.getUpdDate());
		}
	}
	
	/**
	 * 导出项目
	 * 
	 * @param util
	 * @param request
	 * @throws Exception
	 */
	private void ExprotProject(ExcelUtil util, HttpServletRequest request) throws Exception {
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);
		List<ProjectVO> pageResult = staticsDbService.findProjectStaticsData(whereCause);

		String[] header = new String[] { "项目名称", "项目编号", "客户联系人", "客户经理", "项目经理", "项目类别", "合同编号", "合同金额", "签订日期",
				"缴费类型", "工期截止日期", "开工日期", "年服务费", "服务截止日期", "缴费日期", "建设方式", "入网日期", "停机日期", "备注", "创建人", "创建日期", "修改人",
				"修改日期" };
		util.createHeader(header);
		for (int i = 0; i < pageResult.size(); i++) {
			ProjectVO po = pageResult.get(i);
			util.setValue(i + 1, 0, po.getProjectName()==null?"":po.getProjectName());
			util.setValue(i + 1, 1, po.getProjectNo()==null?"":po.getProjectNo());
			util.setValue(i + 1, 2, po.getCusName()==null?"":po.getCusName());
			util.setValue(i + 1, 3, po.getCmName()==null?"":po.getCmName());
			util.setValue(i + 1, 4, po.getPmName()==null?"":po.getPmName());
			util.setValue(i + 1, 5, po.getProjectTypeDesc()==null?"":po.getProjectTypeDesc());
			util.setValue(i + 1, 6, po.getContractId()==null?"":po.getContractId());
			util.setValue(i + 1, 7, po.getContractSum()==null?"":po.getContractSum().toString());
			util.setValue(i + 1, 8, po.getSignDate()==null?"":po.getSignDate());
			util.setValue(i + 1, 9, po.getPayType()==null?"":po.getPayType());
			util.setValue(i + 1, 10, po.getWorkEndDate()==null?"":po.getWorkEndDate());
			util.setValue(i + 1, 11, po.getWorkStartDate()==null?"":po.getWorkStartDate());
			util.setValue(i + 1, 12, po.getYearPay()==null?"":po.getYearPay());
			util.setValue(i + 1, 13, po.getServiceEndDate()==null?"":po.getServiceEndDate());
			util.setValue(i + 1, 14, po.getPayDate()==null?"":po.getPayDate());
			util.setValue(i + 1, 15, po.getBuildTypeDesc()==null?"":po.getBuildTypeDesc());
			util.setValue(i + 1, 16, po.getJoinDate()==null?"":po.getJoinDate());
			util.setValue(i + 1, 17, po.getStopDate()==null?"":po.getStopDate());
			util.setValue(i + 1, 18, po.getProjectRemark()==null?"":po.getProjectRemark());
			util.setValue(i + 1, 19, po.getInsUser()==null?"":po.getInsUser());
			util.setValue(i + 1, 20, po.getInsDate()==null?"":po.getInsDate());
			util.setValue(i + 1, 21, po.getUpdUser()==null?"":po.getUpdUser());
			util.setValue(i + 1, 22, po.getUpdDate()==null?"":po.getUpdDate());
		}
	}

	/**
	 * 导出订单
	 * 
	 * @param util
	 * @param request
	 */
	private void ExprotDviceItem(ExcelUtil util, HttpServletRequest request)  throws Exception{
		DeviceItemVO whereCause = new DeviceItemVO();
		bindObject(request, whereCause);
		List<DeviceItemVO> pageResult = staticsDbService.findDeviceItemVOStaticsData(whereCause);

		String[] header = new String[] {"项目编号", "项目名称", "订单编号", "设备分类", "设备类型", "设备标识", "设备名称", "创建人",
				"创建日期", "修改人", "修改日期" };
		util.createHeader(header);
		for (int i = 0; i < pageResult.size(); i++) {
			DeviceItemVO po = pageResult.get(i);
			util.setValue(i + 1, 0, po.getProjectNo()==null?"":po.getProjectNo());
			util.setValue(i + 1, 1, po.getProjectName()==null?"":po.getProjectName());
			util.setValue(i + 1, 2, po.getOrderNo()==null?"":po.getOrderNo());
			util.setValue(i + 1, 3, po.getDeviceTypeClassTypeDesc()==null?"":po.getDeviceTypeClassTypeDesc());
			util.setValue(i + 1, 4, po.getDeviceTypeName()==null?"":po.getDeviceTypeName());
			util.setValue(i + 1, 5, po.getDeviceItemUid()==null?"":po.getDeviceItemUid());
			util.setValue(i + 1, 6, po.getDeviceItemName()==null?"":po.getDeviceItemName());
			util.setValue(i + 1, 7, po.getInsUser()==null?"":po.getInsUser());
			util.setValue(i + 1, 8, po.getInsDate()==null?"":po.getInsDate());
			util.setValue(i + 1, 9, po.getUpdUser()==null?"":po.getUpdUser());
			util.setValue(i + 1, 10, po.getUpdDate()==null?"":po.getUpdDate());
		}
	}

	public void ajaxStaticsDeviceItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		DeviceItemVO whereCause = new DeviceItemVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		List<DeviceItemVO> pageResult = null;
		try {
			pageResult = staticsDbService.findDeviceItemVOStaticsData(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "统计项目信息异常！" + e.getMessage();
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

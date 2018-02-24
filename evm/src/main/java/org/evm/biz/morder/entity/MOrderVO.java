package org.evm.biz.morder.entity;

import org.evm.core.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.List;

import org.evm.biz.dict.BizDictFommater;
import org.evm.biz.faulttype.entity.FaultTypeVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.user.entity.UserVO;

public class MOrderVO extends AbstractEntity {
	/**
	 * 
	 * jerry.x 2016年10月24日
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 导入时的批量数据
	 */
	private String excelImportString;
	/**
	 * 设备唯一ID  add 2018/02/20
	 */
	private String deviceItemUid;
	/**
	 * 汇聚箱编号 add 2018/01/10
	 */
	private String convergeBoxNo;
	/**
	 * 订单ID
	 */
	private long orderId;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 运维订单ID
	 */
	private long morderId;
	/**
	 * 运维订单编号
	 */
	private String morderNo;
	/**
	 * 运维经理ID
	 */
	private long morderPmId;
	/**
	 * 派修时间
	 */
	private String maintainDate;
	/**
	 * 故障描述
	 */
	private String faultDesc;
	/**
	 * 预约时间
	 */
	private String appointmentDate;
	/**
	 * 计划完成时间
	 */
	private String planEndDate;
	/**
	 * 维修人数
	 */
	private String mworkerCnt;
	/**
	 * 维修开始日期
	 */
	private String maintainBeginDate;
	/**
	 * 维修状态
	 */
	private String maintainStatus;
	/**
	 * 维修进度
	 */
	private String maintainSpeed;
	/**
	 * 故障类别
	 */
	private String faultType;
	/**
	 * 维修负责人ID
	 */
	private String maintainPmId;
	/**
	 * 验收状态
	 */
	private String acceptStatus;
	/**
	 * 验收回访人
	 */
	private String acceptecallMan;
	/**
	 * 验收回访时间
	 */
	private String callbackDate;
	/**
	 * 解决方案
	 */
	private String solution;
	/**
	 * 维修结果描述
	 */
	private String maintainResultdesc;
	/**
	 * 备注
	 */
	private String maintainRemark;
	/**
	 * 用户评分
	 */
	private String userScore;
	/**
	 * 用户评价
	 */
	private String userProposal;
	/**
	 * 最后一次进度更新时间
	 */
	private String lastUpdDate;
	/**
	 * 维修图片集合
	 */
	private List<MOrderFileVO> morderFileList;
	private String fileStrs;
	// ---------------------------------
	/**
	 * 
	 */
	private OrderVO order;
	/**
	 * 维修负责人
	 * 
	 */
	private UserVO maintainPmUser;
	/**
	 * 运维经理
	 */
	private UserVO morderPmUser;

	// ------------------------------------
	/**
	 * 项目经理名称
	 */
	private String morderPmName;
	/**
	 * 维修负责人名称
	 */
	private String maintainPmName;
	/**
	 * 项目ID
	 */
	private long projectId;
	/**
	 * 项目
	 */
	private String projectNo;

	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 客户名称
	 */
	private String cusName;
	/**
	 * 项目经理名称
	 */
	private String pmName;
	/**
	 * 维修状态描述
	 */
	private String maintainStatusDesc;
	/**
	 * 故障类型描述
	 */
	private String faultTypeDesc;
	/**
	 * 验收状态描述
	 */
	private String acceptStatusDesc;
	/**
	 * 验收回访人
	 */
	private String acceptecallManName;
	/**
	 * 订单地址
	 */
	private String workAddress;
	private BigDecimal lng;
	private BigDecimal lat;
	/**
	 * App过滤条件
	 */
	private String strLike;
	/**
	 * 查询状态
	 */
	private String queryStat;
	/**
	 * App需要所有故障类型(100302 和 100312 放到这两个接口里增加【维修的故障类】)
	 */
	private List<FaultTypeVO> faultTypeVOList;
	/**
	 * 统计过滤条件：派休开始时间
	 */
	private String maintainStartDate;
	/**
	 * 统计过滤条件：派休结束时间
	 */
	private String maintainEndDate;
	/**
	 * 统计过滤条件：完成开始时间
	 */
	private String completeStartDate;
	/**
	 * 统计过滤条件：完成结束时间
	 */
	private String completeEndDate;
	/**
	 * 统计过滤条件：维修时长
	 */
	private String maintianDuration;
	/**
	 * 统计过滤条件：>=
	 */
	private String optType;
	
	
	

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getMaintianDuration() {
		return maintianDuration;
	}

	public void setMaintianDuration(String maintianDuration) {
		this.maintianDuration = maintianDuration;
	}

	public String getDeviceItemUid() {
		return deviceItemUid;
	}

	public void setDeviceItemUid(String deviceItemUid) {
		this.deviceItemUid = deviceItemUid;
	}

	public String getConvergeBoxNo() {
		return convergeBoxNo;
	}

	public void setConvergeBoxNo(String convergeBoxNo) {
		this.convergeBoxNo = convergeBoxNo;
	}

	public List<FaultTypeVO> getFaultTypeVOList() {
		return faultTypeVOList;
	}

	public void setFaultTypeVOList(List<FaultTypeVO> faultTypeVOList) {
		this.faultTypeVOList = faultTypeVOList;
	}

	public String getQueryStat() {
		return queryStat;
	}

	public void setQueryStat(String queryStat) {
		this.queryStat = queryStat;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getMorderId() {
		return morderId;
	}

	public void setMorderId(long morderId) {
		this.morderId = morderId;
	}

	public String getMaintainDate() {
		return maintainDate;
	}

	public void setMaintainDate(String maintainDate) {
		this.maintainDate = maintainDate;
	}

	public String getFaultDesc() {
		return faultDesc;
	}

	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getMworkerCnt() {
		return mworkerCnt;
	}

	public void setMworkerCnt(String mworkerCnt) {
		this.mworkerCnt = mworkerCnt;
	}

	public String getMaintainBeginDate() {
		return maintainBeginDate;
	}

	public void setMaintainBeginDate(String maintainBeginDate) {
		this.maintainBeginDate = maintainBeginDate;
	}

	public String getMaintainStatus() {
		return maintainStatus;
	}

	public void setMaintainStatus(String maintainStatus) {
		this.maintainStatus = maintainStatus;
	}

	public String getMaintainSpeed() {
		return maintainSpeed;
	}

	public void setMaintainSpeed(String maintainSpeed) {
		this.maintainSpeed = maintainSpeed;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getAcceptStatus() {
		return acceptStatus;
	}

	public void setAcceptStatus(String acceptStatus) {
		this.acceptStatus = acceptStatus;
	}

	public String getAcceptecallMan() {
		return acceptecallMan;
	}

	public void setAcceptecallMan(String acceptecallMan) {
		this.acceptecallMan = acceptecallMan;
	}

	public String getCallbackDate() {
		return callbackDate;
	}

	public void setCallbackDate(String callbackDate) {
		this.callbackDate = callbackDate;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getMaintainResultdesc() {
		return maintainResultdesc;
	}

	public void setMaintainResultdesc(String maintainResultdesc) {
		this.maintainResultdesc = maintainResultdesc;
	}

	public String getMaintainRemark() {
		return maintainRemark;
	}

	public void setMaintainRemark(String maintainRemark) {
		this.maintainRemark = maintainRemark;
	}

	public String getUserScore() {
		return userScore;
	}

	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}

	public String getUserProposal() {
		return userProposal;
	}

	public void setUserProposal(String userProposal) {
		this.userProposal = userProposal;
	}

	public long getMorderPmId() {
		return morderPmId;
	}

	public void setMorderPmId(long morderPmId) {
		this.morderPmId = morderPmId;
	}

	public String getMaintainPmId() {
		return maintainPmId;
	}

	public void setMaintainPmId(String maintainPmId) {
		this.maintainPmId = maintainPmId;
	}

	public String getMorderPmName() {
		return morderPmName;
	}

	public void setMorderPmName(String morderPmName) {
		this.morderPmName = morderPmName;
	}

	public String getMaintainPmName() {
		return maintainPmName;
	}

	public void setMaintainPmName(String maintainPmName) {
		this.maintainPmName = maintainPmName;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMorderNo() {
		return morderNo;
	}

	public void setMorderNo(String morderNo) {
		this.morderNo = morderNo;
	}

	public OrderVO getOrder() {
		return order;
	}

	public void setOrder(OrderVO order) {
		this.order = order;
	}

	public UserVO getMaintainPmUser() {
		return maintainPmUser;
	}

	public void setMaintainPmUser(UserVO maintainPmUser) {
		this.maintainPmUser = maintainPmUser;
	}

	public UserVO getMorderPmUser() {
		return morderPmUser;
	}

	public void setMorderPmUser(UserVO morderPmUser) {
		this.morderPmUser = morderPmUser;
	}

	public String getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(String lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getMaintainStatusDesc() {
		return maintainStatusDesc;
	}

	public void setMaintainStatusDesc(String maintainStatusDesc) {
		this.maintainStatusDesc = maintainStatusDesc;
	}

	public String getFaultTypeDesc() {
		return faultTypeDesc;
	}

	public void setFaultTypeDesc(String faultTypeDesc) {
		this.faultTypeDesc = faultTypeDesc;
	}

	public String getAcceptStatusDesc() {
		return acceptStatusDesc;
	}

	public void setAcceptStatusDesc(String acceptStatusDesc) {
		this.acceptStatusDesc = acceptStatusDesc;
	}

	public String getAcceptecallManName() {
		return acceptecallManName;
	}

	public void setAcceptecallManName(String acceptecallManName) {
		this.acceptecallManName = acceptecallManName;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public String getStrLike() {
		return strLike;
	}

	public void setStrLike(String strLike) {
		this.strLike = strLike;
	}

	public List<MOrderFileVO> getMorderFileList() {
		return morderFileList;
	}

	public void setMorderFileList(List<MOrderFileVO> morderFileList) {
		this.morderFileList = morderFileList;
	}

	public String getFileStrs() {
		return fileStrs;
	}

	public void setFileStrs(String fileStrs) {
		this.fileStrs = fileStrs;
	}

	public String getMaintainStartDate() {
		return maintainStartDate;
	}

	public void setMaintainStartDate(String maintainStartDate) {
		this.maintainStartDate = maintainStartDate;
	}

	public String getMaintainEndDate() {
		return maintainEndDate;
	}

	public void setMaintainEndDate(String maintainEndDate) {
		this.maintainEndDate = maintainEndDate;
	}

	public String getCompleteStartDate() {
		return completeStartDate;
	}

	public void setCompleteStartDate(String completeStartDate) {
		this.completeStartDate = completeStartDate;
	}

	public String getCompleteEndDate() {
		return completeEndDate;
	}

	public void setCompleteEndDate(String completeEndDate) {
		this.completeEndDate = completeEndDate;
	}

	public String getExcelImportString() {
		return excelImportString;
	}

	public void setExcelImportString(String excelImportString) {
		this.excelImportString = excelImportString;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.orderId != 0)
			sb.append("订单ID:" + this.orderId).append(";");
		if (this.morderId != 0)
			sb.append("运维订单ID:" + this.morderId).append(";");
		if (this.morderNo != null)
			sb.append("运维订单编号:" + this.morderNo).append(";");
		if (this.morderPmId != 0)
			sb.append("运维经理ID:" + this.morderPmId).append(";");
		if (this.maintainDate != null)
			sb.append("派修时间:" + this.maintainDate).append(";");
		if (this.faultDesc != null)
			sb.append("故障描述:" + this.faultDesc).append(";");
		if (this.appointmentDate != null)
			sb.append("预约时间:" + this.appointmentDate).append(";");
		if (this.planEndDate != null)
			sb.append("计划完成时间:" + this.planEndDate).append(";");
		if (this.mworkerCnt != null)
			sb.append("维修人数:" + this.mworkerCnt).append(";");
		if (this.maintainBeginDate != null)
			sb.append("维修开始日期:" + this.maintainBeginDate).append(";");
		if (this.maintainStatus != null)
			sb.append("维修状态:" + BizDictFommater.WorkMaintainStatusformater(this.maintainStatus)).append(";");
		if (this.maintainSpeed != null)
			sb.append("维修进度:" + this.maintainSpeed).append(";");
		if (this.faultType != null)
			sb.append("故障类别:" + this.faultType).append(";");
		if (this.maintainPmId != null)
			sb.append("维修负责人ID:" + this.maintainPmId).append(";");
		if (this.acceptStatus != null)
			sb.append("验收状态:" + BizDictFommater.WorkAcceptStatusformater(this.acceptStatus)).append(";");
		if (this.acceptecallMan != null)
			sb.append("验收回访人:" + this.acceptecallMan).append(";");
		if (this.callbackDate != null)
			sb.append(" 验收回访时间:" + this.callbackDate).append(";");
		if (this.solution != null)
			sb.append("解决方案:" + this.solution).append(";");
		if (this.maintainResultdesc != null)
			sb.append("维修结果描述:" + this.maintainResultdesc).append(";");
		if (this.maintainRemark != null)
			sb.append("备注:" + this.maintainRemark).append(";");
		if (this.userScore != null)
			sb.append("用户评分:" + this.userScore).append(";");
		if (this.userProposal != null)
			sb.append("用户评价:" + this.userProposal).append(";");
		if (this.lastUpdDate != null)
			sb.append("最后一次进度更新时间:" + this.lastUpdDate).append(";");

		// sb.append("updUser=" + super.updUser).append(";");
		// sb.append("updDate=" + super.updDate).append(";");
		// sb.append("insUser=" + super.insUser).append(";");
		// sb.append("updUser=" + super.updUser).append(";");
		return sb.toString();
	}
}

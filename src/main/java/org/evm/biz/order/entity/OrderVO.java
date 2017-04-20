package org.evm.biz.order.entity;

import java.math.BigDecimal;
import java.util.List;

import org.evm.biz.dict.BizDictFommater;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.user.entity.UserVO;
import org.evm.core.entity.AbstractEntity;

public class OrderVO extends AbstractEntity {
	/**
	 * 
	 * jerry.x 2016年10月25日
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单ID
	 */
	private long orderId;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 项目ID
	 */
	private String projectId;
	/**
	 * 施工负责人ID
	 */
	private String workPmId;
	/**
	 * 施工人数
	 */
	private String workCnt;
	/**
	 * 施工地点
	 */
	private String workAddress;
	private BigDecimal lng;
	private BigDecimal lat;
	/**
	 * 施工单位
	 */
	private String workCompany;
	/**
	 * 单位资质情况
	 */
	private String workCompanyQualified;
	/**
	 * 施工类型
	 */
	private String workType;
	/**
	 * 施工天数
	 */
	private String workDays;
	/**
	 * 计划施工时间
	 */
	private String planBeginDate;
	/**
	 * 计划完工时间
	 */
	private String planEndDate;
	/**
	 * 出库联系人
	 */
	private String storePmId;
	/**
	 * 计划出库时间
	 */
	private String planOutstoreDate;
	/**
	 * 施工状态
	 */
	private String workStatus;
	/**
	 * 实际施工时间
	 */
	private String workBeginDate;
	/**
	 * 实际施工完成时间
	 */
	private String workEndDate;
	/**
	 * 施工进度
	 */
	private String workProgress;
	/**
	 * 最后一次施工更新时间
	 */
	private String lastWorkupdDate;
	/**
	 * 内检人员
	 */
	private String checkerId;
	/**
	 * 施工内检结果
	 */
	private String checkResult;
	/**
	 * 内检时间
	 */
	private String checkDate;
	/**
	 * 用户评分
	 */
	private String userRating;
	/**
	 * 用户评价
	 */
	private String userAppraise;
	/**
	 * 备注
	 */
	private String workRemark;
	/**
	 * 项目
	 */
	private ProjectVO project;
	/**
	 * 施工负责人
	 */

	private UserVO workPm;
	/**
	 * k库管员
	 */
	private UserVO storePm;
	/**
	 * 内检人员
	 */
	private UserVO checkePm;

	private String deviceStr;
	/**
	 * 设备信息
	 */
	List<OrderDeviceVO> deviceList;
	/**
	 * 文件信息
	 */
	List<OrderFileVO> filesList;
	private String fileStrs;
	// --------------------------------------------
	private String projectName;
	private String projectNo;
	private String cusId;
	private String cusName;
	private String pmId;
	private String pmName;
	private String workPmName;
	private String workTypeDesc;
	private String checkerName;
	private String storePmName;
	private String workStatusDesc;
	private String checkResultDesc;
	// --------------------------------------------
	/**
	 * 批量创建订单 起始订单号
	 */
	private String batchOrderPre;
	/**
	 * 批量创建的个数
	 */
	private int batchOrderCnt;
	/**
	 * 该订单对应的维修订单数量
	 */
	private int morderCnt;

	/**
	 * 首页过滤设备唯一编号问题
	 */
	private String deviceItemPara;
	/**
	 * App过滤条件
	 */
	private String strLike;

	/**
	 * 查询状态
	 */
	private String queryStat;
	/**
	 * 停止服务的天数
	 */
	private int stopServiceDays;
	/**
	 * App需要的订单类型、工程订单或维修订单
	 */
	private String orderType;
	/**
	 * 
	 */
	private String completeImgContent;

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getQueryStat() {
		return queryStat;
	}

	public void setQueryStat(String queryStat) {
		this.queryStat = queryStat;
	}

	public int getStopServiceDays() {
		return stopServiceDays;
	}

	public void setStopServiceDays(int stopServiceDays) {
		this.stopServiceDays = stopServiceDays;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getWorkPmId() {
		return workPmId;
	}

	public void setWorkPmId(String workPmId) {
		this.workPmId = workPmId;
	}

	public String getWorkCnt() {
		return workCnt;
	}

	public void setWorkCnt(String workCnt) {
		this.workCnt = workCnt;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	public String getWorkCompanyQualified() {
		return workCompanyQualified;
	}

	public void setWorkCompanyQualified(String workCompanyQualified) {
		this.workCompanyQualified = workCompanyQualified;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkDays() {
		return workDays;
	}

	public void setWorkDays(String workDays) {
		this.workDays = workDays;
	}

	public String getPlanBeginDate() {
		return planBeginDate;
	}

	public void setPlanBeginDate(String planBeginDate) {
		this.planBeginDate = planBeginDate;
	}

	public String getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getStorePmId() {
		return storePmId;
	}

	public void setStorePmId(String storePmId) {
		this.storePmId = storePmId;
	}

	public String getPlanOutstoreDate() {
		return planOutstoreDate;
	}

	public void setPlanOutstoreDate(String planOutstoreDate) {
		this.planOutstoreDate = planOutstoreDate;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getWorkBeginDate() {
		return workBeginDate;
	}

	public void setWorkBeginDate(String workBeginDate) {
		this.workBeginDate = workBeginDate;
	}

	public String getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}

	public String getWorkProgress() {
		return workProgress;
	}

	public void setWorkProgress(String workProgress) {
		this.workProgress = workProgress;
	}

	public String getLastWorkupdDate() {
		return lastWorkupdDate;
	}

	public void setLastWorkupdDate(String lastWorkupdDate) {
		this.lastWorkupdDate = lastWorkupdDate;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getUserRating() {
		return userRating;
	}

	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}

	public String getUserAppraise() {
		return userAppraise;
	}

	public void setUserAppraise(String userAppraise) {
		this.userAppraise = userAppraise;
	}

	public String getWorkRemark() {
		return workRemark;
	}

	public void setWorkRemark(String workRemark) {
		this.workRemark = workRemark;
	}

	public ProjectVO getProject() {
		return project;
	}

	public void setProject(ProjectVO project) {
		this.project = project;
	}

	public UserVO getWorkPm() {
		return workPm;
	}

	public void setWorkPm(UserVO workPm) {
		this.workPm = workPm;
	}

	public UserVO getStorePm() {
		return storePm;
	}

	public void setStorePm(UserVO storePm) {
		this.storePm = storePm;
	}

	public UserVO getCheckePm() {
		return checkePm;
	}

	public void setCheckePm(UserVO checkePm) {
		this.checkePm = checkePm;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getWorkPmName() {
		return workPmName;
	}

	public void setWorkPmName(String workPmName) {
		this.workPmName = workPmName;
	}

	public String getDeviceStr() {
		return deviceStr;
	}

	public void setDeviceStr(String deviceStr) {
		this.deviceStr = deviceStr;
	}

	public List<OrderDeviceVO> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<OrderDeviceVO> deviceList) {
		this.deviceList = deviceList;
	}

	public List<OrderFileVO> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<OrderFileVO> filesList) {
		this.filesList = filesList;
	}

	public String getFileStrs() {
		return fileStrs;
	}

	public void setFileStrs(String fileStrs) {
		this.fileStrs = fileStrs;
	}

	public String getBatchOrderPre() {
		return batchOrderPre;
	}

	public void setBatchOrderPre(String batchOrderPre) {
		this.batchOrderPre = batchOrderPre;
	}

	public int getBatchOrderCnt() {
		return batchOrderCnt;
	}

	public void setBatchOrderCnt(int batchOrderCnt) {
		this.batchOrderCnt = batchOrderCnt;
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

	public int getMorderCnt() {
		return morderCnt;
	}

	public void setMorderCnt(int morderCnt) {
		this.morderCnt = morderCnt;
	}

	public String getWorkTypeDesc() {
		return workTypeDesc;
	}

	public void setWorkTypeDesc(String workTypeDesc) {
		this.workTypeDesc = workTypeDesc;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getStorePmName() {
		return storePmName;
	}

	public void setStorePmName(String storePmName) {
		this.storePmName = storePmName;
	}

	public String getWorkStatusDesc() {
		return workStatusDesc;
	}

	public void setWorkStatusDesc(String workStatusDesc) {
		this.workStatusDesc = workStatusDesc;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.orderId != 0)
			sb.append("订单ID:" + this.orderId).append(";");
		if (this.orderNo != null)
			sb.append("订单编号:" + this.orderNo).append(";");
		if (this.projectId != null)
			sb.append("项目ID:" + this.projectId).append(";");
		if (this.workPmId != null)
			sb.append("施工负责人ID:" + this.workPmId).append(";");
		if (this.workCnt != null)
			sb.append("施工人数:" + this.workCnt).append(";");
		if (this.workAddress != null)
			sb.append("施工地点:" + this.workAddress).append(";");
		if (this.lng != null)
			sb.append("经度:" + this.lng).append(";");
		if (this.lat != null)
			sb.append("纬度:" + this.lat).append(";");
		if (this.workCompany != null)
			sb.append("施工单位:" + this.workCompany).append(";");
		if (this.workCompanyQualified != null)
			sb.append("单位资质情况:" + this.workCompanyQualified).append(";");
		if (this.workType != null)
			sb.append("施工类型:" + BizDictFommater.WorkTypeformater(this.workType)).append(";");
		if (this.workDays != null)
			sb.append("施工天数:" + this.workDays).append(";");
		if (this.planBeginDate != null)
			sb.append("计划施工时间:" + this.planBeginDate).append(";");
		if (this.planEndDate != null)
			sb.append("计划完工时间:" + this.planEndDate).append(";");
		if (this.storePmId != null)
			sb.append("出库联系人ID:" + this.storePmId).append(";");
		if (this.storePmId != null)
			sb.append("计划出库时间:" + this.planOutstoreDate).append(";");
		if (this.workStatus != null)
			sb.append("施工状态:" + BizDictFommater.WorkStatusformater(this.workStatus)).append(";");
		if (this.workBeginDate != null)
			sb.append("实际施工时间:" + this.workBeginDate).append(";");
		if (this.workEndDate != null)
			sb.append("实际施工完成时间:" + this.workEndDate).append(";");
		if (this.workProgress != null)
			sb.append("施工进度:" + this.workProgress).append(";");
		if (this.lastWorkupdDate != null)
			sb.append("最后一次施工更新时间:" + this.lastWorkupdDate).append(";");
		if (this.checkerId != null)
			sb.append("内检人员ID:" + this.checkerId).append(";");
		if (this.checkResult != null)
			sb.append("施工内检结果:" + BizDictFommater.WorkCheckResultformater(this.checkResult)).append(";");
		if (this.checkDate != null)
			sb.append("内检时间:" + this.checkDate).append(";");
		if (this.userRating != null)
			sb.append("用户评分:" + this.userRating).append(";");
		if (this.userAppraise != null)
			sb.append("用户评价:" + this.userAppraise).append(";");
		if (this.workRemark != null)
			sb.append("备注:" + this.workRemark).append(";");
		// sb.append("updUser:" + super.updUser).append(";");
		// sb.append("updDate:" + super.updDate).append(";");
		// sb.append("insUser:" + super.insUser).append(";");
		// sb.append("insDate:" + super.updDate).append(";");

		return sb.toString();
	}

	public String getDeviceItemPara() {
		return deviceItemPara;
	}

	public void setDeviceItemPara(String deviceItemPara) {
		this.deviceItemPara = deviceItemPara;
	}

	public String getCheckResultDesc() {
		return checkResultDesc;
	}

	public void setCheckResultDesc(String checkResultDesc) {
		this.checkResultDesc = checkResultDesc;
	}

	public String getStrLike() {
		return strLike;
	}

	public void setStrLike(String strLike) {
		this.strLike = strLike;
	}

}

package org.evm.biz.project.entity;

import java.math.BigDecimal;
import java.util.List;

import org.evm.core.entity.AbstractEntity;
import org.evm.biz.custom.entity.CustomVO;
import org.evm.biz.dict.BizDictFommater;
import org.evm.biz.user.entity.UserVO;

public class ProjectVO extends AbstractEntity {
	/**
	 * 项目ID
	 */
	private long projectId;
	/**
	 * 项目号
	 */
	private String projectNo;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 客户ID
	 */
	private long cusId;
	/**
	 * 客户经理ID
	 */
	private long cmId;
	/**
	 * 项目经理ID
	 */
	private long pmId;
	/**
	 * 项目分类
	 */
	private String projectType;
	/**
	 * 合同编号
	 */
	private String contractId;
	/**
	 * 签订日期
	 */
	private String signDate;
	/**
	 * 合同金额
	 */
	private BigDecimal contractSum;

	/**
	 * 缴费类型
	 */
	private String payType;
	/**
	 * 缴费金额
	 */
	private String paySum;
	/**
	 * 工期截止日期
	 */
	private String workEndDate;
	/**
	 * 开工日期
	 */
	private String workStartDate;
	/**
	 * 年服务费
	 */
	private String yearPay;
	/**
	 * 服务截止日期
	 */
	private String serviceEndDate;
	/**
	 * 缴费日期
	 */
	private String payDate;
	/**
	 * 建设方式
	 */
	private String buildType;
	/**
	 * 入网日期
	 */
	private String joinDate;
	/**
	 * 停机日期
	 */
	private String stopDate;
	/**
	 * 备注
	 */
	private String projectRemark;

	/**
	 * 设备管理
	 */
	private List<RelProjectDeviceVO> deviceList;
	/**
	 * 设备数据串
	 */
	private String deviceStr;

	/**
	 * 客户类型
	 */
	private String cusType;
	/**
	 * 客户类型描述
	 */
	private String cusTypeDesc;
	private String cusName;
	private String cusSex;
	private String cusTel;

	private String pmName;
	private String pmTel;
	private String pmSex;
	private String cmName;
	private String cmTel;
	private String cmSex;

	private CustomVO custom;

	private UserVO pmUser;

	private UserVO cmUser;
	/**
	 * 建设方式
	 */
	private String buildTypeDesc;
	/**
	 * 项目分类
	 */
	private String projectTypeDesc;
	/**
	 * 过滤条件名
	 */
	private String condName;
	/**
	 * 过滤条件值
	 */
	private String condValue;

	/**
	 * 查询状态
	 */
	private String queryStat;
	/**
	 * 工期截止的天数
	 */
	private int stopServiceDays;
	/**
	 * 关联用户
	 */

	private String relUserStr;
	/**
	 * 项目状态：（维护期内，维护期外，终止）
	 */
	private String proStat;

	private List<RelProjectUserVO> relUserList;
	/**
	 * 查询条件：开始时间
	 */
	private String beginDate;
	/**
	 * 查询条件：结束时间
	 */
	private String endDate;

	public String getProStat() {
		return proStat;
	}

	public void setProStat(String proStat) {
		this.proStat = proStat;
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

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public long getCusId() {
		return cusId;
	}

	public void setCusId(long cusId) {
		this.cusId = cusId;
	}

	public long getCmId() {
		return cmId;
	}

	public void setCmId(long cmId) {
		this.cmId = cmId;
	}

	public long getPmId() {
		return pmId;
	}

	public void setPmId(long pmId) {
		this.pmId = pmId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}

	public String getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(String workStartDate) {
		this.workStartDate = workStartDate;
	}

	public String getYearPay() {
		return yearPay;
	}

	public void setYearPay(String yearPay) {
		this.yearPay = yearPay;
	}

	public String getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getStopDate() {
		return stopDate;
	}

	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}

	public String getProjectRemark() {
		return projectRemark;
	}

	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}

	public List<RelProjectDeviceVO> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<RelProjectDeviceVO> deviceList) {
		this.deviceList = deviceList;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getCusTypeDesc() {
		return cusTypeDesc;
	}

	public void setCusTypeDesc(String cusTypeDesc) {
		this.cusTypeDesc = cusTypeDesc;
	}

	public CustomVO getCustom() {
		return custom;
	}

	public void setCustom(CustomVO custom) {
		this.custom = custom;
	}

	public UserVO getPmUser() {
		return pmUser;
	}

	public void setPmUser(UserVO pmUser) {
		this.pmUser = pmUser;
	}

	public UserVO getCmUser() {
		return cmUser;
	}

	public void setCmUser(UserVO cmUser) {
		this.cmUser = cmUser;
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

	public String getPmTel() {
		return pmTel;
	}

	public void setPmTel(String pmTel) {
		this.pmTel = pmTel;
	}

	public String getPmSex() {
		return pmSex;
	}

	public void setPmSex(String pmSex) {
		this.pmSex = pmSex;
	}

	public String getCmName() {
		return cmName;
	}

	public void setCmName(String cmName) {
		this.cmName = cmName;
	}

	public String getCmTel() {
		return cmTel;
	}

	public void setCmTel(String cmTel) {
		this.cmTel = cmTel;
	}

	public String getCmSex() {
		return cmSex;
	}

	public void setCmSex(String cmSex) {
		this.cmSex = cmSex;
	}

	public String getPaySum() {
		return paySum;
	}

	public void setPaySum(String paySum) {
		this.paySum = paySum;
	}

	public String getCusSex() {
		return cusSex;
	}

	public void setCusSex(String cusSex) {
		this.cusSex = cusSex;
	}

	public String getCusTel() {
		return cusTel;
	}

	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}

	public BigDecimal getContractSum() {
		return contractSum;
	}

	public void setContractSum(BigDecimal contractSum) {
		this.contractSum = contractSum;
	}

	public String getDeviceStr() {
		return deviceStr;
	}

	public void setDeviceStr(String deviceStr) {
		this.deviceStr = deviceStr;
	}

	public String getBuildTypeDesc() {
		return buildTypeDesc;
	}

	public void setBuildTypeDesc(String buildTypeDesc) {
		this.buildTypeDesc = buildTypeDesc;
	}

	public String getProjectTypeDesc() {
		return projectTypeDesc;
	}

	public void setProjectTypeDesc(String projectTypeDesc) {
		this.projectTypeDesc = projectTypeDesc;
	}

	public String getCondName() {
		return condName;
	}

	public void setCondName(String condName) {
		this.condName = condName;
	}

	public String getCondValue() {
		return condValue;
	}

	public void setCondValue(String condValue) {
		this.condValue = condValue;
	}

	public String getRelUserStr() {
		return relUserStr;
	}

	public void setRelUserStr(String relUserStr) {
		this.relUserStr = relUserStr;
	}

	public List<RelProjectUserVO> getRelUserList() {
		return relUserList;
	}

	public void setRelUserList(List<RelProjectUserVO> relUserList) {
		this.relUserList = relUserList;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.projectId != 0)
			sb.append("项目ID:" + this.projectId).append(";");
		if (this.projectNo != null)
			sb.append("项目编号:" + this.projectNo).append(";");
		if (this.projectName != null)
			sb.append("项目名称:" + this.projectName).append(";");
		if (this.cusId != 0)
			sb.append("客户ID:" + this.cusId).append(";");
		if (this.cmId != 0)
			sb.append("客户经理ID:" + this.cmId).append(";");
		if (this.pmId != 0)
			sb.append("项目经理ID:" + this.pmId).append(";");
		if (this.projectType != null)
			sb.append("项目分类:" + BizDictFommater.ProjectTypeformater(this.projectType)).append(";");
		if (this.contractId != null)
			sb.append("合同编号:" + this.contractId).append(";");
		if (this.signDate != null)
			sb.append("签订日期:" + this.signDate).append(";");
		if (this.contractSum != null)
			sb.append("合同金额:" + this.contractSum).append(";");
		if (this.payType != null)
			sb.append("缴费类型:" + this.payType).append(";");
		if (this.paySum != null)
			sb.append("缴费金额:" + this.paySum).append(";");
		if (this.workEndDate != null)
			sb.append("工期截止日期:" + this.workEndDate).append(";");
		if (this.workStartDate != null)
			sb.append("开工日期:" + this.workStartDate).append(";");
		if (this.yearPay != null)
			sb.append("年服务费:" + this.yearPay).append(";");
		if (this.serviceEndDate != null)
			sb.append("服务截止日期:" + this.serviceEndDate).append(";");
		if (this.payDate != null)
			sb.append("缴费日期:" + this.payDate).append(";");
		if (this.buildType != null)
			sb.append("建设方式:" + BizDictFommater.BuildTypeformater(this.buildType)).append(";");
		if (this.joinDate != null)
			sb.append("入网日期:" + this.joinDate).append(";");
		if (this.stopDate != null)
			sb.append("停机日期:" + this.stopDate).append(";");
		if (this.projectRemark != null)
			sb.append("备注:" + this.projectRemark).append(";");

		// sb.append("updUser:" + super.updUser).append(";");
		// sb.append("updDate:" + super.updDate).append(";");
		// sb.append("insUser:" + super.insUser).append(";");
		// sb.append("insDate:" + super.updDate).append(";");

		return sb.toString();
	}
}

package org.evm.biz.log.entity;

import org.evm.core.entity.AbstractEntity;

public class LogVO extends AbstractEntity {
	/**
	 * 日志ID
	 */
	private String logId;
	/**
	 * 内容
	 */
	private String logContent;
	/**
	 * 输入参数
	 */
	private String logPara;
	/**
	 * 业务id
	 */
	private long logBizId;
	/**
	 * 业务类型ID
	 */
	private String logBizType;
	/**
	 * 操作用户名
	 */
	private String logUserName;
	/**
	 * 过滤条件
	 */
	private String endDate;
	private String beginDate;

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getLogPara() {
		return logPara;
	}

	public void setLogPara(String logPara) {
		this.logPara = logPara;
	}

	public long getLogBizId() {
		return logBizId;
	}

	public void setLogBizId(long logBizId) {
		this.logBizId = logBizId;
	}

	public String getLogBizType() {
		return logBizType;
	}

	public void setLogBizType(String logBizType) {
		this.logBizType = logBizType;
	}

	public String getLogUserName() {
		return logUserName;
	}

	public void setLogUserName(String logUserName) {
		this.logUserName = logUserName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("logId=" + this.logId).append(";");
		sb.append("logContent=" + this.logContent).append(";");
		sb.append("logPara=" + this.logPara).append(";");
		sb.append("logBizId=" + this.logBizId).append(";");
		sb.append("logBizType=" + this.logBizType).append(";");

		sb.append("logUserName=" + this.logUserName).append(";");

		return sb.toString();
	}
}

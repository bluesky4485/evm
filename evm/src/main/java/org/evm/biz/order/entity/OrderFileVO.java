package org.evm.biz.order.entity;

import org.evm.core.entity.AbstractEntity;

public class OrderFileVO extends AbstractEntity {
	/**
	 * 文件ID
	 */
	private Long fileId;
	/**
	 * 订单ID
	 */
	private long orderId;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 文件大小
	 */
	private Long fileSize;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 业务类型（施工图片1，2竣工图片）
	 */
	private String bizType;
	/**
	 * 文件内容
	 */
	private String fileContent;
	
	/**
	 * 订单编号 (创建文件夹用)
	 */
	private String orderNo;
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		StringBuffer sb = new StringBuffer();
//		sb.append("fileId=" + this.fileId).append(";");
//		sb.append("filePath=" + this.filePath).append(";");
//		sb.append("fileSize=" + this.fileSize).append(";");
//		sb.append("fileType=" + this.fileType).append(";");
//		sb.append("bizType=" + this.bizType).append(";");
//		return sb.toString();
//	}
}

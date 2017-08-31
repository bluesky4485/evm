package org.evm.biz.morder.entity;

import org.evm.core.entity.AbstractEntity;

public class MOrderFileVO extends AbstractEntity {
	/**
	 * 文件ID
	 */
	private Long fileId;
	/**
	 * 运维订单ID
	 */
	private long morderId;
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
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public long getMorderId() {
		return morderId;
	}
	public void setMorderId(long morderId) {
		this.morderId = morderId;
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
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	
}

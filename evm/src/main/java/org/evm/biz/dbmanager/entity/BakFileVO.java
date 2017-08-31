package org.evm.biz.dbmanager.entity;

/**
 * 数据备份文件
 * 
 * @author jerry.x
 *
 */
public class BakFileVO {
	private String fileName;
	private String fileSize;
	private String modefiedTime;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getModefiedTime() {
		return modefiedTime;
	}

	public void setModefiedTime(String modefiedTime) {
		this.modefiedTime = modefiedTime;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		sb.append("文件名：").append(this.fileName);
		sb.append("文件大小：").append(this.fileSize);
		sb.append("修改时间：").append(this.modefiedTime);
		return sb.toString();
	}
}

package org.evm.core.entity;

import java.util.List;

/**
 * 客户端返回模型
 * 
 * @author xuh 下午04:07:00 2014-6-19
 * 
 */
public class PageResult {
	
	//当前页号
	private int pageNum;
	//每页数量
	private int pageSize;
	//数据总数
	private int total;
	//数据
	private List rows;
	//消息数据
	private String messageData;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public String getMessageData() {
		return messageData;
	}
	public void setMessageData(String messageData) {
		this.messageData = messageData;
	}
	
}

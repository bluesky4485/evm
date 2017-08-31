package org.evm.biz.webService.entity;

public class ResponseResult {
	private int pageSize;
	private int pageNum;
	private Object dataResult;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Object getDataResult() {
		return dataResult;
	}

	public void setDataResult(Object dataResult) {
		this.dataResult = dataResult;
	}

}

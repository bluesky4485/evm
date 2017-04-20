package org.evm.biz.faulttype.service;

import java.util.List;

import org.evm.biz.faulttype.entity.FaultTypeVO;

public interface IFaultTypeDbService {
	final String MS_FIND__ALL_FAULT= "MS_FIND__ALL_FAULT";
	final String MS_DELETE_FAULTTYPE="MS_DELETE_FAULTTYPE";
	final String MS_UPDATE_FAULTTYPE="MS_UPDATE_FAULTTYPE";
	final String MS_INSERT_FAULTTYPE="MS_INSERT_FAULTTYPE";
	List<FaultTypeVO> findAllFaultType(FaultTypeVO whereCause);
	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int insertFaultType(FaultTypeVO whereCause);
	
	int deleteFaultType(FaultTypeVO para);
	int updateFaultType(FaultTypeVO para);
}

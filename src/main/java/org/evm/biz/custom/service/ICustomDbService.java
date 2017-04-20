package org.evm.biz.custom.service;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.biz.custom.entity.CustomVO;

public interface ICustomDbService {
	final String MS_FIND_CUSTOM_ALL_CNT = "MS_FIND_CUSTOM_ALL_CNT";
	final String MS_FIND_CUSTOM_PAGE = "MS_FIND_CUSTOM_PAGE";

	final String MS_UPDATE_CUATOM = "MS_UPDATE_CUATOM";
	final String MS_INSERT_CUSTOM = "MS_INSERT_CUSTOM";
	final String MS_DELETE_CUSTOM = "MS_DELETE_CUSTOM";
	final String MS_FIND_CUSTOM_BYID="MS_FIND_CUSTOM_BYID";

	 

	/**
	 * 分页查询 update by jerry.x 2016年10月17日
	 * 
	 * @param para
	 * @return
	 *
	 */
	PageResult findAllCustomPage(CustomVO para);

	/**
	 * 
	 * update by jerry.x 2016年10月10日
	 * 
	 * @param para
	 *
	 */
	int deleteCustom(CustomVO para);

	/**
	 * 
	 * update by jerry.x 2016年10月10日
	 * 
	 * @param para
	 *
	 */
	int updateCustom(CustomVO para);

	/**
	 * 
	 * update by jerry.x 2016年10月10日
	 * 
	 * @param para
	 *
	 */
	int insertCustom(CustomVO para);
	/**
	 * 
	 * update by jerry.x 2016年10月26日 
	*@param para
	*@return 
	 *
	 */
	CustomVO getCustomerById(long para);
}

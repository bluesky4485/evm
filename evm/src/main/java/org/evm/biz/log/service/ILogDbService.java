package org.evm.biz.log.service;

import org.evm.core.entity.PageResult;
import org.evm.biz.log.entity.LogVO;

/**
 * 
 * @author jerry.x update 2016年10月14日 上午9:40:42
 */
public interface ILogDbService {
	final String MS_INSERT_DBLOG = "MS_INSERT_DBLOG";
	final String MS_FIND_DBLOG_PAGE = "MS_FIND_DBLOG_PAGE";
	final String MS_FIND_DBLOG_PAGE_CNT = "MS_FIND_DBLOG_PAGE_CNT";
	/**
	 * 追加日志 update by jerry.x 2016年10月14日
	 * 
	 * @param para
	 *
	 */
	void insertLog(LogVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月14日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findLogPage(LogVO whereCause);
}

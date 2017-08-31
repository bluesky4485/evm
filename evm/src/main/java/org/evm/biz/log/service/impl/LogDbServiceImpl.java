package org.evm.biz.log.service.impl;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.log.entity.LogVO;
import org.evm.biz.log.service.ILogDbService;

/**
 * 
 * @author jerry.x update 2016年10月14日 上午9:40:33
 */
public class LogDbServiceImpl extends BaseDBService implements ILogDbService {

	@Override
	public void insertLog(LogVO whereCause) {
		// TODO Auto-generated method stub
		try {
			super.insert(MS_INSERT_DBLOG, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("插入日志信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("插入日志信息异常");
		}
	}

	@Override
	@SystemServiceLog(description = "查询日志信息", bizTypeId = BizOptType.query, functionId = FunctionMap.MIS_QUERY)
	public PageResult findLogPage(LogVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		pr.setTotal(super.getCount(MS_FIND_DBLOG_PAGE_CNT, whereCause));
		List<LogVO> rows = super.find(MS_FIND_DBLOG_PAGE, whereCause);
		pr.setRows(rows);
		return pr;
	}

}

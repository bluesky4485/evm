package org.evm.biz.custom.service.impl;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;
import org.evm.biz.custom.entity.CustomVO;
import org.evm.biz.custom.service.ICustomDbService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;

public class CustomDbServiceImpl extends BaseDBService implements ICustomDbService {
	@Override
	@SystemServiceLog(description = "删除客户信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.WEB_DELETE)
	public int deleteCustom(CustomVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = super.update(MS_DELETE_CUSTOM, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("删除客户信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("删除客户信息异常", e);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "更新客户信息", bizTypeId = BizOptType.update, functionId = FunctionMap.WEB_UPDATE)
	public int updateCustom(CustomVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = super.update(MS_UPDATE_CUATOM, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("更新客户信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("更新客户信息异常", e);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "创建客户信息", bizTypeId = BizOptType.add, functionId = FunctionMap.WEB_ADD)
	public int insertCustom(CustomVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = (Integer) super.insert(MS_INSERT_CUSTOM, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("插入客户信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("插入客户信息异常", e);
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "查询客户信息", bizTypeId = BizOptType.query, functionId = FunctionMap.WEB_QUERY)
	public PageResult findAllCustomPage(CustomVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<CustomVO> rows = null;
		int total = super.getCount(MS_FIND_CUSTOM_ALL_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_FIND_CUSTOM_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	public CustomVO getCustomerById(long para) {
		// TODO Auto-generated method stub
		return (CustomVO) super.get(MS_FIND_CUSTOM_BYID, para);
	}

}

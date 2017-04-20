package org.evm.biz.faulttype.service.impl;

import java.util.List;

import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;
import org.evm.biz.faulttype.entity.FaultTypeVO;
import org.evm.biz.faulttype.service.IFaultTypeDbService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;

public class FaultTypeDbServiceImpl extends BaseDBService implements IFaultTypeDbService {
	@SystemServiceLog(description = "查询故障类型信息", bizTypeId = BizOptType.query, functionId = FunctionMap.WEB_QUERY)
	public List<FaultTypeVO> findAllFaultType(FaultTypeVO whereCause) {
		// TODO Auto-generated method stub
		try {
			return super.find(MS_FIND__ALL_FAULT, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询故障信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("查询故障信息异常", e);
		}
	}

	@Override
	@SystemServiceLog(description = "创建故障类型信息", bizTypeId = BizOptType.add, functionId = FunctionMap.WEB_ADD)
	public int insertFaultType(FaultTypeVO whereCause) {
		int res = 0;
		try {
			res = (Integer) super.insert(MS_INSERT_FAULTTYPE, whereCause);
			// super.batchInsert(MS_DELETE_DEVICE_PROPERTY,
			// whereCause.getProperties());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("插入设备信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("插入设备信息异常", e);
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "删除故障类型信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.WEB_DELETE)
	public int deleteFaultType(FaultTypeVO whereCause) {
		int res = 0;
		try {
			res = super.delete(MS_DELETE_FAULTTYPE, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("删除故障信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("删除故障信息异常", e);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧");
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "更新故障类型信息", bizTypeId = BizOptType.update, functionId = FunctionMap.WEB_UPDATE)
	public int updateFaultType(FaultTypeVO para) {
		int res = 0;
		try {
			res = super.update(MS_UPDATE_FAULTTYPE, para);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("更新故障信息异常!参数：" + para.toString(), e);
			throw new SmartDBAccessException("更新故障信息异常", e);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧");
		}
		return res;
	}
}

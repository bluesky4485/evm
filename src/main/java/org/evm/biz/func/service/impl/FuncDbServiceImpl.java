package org.evm.biz.func.service.impl;

import java.util.List;

import org.evm.biz.func.entity.AppServiceVO;
import org.evm.biz.func.entity.FuncVO;
import org.evm.biz.func.service.IFuncDbService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.core.entity.PageResult;
import org.evm.core.service.BaseDBService;

public class FuncDbServiceImpl extends BaseDBService implements IFuncDbService {

	@Override
	@SystemServiceLog(description = "查询权限信息", bizTypeId = BizOptType.query, functionId = FunctionMap.MIS_QUERY)
	public PageResult findFuncPage(FuncVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<FuncVO> rows = null;
		int total = super.getCount(MS_SQL_FIND_FUNC_PAGE_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_SQL_FIND_FUNC_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	public List<FuncVO> getConnectFuncByUName(String systemID, String uname) {
		// TODO Auto-generated method stub
		FuncVO whereCause = new FuncVO();
		whereCause.setuName(uname);
		if (systemID.equals("100")) {
			// 用户APP
			whereCause.setFuncId("30001");
		} else if (systemID.equals("200")) {
			// 工程APP
			whereCause.setFuncId("40001");
		} else if (systemID.equals("300")) {
			// 维修APP
			whereCause.setFuncId("50001");
		} else if (systemID.equals("400")) {
			// WEB系统
			whereCause.setFuncId("10001");
		} else if (systemID.equals("500")) {
			// MIS系统
			whereCause.setFuncId("20001");
		}
		List<FuncVO> list = super.find(MS_SQL_FIND_FUNC_BY_UNAME, whereCause);
		return list;
	}

	@Override
	public List<FuncVO> getFuncByUidForApp(String serviceId, String uid) {
		// TODO Auto-generated method stub
		FuncVO whereCause = new FuncVO();
		whereCause.setuId(uid);
		whereCause.setServiceId(serviceId);
		return super.find(MS_SQL_FIND_FUNC_BY_UID_APP, whereCause);
	}

	@Override
	public List<AppServiceVO> getServiceFuncVO(AppServiceVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_SQL_FIND_SERVICE_FUNCID, whereCause);
	}

}

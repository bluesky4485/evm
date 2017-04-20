package org.evm.biz.phoneapp.service.impl;

import java.util.List;

import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.phoneapp.entity.AppDataStVO;
import org.evm.biz.phoneapp.service.IAppDbService;
import org.evm.core.service.BaseDBService;

public class AppDbServiceImpl extends BaseDBService implements IAppDbService {
 

	@Override
	public AppDataStVO getAppDataStVO(long uid) {
		// TODO Auto-generated method stub
		AppDataStVO appDataStVO = new AppDataStVO();
		appDataStVO.setWorkallnum(super.getCount("getWorkallnum", uid));
		appDataStVO.setWorkingnum(super.getCount("getWorkingnum", uid));
		appDataStVO.setRepairallnum(super.getCount("getRepairallnum", uid));
		appDataStVO.setRepairingnum(super.getCount("getRepairingnum", uid));
		return appDataStVO;
	}

	@Override
	public List<OrderVO> findAppOrderByProjectUser(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_SQL_APP_FIND_ORDER_MORDER_BY_UID, whereCause);
	}

	@Override
	public List<OrderVO> findAppOrder(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return  super.find(MS_SQL_APP_FIND_ORDER_LOGIN, whereCause);
	}

	@Override
	public List<OrderVO> findAppMOrder(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return  super.find(MS_SQL_APP_FIND_MORDER_LOGIN, whereCause);
	}

	@Override
	public MOrderVO getLastMorder(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return (MOrderVO) super.find(MS_SQL_APP_FIND_MORDER_100312, whereCause);
	}



}

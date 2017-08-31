package org.evm.biz.statics.service.impl;

import java.util.List;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.statics.service.IStaticsDbService;
import org.evm.core.service.BaseDBService;

public class StaticsDbServiceImpl extends BaseDBService implements IStaticsDbService {

	@Override
	public List<ProjectVO> findProjectStaticsData(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return super.find("MS_FIND_STATICS_PROJECT", whereCause);
	}

	@Override
	public List<MOrderVO> findMorderStaticsData(MOrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find("MS_FIND_STATICS_MORDER", whereCause);
	}

	@Override
	public List<OrderVO> findOrderStaticsData(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find("MS_SQL_FIND_STATICS_ORDER", whereCause);
	}

	@Override
	public List<DeviceItemVO> findDeviceItemVOStaticsData(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		return super.find("MS_SQL_FIND_STATICS_DEVICEITEM", whereCause);
	}

}

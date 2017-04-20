package org.evm.biz.statics.service;

import java.util.List;

import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.project.entity.ProjectVO;

public interface IStaticsDbService {
	/**
	 * 项目统计
	 * 
	 * @param whereCause
	 * @return
	 */
	List<ProjectVO> findProjectStaticsData(ProjectVO whereCause);

	/**
	 * 
	 * @param whereCause
	 * @return
	 */
	List<MOrderVO> findMorderStaticsData(MOrderVO whereCause);

	/**
	 * 
	 * @param whereCause
	 * @return
	 */
	List<DeviceItemVO> findDeviceItemVOStaticsData(DeviceItemVO whereCause);

	/**
	 * 订单统计
	 * 
	 * @param whereCause
	 * @return
	 */
	List<OrderVO> findOrderStaticsData(OrderVO whereCause);

}

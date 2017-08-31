package org.evm.biz.order.service.impl;

import org.evm.core.service.BaseDBService;

import java.util.List;

import org.evm.biz.order.entity.OrderDeviceVO;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.biz.order.service.IOrderDeviceService;

public class OrderDeviceServiceImpl extends BaseDBService implements IOrderDeviceService {

	@Override
	public List<OrderDeviceVO> getOrderDeviceByOrderId(OrderDeviceVO para) {
		// TODO Auto-generated method stub
		return super.find(IOrderDbService.MS_FIND_REL_ORDER_DEVICE_BY_ORDER_ID, para.getOrderId());
	}

	@Override
	public int batchInsertOrderDeviceVO(List<OrderDeviceVO> para) {
		// TODO Auto-generated method stub
		return super.batchDelete(IOrderDbService.MS_INSERT_REL_ORDER_DEVICE, para);
	}

	@Override
	public int batchUpdateOrderDeviceVO(List<OrderDeviceVO> para) {
		// TODO Auto-generated method stub
		return super.batchUpdate(IOrderDbService.MS_UPDATE_REL_ORDER_DEVICE_BY_RELID, para);
	}

	@Override
	public int batchDeleteOrderDeviceVO(List<OrderDeviceVO> para) {
		// TODO Auto-generated method stub
		return super.batchDelete(IOrderDbService.MS_DELETE_REL_ORDER_DEVICE_BY_RELID, para);
	}

	@Override
	public OrderDeviceVO getOrderDeviceByCond(Long orderId, Long deviceId) {
		// TODO Auto-generated method stub
		OrderDeviceVO whereCause = new OrderDeviceVO();
		whereCause.setOrderId(orderId);
		whereCause.setDeviceId(deviceId);
		return (OrderDeviceVO) super.get("MS_FIND_REL_ORDER_DEVICE_BY_ORDER_ID_DEVICE_ID", whereCause);
	}

}

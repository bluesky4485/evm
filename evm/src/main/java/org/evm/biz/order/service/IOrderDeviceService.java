package org.evm.biz.order.service;

import java.util.List;

import org.evm.biz.order.entity.OrderDeviceVO;

public interface IOrderDeviceService {
	/**
	 * 根据订单ID查询所有设备
	 * 
	 * @param para
	 * @return
	 */
	List<OrderDeviceVO> getOrderDeviceByOrderId(OrderDeviceVO para);
	/**
	 * 根据订单ID和设备类型ID，查询订单中的统计数据
	 * @param para
	 * @return
	 */
	OrderDeviceVO getOrderDeviceByCond(Long orderId,Long deviceId);
	
	/**
	 * 批量增加设备
	 * @param para
	 */
	int batchInsertOrderDeviceVO(List<OrderDeviceVO> para);
	/**
	 * 批量修改设备
	 * @param para
	 * @return
	 */
	int batchUpdateOrderDeviceVO(List<OrderDeviceVO> para);
	/**
	 * 批量删除设备
	 * @param para
	 * @return
	 */
	int batchDeleteOrderDeviceVO(List<OrderDeviceVO> para);
}

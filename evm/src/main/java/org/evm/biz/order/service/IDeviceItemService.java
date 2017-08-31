package org.evm.biz.order.service;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.biz.order.entity.DeviceItemPropertyVO;
import org.evm.biz.order.entity.DeviceItemVO;

public interface IDeviceItemService {
	final String MS_FIND_DEVICEITEM_PAGE = "MS_FIND_DEVICEITEM_PAGE";
	final String MS_FIND_DEVICEITEM_CNT = "MS_FIND_DEVICEITEM_CNT";
	final String MS_DELETE_DEVICEITEM = "MS_DELETE_DEVICEITEM";
	final String MS_FIND_DEVICEITEM_BYID = "MS_FIND_DEVICEITEM_BYID";
	final String MS_INSERT_DEVICEITEM_PROPERTY = "MS_INSERT_DEVICEITEM_PROPERTY";
	final String MS_DELETE_DEVICEITEM_PROPERTY_BY_ITEMID = "MS_DELETE_DEVICEITEM_PROPERTY_BY_ITEMID";
	final String MS_INSERT_DEVICEITEM = "MS_INSERT_DEVICEITEM";

	final String MS_INSERT_REL_ORDER_DEVICE_BY_ADD_DEVICEITEM = "MS_INSERT_REL_ORDER_DEVICE_BY_ADD_DEVICEITEM";
	final String MS_UPDATE_REL_ORDER_DEVICE_BY_ADD_DEVICEITEM = "MS_UPDATE_REL_ORDER_DEVICE_BY_ADD_DEVICEITEM";
	final String MS_SQL_FIND_FIXED_DEVICE_CNT="MS_SQL_FIND_FIXED_DEVICE_CNT";
	final String MS_SQL_UPDATE_ORDER_UPDDATE_ADD_DEVICEITEM="MS_SQL_UPDATE_ORDER_UPDDATE_ADD_DEVICEITEM";
	final String MS_SQL_FIND_DEVICEITEM_BY_ORDERNO_OR_ID="MS_SQL_FIND_DEVICEITEM_BY_ORDERNO_OR_ID";
	
	final String MS_SQL_FIND_DEVICE_BY_UID_CNT="MS_SQL_FIND_DEVICE_BY_UID_CNT";
	/**
	 * 
	 * update by jerry.x 2016年11月8日 分页查询
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findDeviceItemPage(DeviceItemVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年11月8日 删除
	 * 
	 * @param deviceItemId
	 * @return
	 *
	 */
	int deleteDeviceItemById(DeviceItemVO whereCause);

	/**
	 * 查询唯一设备 update by jerry.x 2016年11月8日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	DeviceItemVO findDeviceItemById(DeviceItemVO whereCause);

	/**
	 * 批量插入设备属性
	 * 
	 * @param whereCause
	 * @return
	 */
	int batchInsertDeviceItemProperty(DeviceItemVO deviceItemVO, List<DeviceItemPropertyVO> whereCause);

	/**
	 * 插入设备 update by jerry.x 2016年11月9日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	long insertDeviceItem(DeviceItemVO whereCause);

	/**
	 * 获得订单中具体类型设备的已安装数量
	 * @param orderid
	 * @param deviceId
	 * @return
	 */
	int getFixedCnt(long orderId, long deviceTypeId);
	/**
	 * 根据订单iD或订单NO查询设备
	 * 
	 * @param whereCause
	 * @return
	 */
	List<DeviceItemVO> findDeviceItemByOrder(DeviceItemVO whereCause);
	/**
	 * 根据Uid查询设备
	 * @param whereCause
	 * @return
	 * List<DeviceItemVO> findDeviceItemByUid(String whereCause);
	 */
	
	
}

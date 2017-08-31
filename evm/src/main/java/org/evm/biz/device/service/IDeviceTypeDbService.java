package org.evm.biz.device.service;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.biz.device.entity.DeviceTypePropertyVO;
import org.evm.biz.device.entity.DeviceTypeVO;

/**
 * 
 * @author admin
 *
 */
public interface IDeviceTypeDbService {
	final String MS_FIND_DEVICE_ALL_DEVICE = "MS_FIND_DEVICE_ALL_DEVICE";
	final String MS_FIND_DEVICE_ALL_PAGE = "MS_FIND_DEVICE_ALL_PAGE";
	final String MS_FIND_DEVICE_ALL_CNT = "MS_FIND_DEVICE_ALL_CNT";
	final String MS_INSERT_DEVICE = "MS_INSERT_DEVICE";
	final String MS_DELETE_DEVICE = "MS_DELETE_DEVICE";
	final String MS_UPDATE_DEVICE = "MS_UPDATE_DEVICE";
	// -------------
	final String MS_INSERT_DEVICE_PROPERTY = "MS_INSERT_DEVICE_PROPERTY";
	final String MS_UPDATE_DEVICE_PROPERTY = "MS_UPDATE_DEVICE_PROPERTY";
	final String MS_DELETE_DEVICE_PROPERTY_BY_ID = "MS_DELETE_DEVICE_PROPERTY_BY_ID";
	final String MS_DELETE_DEVICE_PROPERTY = "MS_DELETE_DEVICE_PROPERTY";
	final String MS_FIND_DEVICE_PROPERTY_DEVICEID = "MS_FIND_DEVICE_PROPERTY_DEVICEID";
	// -------------
	final String MS_FIND_DEVICE_PROPERTY_ALL = "MS_FIND_DEVICE_PROPERTY_ALL";

	/**
	 * 查询设备 条件 按ID 和名称
	 * 
	 * @param whereCause
	 * @return
	 */
	List<DeviceTypeVO> findAllDeviceType(DeviceTypeVO whereCause);

	/**
	 * 按ID查询
	 * 
	 * @param deviceID
	 * @return
	 */
	DeviceTypeVO findAllDeviceTypeByID(long deviceID);

	List<DeviceTypeVO> findAllDeviceTypeByClassType(DeviceTypeVO classType);

	List<DeviceTypePropertyVO> findDeviceProperyByDeviceId(long deviceID);

	/**
	 * 分页查询 update by jerry.x 2016年10月21日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findAllDeviceTypePage(DeviceTypeVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月22日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int insertDeviceType(DeviceTypeVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int deleteDeviceType(DeviceTypeVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int updateDDeviceType(DeviceTypeVO whereCause);

	int deleteDeviceTypeProperty(long propertyID);

	/**
	 * 查询设备类型及其属性
	 * 
	 * @param whereCause
	 * @return
	 */
	List<DeviceTypeVO> findDeviceTypeAndPropertyByCond(DeviceTypeVO whereCause);
}

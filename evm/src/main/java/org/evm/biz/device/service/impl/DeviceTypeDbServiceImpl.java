package org.evm.biz.device.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.evm.biz.device.entity.DeviceTypePropertyVO;
import org.evm.biz.device.entity.DeviceTypeVO;
import org.evm.biz.device.service.IDeviceTypeDbService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;

public class DeviceTypeDbServiceImpl extends BaseDBService implements IDeviceTypeDbService {

	@Override
	public List<DeviceTypeVO> findAllDeviceType(DeviceTypeVO whereCause) {
		// TODO Auto-generated method stub
		try {
			return super.find(MS_FIND_DEVICE_ALL_DEVICE, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询设备信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("查询设备信息异常", e);
		}

	}

	@Override
	@SystemServiceLog(description = "查询设备类型信息", bizTypeId = BizOptType.query, functionId = FunctionMap.WEB_QUERY)
	public PageResult findAllDeviceTypePage(DeviceTypeVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<ProjectVO> rows = null;
		int total = super.getCount(MS_FIND_DEVICE_ALL_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_FIND_DEVICE_ALL_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	@SystemServiceLog(description = "创建设备类型信息", bizTypeId = BizOptType.add, functionId = FunctionMap.WEB_ADD)
	public int insertDeviceType(DeviceTypeVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		res = (Integer) super.insert(MS_INSERT_DEVICE, whereCause);
		if (res == 0) {
			throw new SmartDBAccessException("插入设备信息异常");
		}
		try {

			for (DeviceTypePropertyVO p : whereCause.getProperties()) {
				p.setDeviceTypeId(res);
			}
			if (whereCause.getProperties().size() > 0) {
				super.batchInsert(MS_INSERT_DEVICE_PROPERTY, whereCause.getProperties());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("插入设备信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("插入设备信息异常", e);
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "删除设备类型信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.WEB_DELETE)
	public int deleteDeviceType(DeviceTypeVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = super.update(MS_DELETE_DEVICE, whereCause);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("删除设备信息异常!参数：" + whereCause.toString(), e1);
			throw new SmartDBAccessException("删除设备类型信息异常", e1);
		}
		if (res == 0) {
			throw new SmartDBAccessException("插入设备信息异常");
		}
		try {
			if (whereCause.getProperties() != null) {
				super.batchDelete(MS_DELETE_DEVICE_PROPERTY, whereCause.getProperties());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("删除设备属性信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("删除设备属性信息异常", e);
		}
		return 1;
	}

	@Override
	@SystemServiceLog(description = "更新设备类型信息", bizTypeId = BizOptType.update, functionId = FunctionMap.WEB_UPDATE)
	public int updateDDeviceType(DeviceTypeVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		res = super.update(MS_UPDATE_DEVICE, whereCause);
		if (res == 0) {
			throw new SmartDBAccessException("更新设备信息异常");
		}
		try {
			List<DeviceTypePropertyVO> addList = new ArrayList<DeviceTypePropertyVO>();
			List<DeviceTypePropertyVO> allList = whereCause.getProperties();

			if (allList != null) {
				for (DeviceTypePropertyVO vo : whereCause.getProperties()) {
					if (vo.getPropertyId().equals("")) {
						addList.add(vo);
					}
				}
				if (addList.size() > 0) {
					super.batchInsert(MS_INSERT_DEVICE_PROPERTY, addList);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("更新设备属性信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("更新设备属性信息异常", e);
		}

		return res;
	}

	@Override
	public DeviceTypeVO findAllDeviceTypeByID(long deviceID) {
		// TODO Auto-generated method stub
		DeviceTypeVO whereCause = new DeviceTypeVO();
		whereCause.setDeviceId(deviceID);
		whereCause = (DeviceTypeVO) super.get(MS_FIND_DEVICE_ALL_DEVICE, whereCause);
		List<DeviceTypePropertyVO> properties = findDeviceProperyByDeviceId(deviceID);
		whereCause.setProperties(properties);
		return whereCause;
	}

	@Override
	public List<DeviceTypeVO> findAllDeviceTypeByClassType(DeviceTypeVO whereCause) {
		// TODO Auto-generated method stub
//		DeviceTypeVO whereCause = new DeviceTypeVO();
//		whereCause.setClassType(classType);
		return super.find(MS_FIND_DEVICE_ALL_DEVICE, whereCause);
	}

	@Override
	public List<DeviceTypePropertyVO> findDeviceProperyByDeviceId(long deviceID) {
		// TODO Auto-generated method stub

		return super.find(MS_FIND_DEVICE_PROPERTY_DEVICEID, deviceID);
	}

	@Override
	public int deleteDeviceTypeProperty(long propertyID) {
		// TODO Auto-generated method stub
		return super.delete(MS_DELETE_DEVICE_PROPERTY_BY_ID, propertyID);
	}

	@Override
	public List<DeviceTypeVO> findDeviceTypeAndPropertyByCond(DeviceTypeVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_FIND_DEVICE_PROPERTY_ALL, whereCause);
	}
}

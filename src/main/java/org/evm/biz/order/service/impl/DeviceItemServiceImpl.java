package org.evm.biz.order.service.impl;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.service.BaseDBService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.order.entity.DeviceItemPropertyVO;
import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderDeviceVO;
import org.evm.biz.order.service.IDeviceItemService;

public class DeviceItemServiceImpl extends BaseDBService implements IDeviceItemService {

	@Override
	@SystemServiceLog(description = "查询设备信息", bizTypeId = BizOptType.query, functionId = FunctionMap.WEB_QUERY)
	public PageResult findDeviceItemPage(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<DeviceItemVO> rows = null;
		int total = super.getCount(MS_FIND_DEVICEITEM_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_FIND_DEVICEITEM_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	@SystemServiceLog(description = "删除设备信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.WEB_DELETE)
	public int deleteDeviceItemById(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = super.update(MS_DELETE_DEVICEITEM, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("删除设备信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("删除设备信息异常", e);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		return res;
	}

	@Override
	public DeviceItemVO findDeviceItemById(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		return (DeviceItemVO) super.get(MS_FIND_DEVICEITEM_BYID, whereCause);
	}

	@Override
	@SystemServiceLog(description = "更新设备信息", bizTypeId = BizOptType.update, functionId = FunctionMap.WEB_UPDATE)
	public int batchInsertDeviceItemProperty(DeviceItemVO deviceItemVO, List<DeviceItemPropertyVO> whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		int a = 0;
		a = super.update("MS_SQL_UPDATE_DEVICEITEM_UPDDATE", deviceItemVO);
		if (a == 0) {
			throw new SmartFunctionException("数据已旧！");
		}
		try {
			if (whereCause != null) {
				super.delete(MS_DELETE_DEVICEITEM_PROPERTY_BY_ITEMID, deviceItemVO.getDeviceItemId());
				res = super.batchInsert(MS_INSERT_DEVICEITEM_PROPERTY, whereCause);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("更新设备信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("更新设备信息异常", e);
		}
		return res + a;
	}

	@Override
	public long insertDeviceItem(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		boolean checkRes = this.checkDeviceItemUid(whereCause.getDeviceItemUid());
		if (checkRes) {
			throw new SmartDBAccessException("设备唯一标识已存在！");
		}

		int deviceItemId = 0;
		try {
			deviceItemId = (Integer) super.insert(MS_INSERT_DEVICEITEM, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("创建设备信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("更新设备信息异常", e);
		}
		if (deviceItemId == 0) {
			throw new SmartDBAccessException("创建设备信息异常！");
		}

		try {
			List<DeviceItemPropertyVO> deviceItemPropertyList = whereCause.getDeviceItemPropertyList();
			if (deviceItemPropertyList != null) {
				for (DeviceItemPropertyVO vo : deviceItemPropertyList) {
					vo.setDeviceItemId(deviceItemId);
					vo.setInsUser(whereCause.getInsUser());
					vo.setUpdUser(whereCause.getUpdUser());
				}
				super.batchInsert(MS_INSERT_DEVICEITEM_PROPERTY, deviceItemPropertyList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("创建设备信息属性异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("创建设备信息属性异常", e);
		}
		// 增加设备，看订单中是否有该类型设备，如果有，安装数+1，如果无，插入总数1，可领数0
		try {
			int cnt = this.getFixedCnt(whereCause.getOrderId(), whereCause.getDeviceTypeId());
			OrderDeviceVO orderDeviceVO = new OrderDeviceVO();
			orderDeviceVO.setDeviceCnt(cnt);
			orderDeviceVO.setPlanCnt(0);
			orderDeviceVO.setDeviceId(whereCause.getDeviceTypeId());
			orderDeviceVO.setOrderId(whereCause.getOrderId());
			orderDeviceVO.setInsUser(whereCause.getInsUser());
			orderDeviceVO.setUpdUser(whereCause.getUpdUser());
			OrderDeviceVO res = getOrderDeviceByCond(whereCause.getOrderId(), whereCause.getDeviceTypeId());
			if (res == null) {
				super.insert(MS_INSERT_REL_ORDER_DEVICE_BY_ADD_DEVICEITEM, orderDeviceVO);
				super.update(MS_SQL_UPDATE_ORDER_UPDDATE_ADD_DEVICEITEM, whereCause);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("创建设备信息,更新订单中设备统计异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("创建设备信息,更新订单中设备统计异常", e);
		}
		return deviceItemId;
	}

	@Override
	public int getFixedCnt(long orderId, long deviceTypeId) {
		// TODO Auto-generated method stub
		DeviceItemVO whereCause = new DeviceItemVO();
		whereCause.setOrderId(orderId);
		whereCause.setDeviceTypeId(deviceTypeId);
		return getCount(MS_SQL_FIND_FIXED_DEVICE_CNT, whereCause);
	}

	public OrderDeviceVO getOrderDeviceByCond(Long orderId, Long deviceId) {
		// TODO Auto-generated method stub
		OrderDeviceVO whereCause = new OrderDeviceVO();
		whereCause.setOrderId(orderId);
		whereCause.setDeviceId(deviceId);
		return (OrderDeviceVO) super.get("MS_FIND_REL_ORDER_DEVICE_BY_ORDER_ID_DEVICE_ID", whereCause);
	}

	@Override
	public List<DeviceItemVO> findDeviceItemByOrder(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_SQL_FIND_DEVICEITEM_BY_ORDERNO_OR_ID, whereCause);
	}

	/**
	 * UID唯一值校验
	 * 
	 * @param deviceItemUid
	 * @return
	 */
	private boolean checkDeviceItemUid(String deviceItemUid) {
		if (deviceItemUid == "")
			return true;
		int res = super.getCount(MS_SQL_FIND_DEVICE_BY_UID_CNT, deviceItemUid);
		return res > 0 ? true : false;
	}
}

package org.evm.biz.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.order.entity.IndexOrderStVO;
import org.evm.biz.order.entity.OrderDeviceVO;
import org.evm.biz.order.entity.OrderFileVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;

import com.alibaba.fastjson.JSON;

public class OrderDbServiceImpl extends BaseDBService implements IOrderDbService {

	@Override
	@SystemServiceLog(description = "创建订单信息", bizTypeId = BizOptType.add, functionId = FunctionMap.WEB_ADD)
	public long insertOrder(OrderVO whereCause) {
		// TODO Auto-generated method stub
		int orderId = 0;
		try {
			orderId = (Integer) super.insert(MS_INSERT_ORDER, whereCause);
			whereCause.setOrderId(orderId);
			List<OrderDeviceVO> deviceList = JSON.parseArray(whereCause.getDeviceStr(), OrderDeviceVO.class);

			if (deviceList != null && deviceList.size() > 0) {
				whereCause.setDeviceList(deviceList);
				for (OrderDeviceVO device : whereCause.getDeviceList()) {
					device.setOrderId(orderId);
					device.setUpdUser(whereCause.getUpdUser());
					device.setInsUser(whereCause.getInsUser());
				}
				super.batchInsert(MS_INSERT_REL_ORDER_DEVICE, deviceList);
				// TODO:不直接创建设备信息
				// List<DeviceItemVO> itemList = new ArrayList<DeviceItemVO>();
				// for (OrderDeviceVO device : whereCause.getDeviceList()) {
				// if (device.getDeviceCnt() > 0) {
				// for (int i = 0; i < device.getDeviceCnt(); i++) {
				// DeviceItemVO vo = new DeviceItemVO();
				// vo.setDeviceItemName(device.getDeviceName());
				// vo.setOrderId(device.getOrderId());
				// vo.setDeviceTypeId(device.getDeviceId());
				// vo.setUpdUser(whereCause.getUpdUser());
				// vo.setInsUser(whereCause.getInsUser());
				// itemList.add(vo);
				// }
				// }
				// }
				// super.batchInsert(MS_INSERT_DEVICEITEM, itemList);
			}
			String fileStrs = whereCause.getFileStrs();
			if (fileStrs != "") {
				String[] paths = whereCause.getFileStrs().split("@");
				if (paths.length > 0) {
					List<OrderFileVO> files = new ArrayList<OrderFileVO>();
					for (String path : paths) {
						OrderFileVO file = new OrderFileVO();
						String[] arr = path.split("&");
						file.setFilePath(arr[0]);
						file.setFileName(arr[1]);
						file.setBizType(arr[2]);
						file.setOrderId(whereCause.getOrderId());
						file.setUpdUser(whereCause.getUpdUser());
						file.setInsUser(whereCause.getInsUser());
						files.add(file);
					}
					super.batchInsert(MS_INSERT_REL_OREDER_FILE, files);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("创建订单信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("创建订单信息异常", e);
		}
		return orderId;
	}

	@Override
	@SystemServiceLog(description = "修改订单信息", bizTypeId = BizOptType.update, functionId = FunctionMap.WEB_UPDATE)
	public long updateOrder(OrderVO whereCause) {
		// TODO Auto-generated method stub
		long res = 0;
		try {
			res = super.update(MS_UPDATE_ORDER, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("修改订单信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("修改订单信息异常", e);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		try {
			super.delete(MS_DELETE_REL_ORDER_DEVICE_BY_ORDER_ID, whereCause.getOrderId());
			List<OrderDeviceVO> deviceList = JSON.parseArray(whereCause.getDeviceStr(), OrderDeviceVO.class);
			if (deviceList != null && deviceList.size() > 0) {
				whereCause.setDeviceList(deviceList);
				for (OrderDeviceVO device : whereCause.getDeviceList()) {
					device.setOrderId(whereCause.getOrderId());
					device.setUpdUser(whereCause.getUpdUser());
					device.setInsUser(whereCause.getInsUser());
				}
				super.batchInsert(MS_INSERT_REL_ORDER_DEVICE, deviceList);
			}
			super.delete(MS_DELETE_ORDER_FILE_BY_ORDER_ID, whereCause.getOrderId());
			String fileStrs = whereCause.getFileStrs();
			if (fileStrs != "") {
				String[] paths = whereCause.getFileStrs().split("@");
				if (paths.length > 0) {
					List<OrderFileVO> files = new ArrayList<OrderFileVO>();
					for (String path : paths) {
						OrderFileVO file = new OrderFileVO();
						String[] arr = path.split("&");
						file.setFilePath(arr[0]);
						file.setFileName(arr[1]);
						file.setBizType(arr[2]);
						file.setOrderId(whereCause.getOrderId());
						file.setUpdUser(whereCause.getUpdUser());
						file.setInsUser(whereCause.getInsUser());
						files.add(file);
					}
					super.batchInsert(MS_INSERT_REL_OREDER_FILE, files);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("修改订单其它信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("修改订单其它信息异常", e);
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "删除订单信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.WEB_DELETE)
	public long deleteOrder(OrderVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = super.update(MS_DELETE_ORDER, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("删除订单信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("删除订单信息异常", e);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		return res;
	}

	@Override
	public List<OrderVO> findOrderId(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_SELECT_ORDER_IDNO_COND, whereCause);
	}

	@Override
	@SystemServiceLog(description = "查询订单信息", bizTypeId = BizOptType.query, functionId = FunctionMap.WEB_QUERY)
	public PageResult findOrderPage(OrderVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<OrderVO> rows = null;
		int total = super.getCount(MS_SELECT_ORDER_PAGE_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_SELECT_ORDER_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	public OrderVO getOrderByID(long whereCause) {
		// TODO Auto-generated method stub
		return (OrderVO) super.get(MS_GET_ORDER_BYID, whereCause);
	}

	@Override
	@SystemServiceLog(description = "批量创建订单信息", bizTypeId = BizOptType.add, functionId = FunctionMap.WEB_ADD)
	public long batchInsertOrder(List<OrderVO> whereCause) {
		// TODO Auto-generated method stub
		int cnt = (Integer) super.batchInsert(MS_INSERT_ORDER, whereCause);
		return cnt;
	}

	@Override
	public List<OrderVO> findOrderByProjectId(long projectId) {
		// TODO Auto-generated method stub
		return super.find(MS_SELECT_ORDER_BY_PROJECTID, projectId);
	}

	@Override
	public List<OrderVO> findOrderForApp(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_SELECT_ORDER_PAGE, whereCause);
	}

	@Override
	public List<OrderVO> findOrderForIndex(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_SELECT_ORDER_INDEX, whereCause);
	}

	@Override
	public int updateOrderUserAppraise(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_USERAPRRISE, whereCause);
	}

	@Override
	public List<IndexOrderStVO> findOrderSTForIndex(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_SELECT_ORDER_INDEX_ST, whereCause);
	}

	@Override
	public OrderVO getOrderByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return (OrderVO) super.get(MS_GET_ORDER_BY_ORDERNO, orderNo);
	}

	@Override
	public int updateOrderWorkProgress(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_WORKPROGRESS, whereCause);
	}

	@Override
	public int updateOrderWorkBeginDate(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_WORKBEGINDATE, whereCause);
	}

	@Override
	public long insertOrderFile(OrderFileVO whereCause) {
		// TODO Auto-generated method stub
		OrderVO order = new OrderVO();
		order.setOrderId(whereCause.getOrderId());
		order.setUpdDate(whereCause.getUpdDate());
		order.setInsUser(whereCause.getInsUser());
		order.setUpdUser(whereCause.getUpdUser());

		int a = this.update(MS_UPDATE_ORDER_UPD_DATE, order);
		if (a == 0)
			throw new SmartDBAccessException("数据已旧，插入文件信息失败！");
		long res = (Long) this.insert(MS_INSERT_REL_OREDER_FILE, whereCause);
		return res;
	}

	@Override
	public int deleteOrderFile(OrderFileVO whereCause) {
		// TODO Auto-generated method stub
		OrderVO moder = new OrderVO();
		moder.setUpdDate(whereCause.getUpdDate());
		moder.setUpdUser(whereCause.getUpdUser());
		moder.setOrderId(whereCause.getOrderId());
		int a = this.update(MS_UPDATE_ORDER_UPD_DATE, moder);
		if (a == 0)
			throw new SmartDBAccessException("数据已旧，删除文件信息失败！");
		int res = (int) super.delete(MS_DELETE_ORDER_FILE_BY_FILE_ID, whereCause);
		return res;
	}

	@Override
	public int updateOrderWorkAddress(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_WORKADDRESS, whereCause);
	}

}

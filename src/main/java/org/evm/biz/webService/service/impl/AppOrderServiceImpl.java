package org.evm.biz.webService.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.evm.biz.device.service.IDeviceTypeDbService;
import org.evm.biz.morder.service.IMOrderDbService;
import org.evm.biz.order.entity.DeviceItemPropertyVO;
import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderDeviceVO;
import org.evm.biz.order.entity.OrderFileVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.order.service.IDeviceItemService;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.biz.order.service.IOrderDeviceService;
import org.evm.biz.phoneapp.service.IAppDbService;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.entity.RelProjectDeviceVO;
import org.evm.biz.project.service.IProjectDbService;
import org.evm.biz.webService.service.IAppOrderService;
import org.evm.core.exception.SmartRuntimeException;
import org.evm.core.log.DataCenterLogger;
import org.evm.core.util.UploadHelper;

import com.alibaba.fastjson.JSON;

import sun.misc.BASE64Decoder;

/**
 * 订单管理
 * 
 * @author jerry.x update 2016年10月31日 下午8:57:05
 */
public class AppOrderServiceImpl implements IAppOrderService {
	IOrderDbService orderDbService;
	IOrderDeviceService orderDeviceService;
	IAppDbService appDbService;
	IProjectDbService projectDbService;
	IDeviceItemService deviceItemService;
	protected DataCenterLogger logger = new DataCenterLogger(this.getClass());

	public void setProjectDbService(IProjectDbService projectDbService) {
		this.projectDbService = projectDbService;
	}

	public void setOrderDbService(IOrderDbService orderDbService) {
		this.orderDbService = orderDbService;
	}

	public void setOrderDeviceService(IOrderDeviceService orderDeviceService) {
		this.orderDeviceService = orderDeviceService;
	}

	public void setAppDbService(IAppDbService appDbService) {
		this.appDbService = appDbService;
	}

	public void setDeviceItemService(IDeviceItemService deviceItemService) {
		this.deviceItemService = deviceItemService;
	}

	@Override
	public List<OrderVO> findAllOrder(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return orderDbService.findOrderForApp(whereCause);
	}

	@Override
	public OrderVO getOrder(OrderVO para) {
		// TODO Auto-generated method stub
		return orderDbService.getOrderByOrderNo(para.getOrderNo());
	}

	@Override
	public List<OrderDeviceVO> findOrderDeviceVO(OrderDeviceVO para) {
		// TODO Auto-generated method stub
		return orderDeviceService.getOrderDeviceByOrderId(para);
	}

	@Override
	public int updateOrderUserAppraise(OrderVO para) {
		// TODO Auto-generated method stub
		return orderDbService.updateOrderUserAppraise(para);
	}

	@Override
	public List<OrderVO> findOrderAndMorder(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return appDbService.findAppOrderByProjectUser(whereCause);
	}

	@Override
	public List<DeviceItemVO> findDeviceItemListByOrder(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		return deviceItemService.findDeviceItemByOrder(whereCause);
	}

	@Override
	public List<RelProjectDeviceVO> findDeviceItemListByProject(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return projectDbService.findAllProjectDeviceTypeByProject(whereCause);
	}

	@Override
	public int batchInsertOrderDeviceVO(List<OrderDeviceVO> whereCause) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int batchUpdateOrderDeviceVO(List<OrderDeviceVO> whereCause) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int batchDeleteOrderDeviceVO(List<OrderDeviceVO> whereCause) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addDeviceItem(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		return (int) deviceItemService.insertDeviceItem(whereCause);
	}

	@Override
	public int deleteDeviceItem(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		int res = this.deviceItemService.deleteDeviceItemById(whereCause);
		return res;
	}

	@Override
	public int updateDeviceItem(DeviceItemVO whereCause) {
		// TODO Auto-generated method stub
		List<DeviceItemPropertyVO> list = new ArrayList<DeviceItemPropertyVO>();
		String Str = whereCause.getDeviceItemPropertyStr();
		list = JSON.parseArray(Str, DeviceItemPropertyVO.class);
		for (int i = 0; list != null && i < list.size(); i++) {
			list.get(i).setInsUser(whereCause.getInsUser());
			list.get(i).setUpdUser(whereCause.getInsUser());
		}
		return deviceItemService.batchInsertDeviceItemProperty(whereCause, list);
	}

	@Override
	public int updateOrderWorkProgress(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return this.orderDbService.updateOrderWorkProgress(whereCause);
	}

	@Override
	public int updateOrderWorkBeginDate(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return this.orderDbService.updateOrderWorkBeginDate(whereCause);
	}

	@Override
	public long uploadOrderCompletImg(OrderFileVO whereCause) {
		// TODO Auto-generated method stub
		URL url = this.getClass().getClassLoader().getResource("../../" + IOrderDbService.orderFileDic);
		String filePath = this.decodeBase64ToImage(whereCause.getFileContent(), whereCause.getFileType(),
				whereCause.getOrderNo());
		whereCause.setFilePath(filePath);
		whereCause.setBizType("2");
		if (whereCause.getFileName() == null) {
			whereCause.setFileName(filePath);
		}
		long res = this.orderDbService.insertOrderFile(whereCause);
		return res;
	}

	@Override
	public long uploadOrderWorkImg(OrderFileVO whereCause) {
		// TODO Auto-generated method stub
		String fileName = this.decodeBase64ToImage(whereCause.getFileContent(), whereCause.getFileType(),
				whereCause.getOrderNo());
		String filePath = IOrderDbService.orderFileDic + "\\" + whereCause.getOrderNo() + "\\" + fileName;
		whereCause.setFilePath(filePath);
		// whereCause.setBizType("1");
		if (whereCause.getFileName() == null) {
			whereCause.setFileName(filePath);
		}
		long res = this.orderDbService.insertOrderFile(whereCause);
		return res;
	}

	@Override
	public int deleteOrderWorkImg(OrderFileVO whereCause) {
		// TODO Auto-generated method stub
		return this.orderDbService.deleteOrderFile(whereCause);
	}

	public String decodeBase64ToImage(String base64, String fileType, String orderId) {
		String v = "";
		URL url = this.getClass().getClassLoader().getResource("../../" + IOrderDbService.orderFileDic);
		String imgName = UUID.randomUUID() + "." + fileType;
		try {
			String path = URLDecoder.decode(url.getPath(), "utf-8");
			// url.getPath();//
			// directory.getAbsolutePath();//
			path += "\\" + orderId;
			File fileFolder = new File(path);
			// 如果文件夹不存在则创建
			if (!fileFolder.exists() && !fileFolder.isDirectory()) {
				logger.debug("需要创建文件夹:" + path);
				fileFolder.mkdir();
			}
			logger.info("将Base64位编码的图片的文件名=" + imgName);
			BASE64Decoder decoder = new BASE64Decoder();
			FileOutputStream write = new FileOutputStream(new File(path + "\\" + imgName));
			byte[] decoderBytes = decoder.decodeBuffer(base64);
			write.write(decoderBytes);
			write.close();
		} catch (IOException e) {
			logger.error("将Base64位编码的图片进行解码异常", e);
			throw new SmartRuntimeException("写文件异常" + e.getMessage());
		}
		return imgName;
	}

	@Override
	public int updateOrderWorkAddress(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return this.orderDbService.updateOrderWorkAddress(whereCause);
	}
}

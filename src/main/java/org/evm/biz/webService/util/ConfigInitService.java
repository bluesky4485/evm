package org.evm.biz.webService.util;

import java.util.HashMap;

import org.evm.biz.device.entity.DeviceTypeVO;
import org.evm.biz.morder.entity.MOrderFileVO;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderDeviceVO;
import org.evm.biz.order.entity.OrderFileVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.entity.RelProjectDeviceVO;
import org.evm.biz.user.entity.UserVO;

/**
 * 对象映射表
 * 
 * @author jerry.x update 2016年10月31日 下午8:06:19
 */
public class ConfigInitService {
	
	/**
	 * 输入参数对象一映射表
	 */
	static HashMap<String, Class> serviceParaClassMap = new HashMap<String, Class>();

	static {
		serviceParaClassMap.put("300001", UserVO.class);
		serviceParaClassMap.put("300002", UserVO.class);
		serviceParaClassMap.put("100100", OrderVO.class);
		serviceParaClassMap.put("100200", OrderVO.class);
		serviceParaClassMap.put("100201", ProjectVO.class);
		serviceParaClassMap.put("100202", OrderVO.class);
		serviceParaClassMap.put("100203", OrderDeviceVO.class);
		serviceParaClassMap.put("100205", DeviceItemVO.class);
		serviceParaClassMap.put("100206", DeviceTypeVO.class);
		serviceParaClassMap.put("100300", MOrderVO.class);
		
		serviceParaClassMap.put("100302", MOrderVO.class);
		serviceParaClassMap.put("100303", MOrderVO.class);
		serviceParaClassMap.put("100312", MOrderVO.class);
		
		
		serviceParaClassMap.put("310305", MOrderVO.class); 
		serviceParaClassMap.put("330306", MOrderVO.class);  
		serviceParaClassMap.put("330307", MOrderVO.class);  
		serviceParaClassMap.put("330308", MOrderVO.class); 
		serviceParaClassMap.put("330309", MOrderVO.class); 
		serviceParaClassMap.put("330310", MOrderFileVO.class);
		serviceParaClassMap.put("330311", MOrderFileVO.class);
		serviceParaClassMap.put("330312", MOrderVO.class);
		//修改工程订单的评价信息。
		serviceParaClassMap.put("310205", OrderVO.class);
		//
		serviceParaClassMap.put("320206", OrderVO.class);
		//
		serviceParaClassMap.put("320207", OrderVO.class);
		//
		serviceParaClassMap.put("320208", RelProjectDeviceVO.class); 
		serviceParaClassMap.put("320209", RelProjectDeviceVO.class);  
		serviceParaClassMap.put("320210", RelProjectDeviceVO.class); 
		//增删改设备
		serviceParaClassMap.put("320212", DeviceItemVO.class);  
		serviceParaClassMap.put("320213", DeviceItemVO.class);  
		serviceParaClassMap.put("320214", DeviceItemVO.class); 
		//上传工程图片
		serviceParaClassMap.put("320215", OrderFileVO.class);
		//删除工程图片
		serviceParaClassMap.put("320216", OrderFileVO.class);
	}

	/**
	 * 根据服务ID获得参数的类型 update by jerry.x 2016年10月31日
	 * 
	 * @param serviceID
	 * @return
	 *
	 */
	public static Class getClazz(String serviceID) {
		// TODO Auto-generated method stub
		return (Class) serviceParaClassMap.get(serviceID);
	}

}

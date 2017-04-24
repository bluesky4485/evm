package org.evm.biz.webService.util;

import org.evm.biz.webService.entity.WsEntity;
import org.evm.core.entity.AbstractEntity;
import org.evm.core.util.CommonUtil;

import com.alibaba.fastjson.JSON;

public class WebServiceUtil {

	/**
	 * 解码参数 update by jerry.x 2016年10月14日
	 * 
	 * @param para
	 * @return
	 *
	 */
	public static WsEntity decodeWsPara(String para) {
		WsEntity wsEntity = JSON.parseObject(para, WsEntity.class);
		Class clazz = null;
		AbstractEntity paraEntity = (AbstractEntity) JSON.parseObject(wsEntity.getPara(),
				(Class) ConfigInitService.getClazz(wsEntity.getServiceId()));
		//TODO:SystemID
		paraEntity.setSystemId(Integer.parseInt(wsEntity.getSystemId()));

		wsEntity.setParaEntity(paraEntity);
		//TODO:
		paraEntity.setUpdUser(wsEntity.getUid());
		paraEntity.setInsUser(wsEntity.getUid());
		// TODO:分页查询中关于页数的计算
		int pageScope[] = CommonUtil.calculatePage(paraEntity.getPageSize(), paraEntity.getPageNum());
		paraEntity.setStartPageNum(pageScope[0]);
		paraEntity.setEndPageNum(pageScope[1]);
		return wsEntity;
	}
}

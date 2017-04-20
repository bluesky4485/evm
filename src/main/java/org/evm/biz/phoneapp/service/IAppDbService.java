package org.evm.biz.phoneapp.service;

import java.util.List;

import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.phoneapp.entity.AppDataStVO;

public interface IAppDbService {
	final String MS_SQL_APP_FIND_ORDER_MORDER_BY_UID="MS_SQL_APP_FIND_ORDER_MORDER_BY_UID";
	final String MS_SQL_APP_FIND_ORDER_LOGIN="MS_SQL_APP_FIND_ORDER_LOGIN";
	final String MS_SQL_APP_FIND_MORDER_LOGIN="MS_SQL_APP_FIND_MORDER_LOGIN";
	final String MS_SQL_APP_FIND_MORDER_100312="MS_SQL_APP_FIND_MORDER_100312";
	
	/**
	 * app登录后的统计
	 * 
	 * @return
	 */
	AppDataStVO getAppDataStVO(long uid);
	/**
	 * app 工程订单和维修订单查询
	 * @param whereCause
	 * @return
	 */
	List<OrderVO> findAppOrderByProjectUser(OrderVO whereCause);
	/**
	 * App查询工程订单
	 * @param whereCause
	 * @return
	 */
	List<OrderVO> findAppOrder (OrderVO whereCause);
	/**
	 * App查询维修订单
	 * @param whereCause
	 * @return
	 */
	List<OrderVO> findAppMOrder (OrderVO whereCause);
	/**
	 * 
	 * @param whereCause
	 * @return
	 */
	MOrderVO getLastMorder(MOrderVO whereCause);
}

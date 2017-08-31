package org.evm.biz.datast.service;

import org.evm.biz.datast.entity.CustomPageStVO;
import org.evm.biz.datast.entity.MOrderPageStVO;
import org.evm.biz.datast.entity.OrderPageStVO;
import org.evm.biz.datast.entity.ProjectPageStVO;

public interface IPageDataStService {
	/**
	 * 统计 今日停止服务的客户 数 3日停止服务的客户数 15日停止服务的客户 数 一个月(30)停止服务的客户 数 update by jerry.x
	 * 2016年11月16日
	 * 
	 * @param stopTime
	 * @return
	 *
	 */
	CustomPageStVO findStopServiceCustomer();

	/**
	 * 今日工期截止的项目
	 * 最近一个月签订的项目
	 * 最近一周工期截止的项目
	 * 所有未处理完的订单
	 * update by jerry.x 2016年11月16日
	 * 
	 * @return
	 *
	 */
	ProjectPageStVO findProjectPageStVO();
	OrderPageStVO findOrderPageStVO();
	/**
	 * 首页用户未处理订单数量
	 * update by jerry.x 2016年11月24日 
	*@param userId
	*@return 
	 *
	 */
	MOrderPageStVO findMOrderStVO(long userId);
	/**
	 * 首页未完成订单统计
	 * @return
	 */
	MOrderPageStVO findAllMOrderStVO();
}

package org.evm.biz.datast.service.impl;

import java.util.Calendar;

import org.evm.biz.datast.entity.CustomPageStVO;
import org.evm.biz.datast.entity.MOrderPageStVO;
import org.evm.biz.datast.entity.OrderPageStVO;
import org.evm.biz.datast.entity.ProjectPageStVO;
import org.evm.biz.datast.service.IPageDataStService;
import org.evm.biz.sys.SysUtil;
import org.evm.core.service.BaseDBService;

public class PageDataStServiceImpl extends BaseDBService implements IPageDataStService {

	@Override
	public ProjectPageStVO findProjectPageStVO() {
		ProjectPageStVO vo = new ProjectPageStVO();
		// 今日工期截止的项目
		vo.setTodayStopWorkCnt(super.getCount("MS_SQL_FIND_PROJ_TODAY_END_COUNT", null));
		// 7日内工期截止的项目
		vo.setWeekStopWorkCnt(super.getCount("MS_SQL_FIND_PROJ_END_COUNT", 7));
		// 本月内工期截止的项目
		vo.setMothSingProjectCnt(super.getCount("MS_SQL_FIND_PROJ_MONTH_END_COUNT", null));
		// 所有项目
		vo.setAllNonDealOrderCnt(super.getCount("MS_SQL_FIND_ALL_PROJECT", -1));
		return vo;
	}

	@Override
	public CustomPageStVO findStopServiceCustomer() {
		// TODO Auto-generated method stub
		CustomPageStVO result = new CustomPageStVO();
		// 今日停止服务的客户
		result.setTodayStopServiceCnt(super.getCount("MS_SQL_FIND_CUSTOM_TODAY_STOP_SERVICE_COUNT", 0));
		// 7日停止服务的客户
		result.setThreeDayStopServiceCnt(super.getCount("MS_SQL_FIND_CUSTOM_STOP_SERVICE_COUNT", 7));
		// 本月内停止服务的客户
		result.setMonthStopServiceCnt(super.getCount("MS_SQL_FIND_CUSTOM_STOP_MONTH_SERVICE_COUNT", null));
		// 所有客户
		result.setAllCustomerCnt(super.getCount("MS_SQL_FIND_CUSTOM_ALL_CNT", SysUtil.getCurrentMonthLastDay()));
		return result;
	}

	@Override
	public OrderPageStVO findOrderPageStVO() {
		// TODO Auto-generated method stub
		OrderPageStVO result = new OrderPageStVO();
		// 今日创建的未处理的订单
		result.setTodayNoDoOrderCnt(super.getCount("MS_SQL_FIND_ORDER_TODAY_NODO_COUNT", null));
		// 7日内创建的未处理的订单
		result.setWeekNoDoOrderCnt(super.getCount("MS_SQL_FIND_ORDER_WEEK_NODO_COUNT", null));
		// 本月创建的未处理的订单
		result.setMonthNoDoOrderCnt(super.getCount("MS_SQL_FIND_ORDER_MONTH_NODO_COUNT", null));
		// 所有的订单
		result.setAllOrderCnt(super.getCount("MS_SQL_FIND_ORDER_ALLCOUNT", null));
		return result;
	}

	@Override
	public MOrderPageStVO findMOrderStVO(long userId) {
		// TODO Auto-generated method stub
		MOrderPageStVO result = new MOrderPageStVO();
		result.setAllNoDoMorderCnt(super.getCount("MS_SQL_FIND_PORDER_ACOUNT", userId));
		return result;
	}

	@Override
	public MOrderPageStVO findAllMOrderStVO() {
		// TODO Auto-generated method stub
		MOrderPageStVO result = new MOrderPageStVO();
		// 未处理的运维订单
		result.setAllNoDoMorderCnt(super.getCount("MS_SQL_FIND_PORDER_STATUS_ACOUNT", 0));
		// 未回访的运维订单
		result.setNoCallBackMorderCnt(super.getCount("MS_SQL_FIND_PORDER_STATUS_ACOUNT", 3));
		// 运维中的运维订单
		result.setDoingMorderCnt(super.getCount("MS_SQL_FIND_PORDER_STATUSING_ACOUNT", ""));
		// 全部的运维订单
		result.setProblemMorderCnt(super.getCount("MS_SQL_FIND_ALL_MORDER_CNT", ""));
		return result;
	}
}

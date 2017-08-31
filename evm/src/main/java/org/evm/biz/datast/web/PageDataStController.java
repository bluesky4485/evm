package org.evm.biz.datast.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.datast.entity.CustomPageStVO;
import org.evm.biz.datast.entity.MOrderPageStVO;
import org.evm.biz.datast.entity.OrderPageStVO;
import org.evm.biz.datast.entity.ProjectPageStVO;
import org.evm.biz.datast.service.IPageDataStService;
import org.evm.core.web.AbstractMultiController;

/**
 * 页面数据统计
 * 
 * @author jerry.x update 2016年11月16日 下午3:43:27
 */
public class PageDataStController extends AbstractMultiController {
	private IPageDataStService pageDataStService;

	public void setPageDataStService(IPageDataStService pageDataStService) {
		this.pageDataStService = pageDataStService;
	}

	/**
	 * 
	 * update by jerry.x 2016年11月18日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxProjectPageST(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProjectPageStVO pagest = new ProjectPageStVO();
		try {
			pagest = pageDataStService.findProjectPageStVO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("项目页面统计异常", e);
		}
		super.ReturnAjaxResult(response, pagest);
	}

	public void ajaxCustomPageST(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CustomPageStVO pagest = new CustomPageStVO();
		try {
			pagest = pageDataStService.findStopServiceCustomer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("客户页面统计异常", e);
		}
		super.ReturnAjaxResult(response, pagest);
	}

	public void ajaxOrderPageST(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderPageStVO pagest = new OrderPageStVO();
		try {
			pagest = pageDataStService.findOrderPageStVO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("订单页面统计异常", e);
		}
		super.ReturnAjaxResult(response, pagest);
	}

	/**
	 * 未处理的订单应该是刚创建的 
	 * 未回访的应该是维修完成，
	 * 但是未回访的 运维中的应该是从预约和维修中的状态 
	 * update by jerry.x
	 * 2016年11月24日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxMOrderST(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MOrderPageStVO pagest = new MOrderPageStVO();
		try {
			pagest = pageDataStService.findMOrderStVO(super.getCurrentLoginVO(request).getUid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("未处理页面统计异常", e);
		}
		super.ReturnAjaxResult(response, pagest);
	}

	public void ajaxAllMOrderST(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MOrderPageStVO pagest = new MOrderPageStVO();
		try {
			pagest = pageDataStService.findAllMOrderStVO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("维修订单页面统计异常", e);
		}
		super.ReturnAjaxResult(response, pagest);
	}
}

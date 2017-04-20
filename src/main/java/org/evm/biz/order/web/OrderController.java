package org.evm.biz.order.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.order.common.OrderNoBuilder;
import org.evm.biz.order.entity.IndexOrderStVO;
import org.evm.biz.order.entity.OrderFileVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.biz.sys.SysUtil;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.util.UploadHelper;
import org.evm.core.web.AbstractMultiController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class OrderController extends AbstractMultiController {

	/**
	 * 文件服务器路径
	 * 
	 * @return
	 */
	public String getFileServerPath(String orderNo) {
		return getServletContext().getRealPath("/") + IOrderDbService.orderFileDic + orderNo + "//";
	}

	IOrderDbService orderDbService;

	public void setOrderDbService(IOrderDbService orderDbService) {
		this.orderDbService = orderDbService;
	}

	/**
	 * 获取订单编号
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void ajaxGetOrderNo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		super.ReturnAjaxResult(response, OrderNoBuilder.BuildOrderNo());
	}

	public void ajaxFindOrderIdNo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		List<OrderVO> result = null;
		try {
			result = this.orderDbService.findOrderId(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, result, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindOrderPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		if (whereCause.getQueryStat() != null && whereCause.getQueryStat().equals("3")) {
			whereCause.setStopServiceDays(SysUtil.getCurrentMonthLastDay());
		}
		PageResult pageResult = null;
		try {
			pageResult = this.orderDbService.findOrderPage(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindOrderById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		OrderVO pageResult = null;
		try {
			pageResult = this.orderDbService.getOrderByID(whereCause.getOrderId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年10月26日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxDeleteOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long res = 0;
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = this.orderDbService.deleteOrder(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "删除订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年10月31日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxInsertOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		long res = 0;
		try {
			res = orderDbService.insertOrder(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "插入订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 批量增加订单 update by jerry.x 2016年11月7日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxBatchInsertOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		long res = 0;
		try {
			List<OrderVO> list = new ArrayList<OrderVO>();
			String pre = whereCause.getBatchOrderPre();
			int cnt = whereCause.getBatchOrderCnt();
			for (int i = 0; i < cnt; i++) {
				OrderVO order = new OrderVO();
				order.setProjectId(whereCause.getProjectId());
				order.setOrderNo(pre + (i + 1));
				order.setWorkStatus("0");
				order.setWorkCnt("0");
				order.setWorkProgress("0");
				order.setInsUser(super.getLoginUserId(request));
				order.setUpdUser(super.getLoginUserId(request));
				list.add(order);
			}
			res = orderDbService.batchInsertOrder(list);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "插入订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 
	 * update by jerry.x 2016年10月31日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxUpdateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		long res = 0;
		try {
			res = orderDbService.updateOrder(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "更新订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	public ModelAndView returnManagePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("orderManage");
		return mv;
	}

	public ModelAndView gotoUpdatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		ModelAndView mv = new ModelAndView("updOrder");
		mv.addObject("updObj", whereCause);
		return mv;
	}

	/**
	 * 上传文件最大10m
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		UploadHelper helper = new UploadHelper(request);
		String revPath = "//" + IOrderDbService.orderFileDic + orderNo + "//";
		List<OrderFileVO> list = new ArrayList<OrderFileVO>();
		// 上传文件
		List<MultipartFile> multipartFiles = helper.getFileMap(1024 * 1024 * 10, null);
		for (MultipartFile multipartFile : multipartFiles) {
			String filePath = helper.uploadFile(multipartFile, getFileServerPath(orderNo));
			OrderFileVO file = getFileVO(multipartFile, revPath + new File(filePath).getName());
			list.add(file);
		}
		ReturnAjaxResult(response, list);
		return null;
	}

	/**
	 * 文件下载，获取upload/file路径
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public void download(HttpServletRequest request, HttpServletResponse response) {
		// Long id = ServletRequestUtils.getLongParameter(request, "id",0);
		logger.debug("download void");
		try {
			String filePath = request.getParameter("filePath");
			String fileName = request.getParameter("fileName");
			String fileSize = request.getParameter("fileSize");
			if (fileSize == null) {
				fileSize = "0";
			}
			UploadHelper helper = new UploadHelper(null);

			// CemeteryFile file = cemeteryFileService.load(CemeteryFile.class,
			// id);
			URL url = Thread.currentThread().getContextClassLoader().getResource("../../" + filePath);

			if (url == null) {
				PrintWriter out = null;
				try {
					response.setCharacterEncoding("GBK");
					out = response.getWriter();
					out.print("<script>alert('文件不存在');history.go(-1);</script>");
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("下载文件异常!");
				} finally {
					if (out != null) {
						out.flush();
						out.close();
					}
				}
				return;
			}
			logger.debug("下载路径:" + url.toString());
			logger.debug("开始下载文件！fileName=" + fileName);
			logger.debug("Path=" + url.getPath());
			logger.debug("fileSize=" + fileSize);
			logger.debug("fileName=" + fileName);
			logger.debug("response=" + response.toString());
			String _filePath = URLDecoder.decode(url.getPath(), "utf-8");
			helper.download(_filePath, fileName, 0L, response);
			logger.debug("下载文件完成！url" + url.getPath());
			logger.debug("下载文件完成！fileName" + fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("下载文件异常", e);
		}

		return;
	}

	OrderFileVO getFileVO(MultipartFile multipartFile, String filePath) {
		OrderFileVO file = new OrderFileVO();
		file.setFileName(multipartFile.getOriginalFilename());
		file.setFileSize(multipartFile.getSize());
		file.setFilePath(filePath);
		return file;
	}

	/**
	 * 
	 * update by jerry.x 2016年11月23日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindOrderByProjectid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<OrderVO> list = null;
		int res = 0;
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		try {
			list = this.orderDbService.findOrderByProjectId(Long.parseLong(whereCause.getProjectId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "首页根据项目查询订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res <= 0) {
			super.ReturnAjaxResult(response, list, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, list, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindOrder4Index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<OrderVO> list = null;
		int res = 0;
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		try {
			list = this.orderDbService.findOrderForIndex(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "首页根据项目查询订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res <= 0) {
			super.ReturnAjaxResult(response, list, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, list, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindOrderSt4Index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<IndexOrderStVO> list = null;
		int res = 0;
		String returnMsgContent = "sucess";
		OrderVO whereCause = new OrderVO();
		bindObject(request, whereCause);
		try {
			list = this.orderDbService.findOrderSTForIndex(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "首页根据项目统计订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res <= 0) {
			super.ReturnAjaxResult(response, list, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, list, returnMsgContent, MessageType.info);
		}
	}
}

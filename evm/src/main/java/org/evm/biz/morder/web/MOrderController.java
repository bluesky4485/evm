package org.evm.biz.morder.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.morder.entity.MOrderFileVO;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.morder.service.IMOrderDbService;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.util.UploadHelper;
import org.evm.core.web.AbstractMultiController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class MOrderController extends AbstractMultiController {
	IMOrderDbService morderDbService;

	public void setMorderDbService(IMOrderDbService morderDbService) {
		this.morderDbService = morderDbService;
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
	public void ajaxInsertMorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = this.morderDbService.insertMOrder(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "保存维修订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res == -1) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}

	}

	public void ajaxUpdateMorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = this.morderDbService.updateMOrder(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "修改维修订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res == -1) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else if (res == 0) {
			returnMsgContent = "数据已旧，更新失败！";
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxDeleteMorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		try {
			res = this.morderDbService.deleteMOrder(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "删除维修订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res == -1) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else if (res == 0) {
			returnMsgContent = "数据已旧，删除失败！";
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindMorderPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 0;
		String returnMsgContent = "sucess";
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		PageResult pageResult = null;
		try {
			pageResult = morderDbService.findAllMorderPage(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询维修订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res == -1) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 进入修改页面 update by jerry.x 2016年10月26日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindMorderById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
		MOrderVO pageResult = null;
		try {
			pageResult = morderDbService.getMorderById(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询维修订单信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
	}

	/**
	 * 返回管理页 update by jerry.x 2016年10月26日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public ModelAndView returnManagePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("maintainOrderManage");
		return mv;
	}

	/**
	 * 页面跳转到更新页 update by jerry.x 2016年10月26日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public ModelAndView gotoUpdatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("updMaintainOrder");
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
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
		String morderId = request.getParameter("morderId");
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		logger.debug("调试上传文件的维修订单号=" + morderId);
		if (morderId == null || morderId == "") {
			morderId = sdf.format(new Date());
		}
		UploadHelper helper = new UploadHelper(request);
		List<MOrderFileVO> list = new ArrayList<MOrderFileVO>();
		// 上传文件
		List<MultipartFile> multipartFiles = helper.getFileMap(1024 * 1024 * 10, null);
		String revPath = "//" + IMOrderDbService.morderFileDic + morderId + "//";
		for (MultipartFile multipartFile : multipartFiles) {
			String filePath = helper.uploadFile(multipartFile, getFileServerPath(morderId));
			MOrderFileVO file = getFileVO(multipartFile, revPath + new File(filePath).getName());
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
		try {
			logger.debug("调试下载文件功能：download void");
			
			String filePath = request.getParameter("filePath");
			String fileName = request.getParameter("fileName");
			String fileSize = request.getParameter("fileSize");
			if (fileSize == null) {
				fileSize = "0";
			}
			UploadHelper helper = new UploadHelper(null);

			logger.debug("fileSize=" + fileSize);
			logger.debug("fileName=" + fileName);
			logger.debug("filePath=" + filePath);
			response.setCharacterEncoding("GBK");
			String _filePath = request.getSession().getServletContext().getRealPath("/") + filePath;
			helper.download(_filePath, fileName, 0L, response);
			logger.debug("下载文件完成！url" + _filePath);
			logger.debug("下载文件完成！fileName" + fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("下载文件异常", e);
		}

		return;
	}

	MOrderFileVO getFileVO(MultipartFile multipartFile, String filePath) {
		MOrderFileVO file = new MOrderFileVO();
		file.setFileName(multipartFile.getOriginalFilename());
		file.setFileSize(multipartFile.getSize());
		file.setFilePath(filePath);
		return file;
	}

	public String getFileServerPath(String morderId) {
		return getServletContext().getRealPath("/") + IMOrderDbService.morderFileDic + "//" + morderId + "//";
	}

}

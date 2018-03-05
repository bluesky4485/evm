package org.evm.biz.morder.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.morder.entity.MOrderFileVO;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.morder.service.IMOrderDbService;
import org.evm.biz.order.common.OrderNoBuilder;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.util.ExcelUtil;
import org.evm.core.util.UploadHelper;
import org.evm.core.web.AbstractMultiController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

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
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @version update by jerry 2018年1月10日
	 */
	public ModelAndView gotoCreatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("addMaintainOrder");
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

	@Override
	protected void doExcelWrite(ExcelUtil util, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String exportMethod = request.getParameter("exportMethod");
		ajaxExcelExport(util, request);
	}

	public void ajaxExcelExport(ExcelUtil util, HttpServletRequest request) throws Exception {
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);
		List<MOrderVO> pageResult = morderDbService.findAllMorderForExport(whereCause);

		String[] header = new String[] { "项目编号", "项目名称", "订单编号", "维修订单编号", "运维经理", "派修时间", "故障描述", "预约时间", "计划完成时间",
				"维修人数", "维修开始日期", "维修状态", "维修进度", "故障类别", "维修负责人", "验收状态", "验收回访人", "解决方案", "维修结果描述", "备注", "用户评分",
				"用户评价", "最后一次进度更新时间", "创建人", "创建日期", "修改人", "修改日期" };
		util.createHeader(header);
		for (int i = 0; i < pageResult.size(); i++) {
			MOrderVO po = pageResult.get(i);
			util.setValue(i + 1, 0, po.getProjectNo() == null ? "" : po.getProjectNo());
			util.setValue(i + 1, 1, po.getProjectName() == null ? "" : po.getProjectName());
			util.setValue(i + 1, 2, po.getOrderNo() == null ? "" : po.getOrderNo());
			util.setValue(i + 1, 3, po.getMorderNo() == null ? "" : po.getMorderNo());
			util.setValue(i + 1, 4, po.getPmName() == null ? "" : po.getPmName());
			//
			util.setValue(i + 1, 5, po.getMaintainDate() == null ? "" : po.getMaintainDate());
			util.setValue(i + 1, 6, po.getFaultDesc() == null ? "" : po.getFaultDesc());
			//
			util.setValue(i + 1, 7, po.getAppointmentDate() == null ? "" : po.getAppointmentDate());
			util.setValue(i + 1, 8, po.getPlanEndDate() == null ? "" : po.getPlanEndDate());
			util.setValue(i + 1, 9, po.getMworkerCnt() == null ? "" : po.getMworkerCnt());
			util.setValue(i + 1, 10, po.getMaintainBeginDate() == null ? "" : po.getMaintainBeginDate());
			util.setValue(i + 1, 11, po.getMaintainStatusDesc() == null ? "" : po.getMaintainStatusDesc());
			util.setValue(i + 1, 12, po.getMaintainSpeed() == null ? "" : po.getMaintainSpeed());
			util.setValue(i + 1, 13, po.getFaultTypeDesc() == null ? "" : po.getFaultTypeDesc());
			util.setValue(i + 1, 14, po.getMaintainPmName() == null ? "" : po.getMaintainPmName());

			util.setValue(i + 1, 15, po.getAcceptStatusDesc() == null ? "" : po.getAcceptStatusDesc());

			util.setValue(i + 1, 16, po.getAcceptecallMan() == null ? "" : po.getAcceptecallMan());
			util.setValue(i + 1, 17, po.getSolution() == null ? "" : po.getSolution());
			util.setValue(i + 1, 18, po.getMaintainResultdesc() == null ? "" : po.getMaintainResultdesc());
			util.setValue(i + 1, 19, po.getMaintainRemark() == null ? "" : po.getMaintainRemark());
			util.setValue(i + 1, 20, po.getUserScore() == null ? "" : po.getUserScore());
			util.setValue(i + 1, 21, po.getUserProposal() == null ? "" : po.getUserProposal());
			util.setValue(i + 1, 22, po.getLastUpdDate() == null ? "" : po.getLastUpdDate());
			util.setValue(i + 1, 23, po.getInsUser() == null ? "" : po.getInsUser());
			util.setValue(i + 1, 24, po.getInsDate() == null ? "" : po.getInsDate());
			util.setValue(i + 1, 25, po.getUpdUser() == null ? "" : po.getUpdUser());
			util.setValue(i + 1, 26, po.getUpdDate() == null ? "" : po.getUpdDate());
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version update by xh 2018年2月12日
	 */
	public ModelAndView fileUploadPage(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("fileUpload");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @version update by xh 2018年2月24日
	 */
	public void uploadExcelFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String morderId = request.getParameter("morderId");
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		logger.debug("调试上传文件的维修订单号=" + morderId);
		if (morderId == null || morderId == "") {
			morderId = sdf.format(new Date());
		}
		UploadHelper helper = new UploadHelper(request);
		ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
		// 上传文件
		List<MultipartFile> multipartFiles = helper.getFileMap(-1, null);
		String revPath = "//" + IMOrderDbService.morderFileDic + morderId + "//";
		for (MultipartFile multipartFile : multipartFiles) {
			String filePath = helper.uploadFile(multipartFile, getFileServerPath(morderId));
			// 文件已经读取了
			ExcelUtil excelUtil = new ExcelUtil();
			list = excelUtil.readExcel2007(new File(filePath));

		}
		if (list.size() < 1) {

		}
		List<MOrderVO> morderList = new ArrayList<>();
		List<String> itemUidList = new ArrayList<>();
		// TODO:i=0为标题行
		for (int i = 1; i < list.size(); i++) {
			MOrderVO vo = new MOrderVO();
			morderList.add(vo);
			for (int j = 0; j < list.get(i).size(); j++) {
				vo.setConvergeBoxNo(String.valueOf(list.get(i).get(0)));
			}
			itemUidList.add(String.valueOf(list.get(i).get(0)));
		}
		List<String> canFindUid = morderDbService.findDeviceItemByImportData(itemUidList);
		// TODO:校验输入的UID是否是库中存在的
		boolean checkResult = true;
		StringBuilder msgContent = new StringBuilder();
		if (canFindUid == null || canFindUid.size() == 0) {
			checkResult = false;
			for (String str : itemUidList) {
				msgContent.append(str).append(",");
			}
		} else {
			for (String str : itemUidList) {
				if (str != "" && !canFindUid.contains(str)) {
					msgContent.append(str).append(",");
					checkResult = false;
				}
			}
		}

		if (!checkResult) {
			String msg = "excel中有[" + msgContent.toString().substring(0, msgContent.length() - 1)
					+ "]未能找到相关设备，请核对导入信息!";
			ReturnAjaxMessage(response, msg, MessageType.error);
		} else {
			List<MOrderVO> previewData = this.morderDbService.findAllMorderForImport(morderList);
			ReturnAjaxResult(response, previewData);
		}
	}

	/**
	 * excel 导入
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @version update by xh 2018年2月21日
	 */
	public void ajaxBatchInsertMorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long res = 0;
		String returnMsgContent = "sucess";
		MOrderVO whereCause = new MOrderVO();
		bindObject(request, whereCause);

		List<MOrderVO> list = JSON.parseArray(whereCause.getExcelImportString(), MOrderVO.class);
		List<MOrderVO> insertList = new ArrayList<>();
		List<MOrderVO> updList = new ArrayList<>();
		for (MOrderVO vo : list) {
			vo.setInsUser(super.getLoginUserId(request));
			vo.setUpdUser(super.getLoginUserId(request));
			vo.setMorderNo(OrderNoBuilder.BuildMOrderNo());
			vo.setMaintainStatus("0");
			vo.setMaintainSpeed("0");
			vo.setMworkerCnt("0");
			vo.setUserScore("0");
			vo.setAcceptStatus("0");
			vo.setFaultDesc("设备【" + vo.getDeviceItemUid() + "】故障！");
			vo.setMaintainRemark("设备" + vo.getDeviceItemUid() + "掉电！");
			if (vo.getMorderId() == 0 || vo.getMorderNo() == "") {
				insertList.add(vo);
			} else {
				updList.add(vo);
			}
		}
		try {
			if (insertList.size() > 0) {
				res = this.morderDbService.batchInsertMOrder(insertList);
			}
			if (updList.size() > 0) {
				res = this.morderDbService.batchUpdateMorderFaultDescByImort(updList);
			}
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
}

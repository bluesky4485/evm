package org.evm.core.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.core.consts.EventType;
import org.evm.core.consts.MessageType;
import org.evm.core.consts.SystemConsts;
import org.evm.core.entity.AbstractEntity;
import org.evm.core.entity.ResponseResult;
import org.evm.core.log.DataCenterLogger;
import org.evm.core.util.CommonUtil;
import org.evm.core.util.ExcelUtil;
import org.evm.biz.user.entity.UserVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public abstract class AbstractMultiController extends MultiActionController {
	private String EVENT_SERVICENAME = "bizEventService";
	 
	protected DataCenterLogger logger = new DataCenterLogger(this.getClass());

	protected AbstractMultiController() {

	}

	/**
	 * 记录用户操作日志
	 */
	protected void logBizEvent(HttpServletRequest request, String eventContent, EventType eventType) {

	}

	/**
	 * 取当前登录用户信息
	 * 
	 * @param request
	 * @return
	 */
	protected UserVO getCurrentLoginVO(HttpServletRequest request) {
		UserVO loginUser = (UserVO) request.getSession().getAttribute(SystemConsts.LOGIN_INFO);
		return loginUser;
	}

	protected String getLoginUserId(HttpServletRequest request) throws Exception {
		UserVO loginVO = this.getCurrentLoginVO(request);
		if (loginVO != null)
			return String.valueOf(loginVO.getUid());
		else
			throw new Exception("未找到用户信息");
	}

	/**
	 * POJO绑定参数
	 * 
	 * @param request
	 * @param command
	 * @return
	 * @throws Exception
	 */
	protected BindingResult bindObject(HttpServletRequest request, Object command) throws Exception {
		ServletRequestDataBinder binder = createBinder(request, command);
		preBind(request, command, binder);
		binder.bind(request);
		Validator validators[] = getValidators();
		if (validators != null) {
			Validator validator = null;
			for (int i = 0; i < validators.length; i++) {
				validator = validators[i];
				if (validator.supports(command.getClass()))
					ValidationUtils.invokeValidator(validator, command, binder.getBindingResult());
			}

		}
		return binder.getBindingResult();
	}

	protected void bindPageinfo(HttpServletRequest request, Object command) {
		if (command instanceof AbstractEntity) {
			AbstractEntity para = (AbstractEntity) command;
			try {
				para.setUpdUser(this.getLoginUserId(request));
				int pageScope[] = CommonUtil.calculatePage(para.getPageSize(), para.getPageNum());
				para.setStartPageNum(pageScope[0]);
				para.setEndPageNum(pageScope[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// BizLoginVO loginVO = this.getCurrentLoginVO(request);
			// ((AbstractEntity) command).setInsUserId(loginVO.getUserId());
			// ((AbstractEntity) command).setUdpUserId(loginVO.getUserId());
		}
	}

	protected void preBind(HttpServletRequest request, Object command, ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
	}

	/**
	 * 返回有消息的结果
	 * 
	 * @param response
	 * @param bizData
	 */
	public void ReturnAjaxResult(HttpServletResponse response, Object bizData, String messageContent,
			MessageType messageType) {
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		PrintWriter printWriter = null;
		try {
			ResponseResult out = new ResponseResult();
			out.setBizData(bizData);
			out.setMessage(messageContent);
			out.setMessageType(messageType.toString());

			String json = JSON.toJSONString(out, true);
			printWriter = response.getWriter();
			printWriter.write(json);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	/**
	 * 返回无消息的结果
	 * 
	 * @param response
	 * @param bizData
	 */
	public void ReturnAjaxResult(HttpServletResponse response, Object bizData) {
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		PrintWriter printWriter = null;
		try {
			ResponseResult out = new ResponseResult();
			out.setBizData(bizData);
			String json = JSON.toJSONString(out, true);
			// logger.info(json);
			printWriter = response.getWriter();
			printWriter.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	/**
	 * 返回无消息的结果
	 * 
	 * @param response
	 * @param bizData
	 */
	public void ReturnAjaxResult(HttpServletResponse response, Object bizData, Class clazz, String[] filterField) {
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		PrintWriter printWriter = null;
		try {
			ResponseResult out = new ResponseResult();
			out.setBizData(bizData);
			String json = JSON.toJSONString(out, true);
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz, filterField);
			json = JSON.toJSONString(out, filter);
			// logger.info(json);
			printWriter = response.getWriter();
			printWriter.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	/**
	 * 返回服务端消息
	 * 
	 * @param response
	 * @param bizData
	 */
	public void ReturnAjaxMessage(HttpServletResponse response, String messageContent, MessageType messageType) {
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		PrintWriter printWriter = null;
		try {
			ResponseResult out = new ResponseResult();
			out.setMessage(messageContent);
			out.setMessageType(messageType.toString());
			String json = JSON.toJSONString(out, true);
			printWriter = response.getWriter();
			printWriter.write(json);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	public void ReturnResultJSONData(HttpServletResponse response, Object bizData) {
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		PrintWriter printWriter = null;
		try {

			String json = JSON.toJSONString(bizData, true);
			printWriter = response.getWriter();
			printWriter.write(json);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	/**
	 * 取得Ip
	 * 
	 * @param request
	 * @return
	 */
	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获得请求URI 型如：http://localhost:8080://qmGmamp//
	 * 
	 * @param request
	 * @return
	 */
	protected String getRequestUri(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	}
	public void ajaxExcelExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExcelUtil util = new ExcelUtil();
		try {
			doExcelWrite(util, request, response);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("设置EXCEL内容异常", e);
		}
		String fileName = util.getFileName();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(fileName).toString());
		ServletOutputStream ouputStream = response.getOutputStream();
		util.write(ouputStream);
	}

	/**
	 * 数据写入excel
	 * 
	 * @param util
	 * @param reques
	 * @param response
	 * @throws Exception
	 */
	protected void doExcelWrite(ExcelUtil util, HttpServletRequest reques, HttpServletResponse response) throws Exception {

	}
}

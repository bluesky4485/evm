package org.evm.biz.project.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.service.IProjectDbService;
import org.evm.core.consts.MessageType;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.web.AbstractMultiController;
import org.springframework.web.servlet.ModelAndView;

public class ProjectController extends AbstractMultiController {
	IProjectDbService projectDbService;

	public void setProjectDbService(IProjectDbService projectDbService) {
		this.projectDbService = projectDbService;
	}

	/**
	 * 录入项目 update by jerry.x 2016年10月18日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public void ajaxInsertProject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = 1;
		String returnMsgContent = "sucess";
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);
		whereCause.setInsUser(super.getLoginUserId(request));
		whereCause.setUpdUser(super.getLoginUserId(request));
		boolean check = checkProjectNo(whereCause.getProjectNo());
		if (!check) {
			super.ReturnAjaxResult(response, res, "已经存在合同编号或项目编号为【" + whereCause.getProjectNo() + "】的项目!", MessageType.error);
			return;
		}
		try {

			this.projectDbService.insertProject(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "创建项目信息异常！" + e.getMessage();
			res = -1;
			logger.error(returnMsgContent, e);
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 更新 update by jerry.x 2016年10月20日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxUpdateProject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);
		whereCause.setUpdUser(super.getLoginUserId(request));
		int res = 0;
		try {
			res = this.projectDbService.updateProject(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res = -1;
			returnMsgContent = "修改项目信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxDeleteProject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);
		whereCause.setUpdUser(super.getLoginUserId(request));
		int res = 0;
		try {
			res = this.projectDbService.deleteProjectById(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res = -1;
			returnMsgContent = "删除项目信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, res, returnMsgContent, MessageType.info);
		}
	}

	/**
	 * 查询项目 update by jerry.x 2016年10月20日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindProjectPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);
		bindPageinfo(request, whereCause);
		PageResult pageResult = null;
		try {
			pageResult = projectDbService.findProjectPage(whereCause);
		} catch (SmartFunctionException e1) {
			returnMsgContent = e1.getMessage();
			res = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询项目信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
			res = -1;
		}
		if (res < 0) {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.error);
		} else {
			super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
		}
	}

	public void ajaxFindProjectById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);

		ProjectVO pageResult = null;
		try {
			pageResult = projectDbService.getProjectById(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMsgContent = "查询项目信息异常！" + e.getMessage();
			logger.error("ajaxFindProjectById" + returnMsgContent, e);
		}
		super.ReturnAjaxResult(response, pageResult, returnMsgContent, MessageType.info);
	}

	/**
	 * 进入更新页 update by jerry.x 2016年10月20日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public ModelAndView gotoUpdatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);

		ModelAndView mv = new ModelAndView("updProject");
		mv.addObject("updObj", whereCause);
		return mv;
	}

	/**
	 * 返回管理页 update by jerry.x 2016年10月20日
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	public ModelAndView returnCustomManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("projectManage");
		return mv;
	}

	/**
	 * 下来框数据 update by jerry.x 2016年10月30日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	public void ajaxFindProjectIDNO(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnMsgContent = "sucess";
		int res = 0;
		ProjectVO whereCause = new ProjectVO();
		bindObject(request, whereCause);
		whereCause.setUpdUser(super.getLoginUserId(request));
		List<ProjectVO> projects = null;
		try {
			projects = this.projectDbService.findAllProjectIdNo(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res = 2;
			returnMsgContent = "查询项目ID信息异常！" + e.getMessage();
			logger.error(returnMsgContent, e);
		}
		super.ReturnAjaxResult(response, projects, returnMsgContent, MessageType.info);
	}

	private boolean checkProjectNo(String projectNo) {
		ProjectVO whereCause = new ProjectVO();
		whereCause.setProjectNo(projectNo);
		List<ProjectVO> list = projectDbService.findProjectByProjectNo(whereCause);
		return list == null || list.size() <= 0 ? true : false;
	}
}

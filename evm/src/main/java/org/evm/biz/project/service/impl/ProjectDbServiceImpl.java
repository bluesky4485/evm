package org.evm.biz.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.evm.biz.device.entity.DeviceTypeVO;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.entity.RelProjectDeviceVO;
import org.evm.biz.project.entity.RelProjectUserVO;
import org.evm.biz.project.service.IProjectDbService;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;

import com.alibaba.fastjson.JSON;

public class ProjectDbServiceImpl extends BaseDBService implements IProjectDbService {
	@Override
	@SystemServiceLog(description = "创建项目信息", bizTypeId = BizOptType.add, functionId = FunctionMap.WEB_ADD)
	public int insertProject(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		int projectId = 0;
		try {
			projectId = (Integer) super.insert(MS_INSERT_PROJECT, whereCause);
			List<RelProjectDeviceVO> deviceList = JSON.parseArray(whereCause.getDeviceStr(), RelProjectDeviceVO.class);
			if (deviceList != null && deviceList.size() > 0) {
				whereCause.setDeviceList(deviceList);
				for (RelProjectDeviceVO device : whereCause.getDeviceList()) {
					device.setProjectId(projectId);
					device.setUpdUser(whereCause.getUpdUser());
					device.setInsUser(whereCause.getInsUser());
				}
				super.batchInsert(MS_INSERT_REL_PROJECT_DEVICE, deviceList);
			}
			// TODO:相关用户
			List<RelProjectUserVO> relUserList = null;
			String relUserStr = whereCause.getRelUserStr();
			if (relUserStr != null && !relUserStr.equals("")) {
				String[] userIds = relUserStr.split(",");
				if (userIds != null && userIds.length > 0) {
					relUserList = new ArrayList<RelProjectUserVO>();
					for (String id : userIds) {
						RelProjectUserVO uservo = new RelProjectUserVO();
						relUserList.add(uservo);
						uservo.setProjectId(projectId);
						uservo.setUserId(Long.parseLong(id));
					}
					super.batchInsert(MS_INSERT_REL_PROJECT_RELUSER, relUserList);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("创建项目信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("创建项目信息异常", e);
		}
		return projectId;
	}

	@Override
	@SystemServiceLog(description = "修改项目信息", bizTypeId = BizOptType.update, functionId = FunctionMap.WEB_UPDATE)
	public int updateProject(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = super.update(MS_UPDATE_PROJECT, whereCause);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("修改项目信息异常!参数：" + whereCause.toString(), e1);
			throw new SmartDBAccessException("修改项目信息异常", e1);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		try {
			super.delete(MS_DELETE_REL_PROJECT_DEVICE_BY_PROJECT_ID, whereCause.getProjectId());
			List<RelProjectDeviceVO> deviceList = JSON.parseArray(whereCause.getDeviceStr(), RelProjectDeviceVO.class);
			if (deviceList != null && deviceList.size() > 0) {
				whereCause.setDeviceList(deviceList);
				for (RelProjectDeviceVO device : whereCause.getDeviceList()) {
					device.setProjectId(whereCause.getProjectId());
					device.setUpdUser(whereCause.getUpdUser());
					device.setInsUser(whereCause.getInsUser());
				}
				super.batchInsert(MS_INSERT_REL_PROJECT_DEVICE, deviceList);
			}
			// TODO:相关用户
			long projectId = whereCause.getProjectId();
			super.delete(MS_DELETE_REL_PROJECT_RELUSER_BY_PDID, projectId);
			List<RelProjectUserVO> relUserList = null;
			String relUserStr = whereCause.getRelUserStr();
			if (relUserStr != null && !relUserStr.equals("")) {
				String[] userIds = relUserStr.split(",");
				if (userIds != null && userIds.length > 0) {
					relUserList = new ArrayList<RelProjectUserVO>();
					for (String id : userIds) {
						RelProjectUserVO uservo = new RelProjectUserVO();
						relUserList.add(uservo);
						uservo.setProjectId(projectId);
						uservo.setUserId(Long.parseLong(id));
					}
					super.batchInsert(MS_INSERT_REL_PROJECT_RELUSER, relUserList);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("修改项目中设备信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("修改项目中设备信息异常", e);
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "查询项目信息", bizTypeId = BizOptType.query, functionId = FunctionMap.WEB_QUERY)
	public PageResult findProjectPage(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<ProjectVO> rows = null;
		int total = super.getCount(MS_FIND_PROJECT_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_FIND_PROJECT_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	public ProjectVO getProjectById(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		ProjectVO res = (ProjectVO) super.get(MS_FIND_PROJECT_BYID, whereCause.getProjectId());
		return res;
	}

	@Override
	@SystemServiceLog(description = "删除项目信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.WEB_DELETE)
	public int deleteProjectById(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = super.update(MS_DEL_RPOJECT_BYID, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("删除项目信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("删除项目信息异常", e);
		}
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		return res;
	}

	@Override
	public List<ProjectVO> findAllProjectIdNo(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_FIND_ALL_RPOJECT_IDNO, whereCause);
	}

	@Override
	public List<RelProjectDeviceVO> findRelProjectDeviceByProjectId(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_FIND_REL_PROJECT_DEVICE_BY_PROJECT_ID, whereCause);
	}

	@Override
	public int updateProjectWorkStartDate(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_WORK_START_DATE, whereCause);
	}

	@Override
	public int addRelProjectDevice(RelProjectDeviceVO whereCause) {
		// TODO Auto-generated method stub
		ProjectVO para=new ProjectVO();
		para.setProjectId(whereCause.getProjectId());
		para.setUpdDate(whereCause.getUpdDate());
		para.setInsUser(whereCause.getInsUser());
		para.setUpdUser(whereCause.getUpdUser());
		int res = super.update(MS_UPDATE_UPD_DATE, para);
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		return (Integer) super.insert(MS_INSERT_REL_PROJECT_DEVICE, whereCause);
	}

	@Override
	public int deleteRelProjectDeviceById(RelProjectDeviceVO whereCause) {
		// TODO Auto-generated method stub
		ProjectVO para=new ProjectVO();
		para.setProjectId(whereCause.getProjectId());
		para.setUpdDate(whereCause.getUpdDate());
		para.setInsUser(whereCause.getInsUser());
		para.setUpdUser(whereCause.getUpdUser());
		int res = super.update(MS_UPDATE_UPD_DATE, para);
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		return super.delete(MS_DELETE_REL_PROJECT_DEVICE_BY_PDID, whereCause.getPdId());
	}

	@Override
	public int updateRelProjectDeviceById(RelProjectDeviceVO whereCause) {
		// TODO Auto-generated method stub
		ProjectVO para=new ProjectVO();
		para.setProjectId(whereCause.getProjectId());
		para.setUpdDate(whereCause.getUpdDate());
		para.setInsUser(whereCause.getInsUser());
		para.setUpdUser(whereCause.getUpdUser());
		int res = super.update(MS_UPDATE_UPD_DATE, para);
		if (res == 0) {
			throw new SmartDBAccessException("数据已旧！");
		}
		return super.update(MS_UPDATE_REL_PROJECT_DEVICE_BY_PDID, whereCause);
	}

	@Override
	public List<RelProjectDeviceVO> findAllProjectDeviceTypeByOrder(OrderVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_FIND_PROJECT_DEVICE_AND_PROPERTY_BY_ORDER, whereCause);
	}

	@Override
	public List<RelProjectDeviceVO> findAllProjectDeviceTypeByProject(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_FIND_PROJECT_DEVICE_AND_PROPERTY_BY_PROJECT, whereCause);
	}

	@Override
	public List<ProjectVO> findProjectByProjectNo(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return super.find(MS_FIND_PROJECT_BY_PROJECTNO, whereCause);
	}
}

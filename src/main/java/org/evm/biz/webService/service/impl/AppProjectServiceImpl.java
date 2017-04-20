package org.evm.biz.webService.service.impl;

import java.util.List;

import org.evm.biz.device.entity.DeviceTypeVO;
import org.evm.biz.device.service.IDeviceTypeDbService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.entity.RelProjectDeviceVO;
import org.evm.biz.project.service.IProjectDbService;
import org.evm.biz.webService.service.IAppProjectService;

/**
 * 项目管理
 * 
 * @author jerry.x update 2016年10月31日 下午8:56:51
 */
public class AppProjectServiceImpl implements IAppProjectService {
	IProjectDbService projectDbService;
	IDeviceTypeDbService deviceTypeDbService;
	public void setProjectDbService(IProjectDbService projectDbService) {
		this.projectDbService = projectDbService;
	}

	public void setDeviceTypeDbService(IDeviceTypeDbService deviceTypeDbService) {
		this.deviceTypeDbService = deviceTypeDbService;
	}

	@Override
	@SystemServiceLog(description = "App查询项目", bizTypeId = BizOptType.query, functionId = FunctionMap.APP_QUERY)
	public ProjectVO getProjectVO(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return projectDbService.getProjectById(whereCause);
	}

	@Override
	public List<DeviceTypeVO> findDeviceTypeByProjectId(DeviceTypeVO whereCause) {
		// TODO Auto-generated method stub
		return deviceTypeDbService.findDeviceTypeAndPropertyByCond(whereCause);
	}

	@Override
	public int updateProjectWorkStartDate(ProjectVO whereCause) {
		// TODO Auto-generated method stub
		return projectDbService.updateProjectWorkStartDate(whereCause);
	}

	@Override
	public int addRelProjectDeviceVO(RelProjectDeviceVO whereCause) {
		// TODO Auto-generated method stub
		return projectDbService.addRelProjectDevice(whereCause);
	}

	@Override
	public int deleteRelProjectDeviceVO(RelProjectDeviceVO whereCause) {
		// TODO Auto-generated method stub
		return projectDbService.deleteRelProjectDeviceById(whereCause);
	}

	@Override
	public int updateRelProjectDeviceVOById(RelProjectDeviceVO whereCause) {
		// TODO Auto-generated method stub
		return projectDbService.updateRelProjectDeviceById(whereCause);
	}

}

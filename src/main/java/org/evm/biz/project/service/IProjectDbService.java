package org.evm.biz.project.service;

import java.util.List;

import org.evm.biz.device.entity.DeviceTypeVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.entity.RelProjectDeviceVO;
import org.evm.core.entity.PageResult;

/**
 * 
 * @author jerry.x update 2016年10月18日 上午9:46:07
 */
public interface IProjectDbService {

	final String MS_INSERT_PROJECT = "MS_INSERT_PROJECT";
	final String MS_FIND_PROJECT_PAGE = "MS_FIND_PROJECT_PAGE";
	final String MS_FIND_PROJECT_CNT = "MS_FIND_PROJECT_CNT";
	final String MS_UPDATE_PROJECT = "MS_UPDATE_PROJECT";
	final String MS_DEL_RPOJECT_BYID = "MS_DEL_RPOJECT_BYID";
	final String MS_FIND_PROJECT_BYID = "MS_FIND_PROJECT_BYID";
	final String MS_FIND_PROJECT_BY_PROJECTNO="MS_FIND_PROJECT_BY_PROJECTNO";
	// 查询所有项目
	final String MS_FIND_ALL_RPOJECT_IDNO = "MS_FIND_ALL_RPOJECT_IDNO";
	// 项目相关设备
	final String MS_FIND_REL_PROJECT_DEVICE_BY_PROJECT_ID = "MS_FIND_REL_PROJECT_DEVICE_BY_PROJECT_ID";
	final String MS_DELETE_REL_PROJECT_DEVICE_BY_PROJECT_ID = "MS_DELETE_REL_PROJECT_DEVICE_BY_PROJECT_ID";
	final String MS_INSERT_REL_PROJECT_DEVICE = "MS_INSERT_REL_PROJECT_DEVICE";

	// ------------APP----------------------------------------------------------------------------------------------------------
	final String MS_UPDATE_WORK_START_DATE = "MS_UPDATE_WORK_START_DATE";
	final String MS_DELETE_REL_PROJECT_DEVICE_BY_PDID = "MS_DELETE_REL_PROJECT_DEVICE_BY_PDID";
	final String MS_UPDATE_REL_PROJECT_DEVICE_BY_PDID = "MS_UPDATE_REL_PROJECT_DEVICE_BY_PDID";
	final String MS_UPDATE_UPD_DATE = "MS_UPDATE_UPD_DATE";
	final String MS_FIND_PROJECT_DEVICE_AND_PROPERTY_BY_ORDER = "MS_FIND_PROJECT_DEVICE_AND_PROPERTY_BY_ORDER";
	final String MS_FIND_PROJECT_DEVICE_AND_PROPERTY_BY_PROJECT = "MS_FIND_PROJECT_DEVICE_AND_PROPERTY_BY_PROJECT";
	
	// ------------REL_PROJECT_USER------------------------------------------------------------------------------------------
	final String MS_INSERT_REL_PROJECT_RELUSER = "MS_INSERT_REL_PROJECT_RELUSER";
	final String MS_DELETE_REL_PROJECT_RELUSER_BY_PDID = "MS_DELETE_REL_PROJECT_RELUSER_BY_PDID";
	final String MS_FIND_REL_PROJECT_USER_BY_PROJECT_ID = "MS_FIND_REL_PROJECT_USER_BY_PROJECT_ID";
    /**
     *根据项目编号查询项目
     *update by jerry.x 2016年11月18日
     * @param whereCause
     * @return
     */
	List<ProjectVO> findProjectByProjectNo(ProjectVO whereCause);
    
	/**
	 * 
	 * update by jerry.x 2016年10月18日
	 * 
	 * @param whereCause
	 *
	 */
	int insertProject(ProjectVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月18日
	 * 
	 * @param whereCause
	 *
	 */
	int updateProject(ProjectVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月19日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findProjectPage(ProjectVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月30日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	ProjectVO getProjectById(ProjectVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月30日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int deleteProjectById(ProjectVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月30日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	List<ProjectVO> findAllProjectIdNo(ProjectVO whereCause);

	/**
	 * 根据项目ID查询相关设备数量;
	 * 
	 * @param whereCause
	 * @return
	 */
	List<RelProjectDeviceVO> findRelProjectDeviceByProjectId(ProjectVO whereCause);

	/**
	 * app 修改工程开工时间。
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateProjectWorkStartDate(ProjectVO whereCause);

	/**
	 * 增加工程中某一订单的设备种类。
	 * 
	 * @param whereCause
	 * @return
	 */
	int addRelProjectDevice(RelProjectDeviceVO whereCause);

	/**
	 * 删除工程中某一订单的设备种类。
	 * 
	 * @param whereCause
	 * @return
	 */
	int deleteRelProjectDeviceById(RelProjectDeviceVO whereCause);

	/**
	 * 增加工程中某一订单的设备种类。
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateRelProjectDeviceById(RelProjectDeviceVO whereCause);

	/**
	 * 根据订单编号查询项目中的设备10025
	 * 
	 * @param projectId
	 * @return
	 */
	List<RelProjectDeviceVO> findAllProjectDeviceTypeByOrder(OrderVO whereCause);
	
	/**
	 * 根据订单编号查询项目中的设备10026
	 * 
	 * @param projectId
	 * @return
	 */
	List<RelProjectDeviceVO> findAllProjectDeviceTypeByProject(ProjectVO whereCause);
}

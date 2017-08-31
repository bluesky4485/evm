package org.evm.biz.webService.service;

import java.util.List;

import org.evm.biz.device.entity.DeviceTypeVO;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.entity.RelProjectDeviceVO;

/**
 * 项目管理
 * 
 * @author jerry.x update 2016年10月31日 下午8:56:51
 */
public interface IAppProjectService {
	/**
	 * 查询某一订单的工程信息100201  
	 * 参数：projectId 工程ID；
	 * @param para
	 * @return
	 */
	ProjectVO getProjectVO(ProjectVO whereCause);
	/**
	 * 查询某一工程预先设置的设备种类信息100206
	 * 参数：projectId工程ID；
	 * @param para
	 * @return
	 */
	List<DeviceTypeVO> findDeviceTypeByProjectId(DeviceTypeVO whereCause);
	/**
	 * 修改工程开工时间320206
	 * 参数：projectId工程ID；updDate:更新时间；workStartDate：开工日期
	 * 
	 * @param para
	 * @return
	 */
	int updateProjectWorkStartDate(ProjectVO whereCause);
	/**
	 * 增加工程中某一订单的设备种类。320208
	 * 参数：updDate：需要用project的UpdDate；projectId 项目ID；deviceId设备类型ID；deviceCnt数量
	 * @param para
	 * @return
	 */
    int addRelProjectDeviceVO(RelProjectDeviceVO whereCause);
    /**
     * 删除工程中某一订单的设备种类320209
     * 参数：updDate：需要用project的UpdDate；pdId 唯一ID
     * @param para
     * @return
     */
    int deleteRelProjectDeviceVO(RelProjectDeviceVO whereCause);
    /**
     * 修改工程中某一订单的设备的数量信息320210
     * 参数：updDate：需要用project的UpdDate；pdId 唯一ID；deviceId设备类型ID；deviceCnt数量
     * @param whereCause
     * @return
     */
    int updateRelProjectDeviceVOById(RelProjectDeviceVO whereCause);
}

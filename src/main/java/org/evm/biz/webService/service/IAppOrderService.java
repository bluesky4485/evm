package org.evm.biz.webService.service;

import java.util.List;

import org.evm.biz.order.entity.DeviceItemVO;
import org.evm.biz.order.entity.OrderDeviceVO;
import org.evm.biz.order.entity.OrderFileVO;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.project.entity.ProjectVO;
import org.evm.biz.project.entity.RelProjectDeviceVO;

/**
 * 订单管理
 * 
 * @author jerry.x update 2016年10月31日 下午8:57:05
 */
public interface IAppOrderService {
	/**
	 * 查询全部工程订单信息。100100
	 * 
	 * @param para
	 * @return
	 */
	List<OrderVO> findOrderAndMorder(OrderVO para);

	/**
	 * 查询全部工程订单信息。100200 参数：待定；pageNum：页号，pageSize 每页数量;strLike 模糊查询条件
	 * 问题：模糊查询，按什么？订单编号，合同（项目编号），项目名称，市场（项目）负责人
	 * 
	 * @param para
	 * @return
	 */
	List<OrderVO> findAllOrder(OrderVO para);

	/**
	 * 查询某一个工程订单的详细信息100202 参数：orderId
	 * 
	 * @param para
	 * @return
	 */
	OrderVO getOrder(OrderVO para);

	/**
	 * 100203 查询某一个工程订单的设备信息 参数：orderId
	 * 
	 * @param para
	 * @return
	 */
	List<OrderDeviceVO> findOrderDeviceVO(OrderDeviceVO para);

	/**
	 * 修改某一工程订单的评价信息。310205 参数： orderId 订单ID；userAppraise 用户评价userRating用户评分
	 * updDate 更新时间 uid 用户ID
	 * 
	 * @param para
	 * @return
	 */
	int updateOrderUserAppraise(OrderVO para);
	/**
	 * 修改工程订单的施工位置 310205 参数： orderId 订单ID；workAddress  lng lat施工位置
	 * @param para
	 * @return
	 * @version update by xh 2017年8月29日
	 */
	int updateOrderWorkAddress(OrderVO para);

	/**
	 * 查询某一个工程订单中的设备的属性信息。 100205
	 * 
	 * @param para
	 * @return
	 */
	List<DeviceItemVO> findDeviceItemListByOrder(DeviceItemVO para);

	/**
	 * 查询某一个工程订单中的设备的属性信息。100206
	 * 
	 * @param whereCause
	 * @return
	 */
	List<RelProjectDeviceVO> findDeviceItemListByProject(ProjectVO whereCause);

	/**
	 * 批量增加设备类型320208增加工程中某一订单的设备种类
	 * 
	 * @param whereCause
	 * @return
	 */
	int batchInsertOrderDeviceVO(List<OrderDeviceVO> whereCause);

	/**
	 * 320210修改工程中某一订单的设备的数量信息
	 * 
	 * @param whereCause
	 * @return
	 */
	int batchUpdateOrderDeviceVO(List<OrderDeviceVO> whereCause);

	/**
	 * 320209删除工程中某一订单的设备种类：
	 * 
	 * @param whereCause
	 * @return
	 */
	int batchDeleteOrderDeviceVO(List<OrderDeviceVO> whereCause);

	/**
	 * 320212增加设备
	 * 
	 * @param whereCause
	 * @return
	 */
	int addDeviceItem(DeviceItemVO whereCause);

	/**
	 *  320213删除设备
	 * 
	 * @param whereCause
	 * @return
	 */
	int deleteDeviceItem(DeviceItemVO whereCause);

	/**
	 * 320214修改设备
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateDeviceItem(DeviceItemVO whereCause);
	/**
	 * 320207	修改订单施工进度
	 * @param whereCause
	 * @return
	 */
	int updateOrderWorkProgress(OrderVO whereCause);
	/**
	 * 320206	修改订单中的开工时间的
	 * @param whereCause
	 * @return
	 */
	int updateOrderWorkBeginDate(OrderVO whereCause);
	/**
	 * 上传竣工资料
	 * @return
	 */
	long uploadOrderCompletImg(OrderFileVO whereCause);
	/**
	 * 上传施工资料
	 * @param whereCause
	 * @return
	 */
	long uploadOrderWorkImg(OrderFileVO whereCause);
	/**
	 * 删除施工资料
	 * @param whereCause
	 * @return
	 */
	int deleteOrderWorkImg(OrderFileVO whereCause);
	
}

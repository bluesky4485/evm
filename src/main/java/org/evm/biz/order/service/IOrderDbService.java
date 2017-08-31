package org.evm.biz.order.service;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.biz.order.entity.IndexOrderStVO;
import org.evm.biz.order.entity.OrderFileVO;
import org.evm.biz.order.entity.OrderVO;

public interface IOrderDbService {
	/**
	 * 运维订单的路径
	 */
	public final String orderFileDic="\\upload\\order\\";
	// 复合条件查询的订单
	final String MS_SELECT_ORDER_COND = "MS_SELECT_ORDER_COND";
	final String MS_SELECT_ORDER_IDNO_COND = "MS_SELECT_ORDER_IDNO_COND";
	final String MS_SELECT_ORDER_PAGE = "MS_SELECT_ORDER_PAGE";
	final String MS_SELECT_ORDER_PAGE_CNT = "MS_SELECT_ORDER_PAGE_CNT";
	final String MS_INSERT_ORDER = "MS_INSERT_ORDER";
	final String MS_UPDATE_ORDER = "MS_UPDATE_ORDER";
	final String MS_DELETE_ORDER = "MS_DELETE_ORDER";
	final String MS_GET_ORDER_BYID = "MS_GET_ORDER_BYID";
	final String MS_GET_ORDER_BY_ORDERNO = "MS_GET_ORDER_BY_ORDERNO";
	final String MS_SELECT_ORDER_BY_PROJECTID="MS_SELECT_ORDER_BY_PROJECTID";
	//--------------------APP-------------------------------------------------------------------------------------------
	final String MS_UPDATE_USERAPRRISE="MS_UPDATE_USERAPRRISE";
	final String MS_UPDATE_WORKPROGRESS="MS_UPDATE_WORKPROGRESS";
	final String MS_UPDATE_WORKBEGINDATE="MS_UPDATE_WORKBEGINDATE";
	final String MS_UPDATE_WORKADDRESS="MS_UPDATE_WORKADDRESS";
	final String MS_UPDATE_ORDER_UPD_DATE="MS_UPDATE_ORDER_UPD_DATE";
	//-----------------------device---------------------------------------------------------------------------------------
	final String  MS_INSERT_REL_ORDER_DEVICE="MS_INSERT_REL_ORDER_DEVICE";
	final String  MS_FIND_REL_ORDER_DEVICE_BY_ORDER_ID="MS_FIND_REL_ORDER_DEVICE_BY_ORDER_ID";
	final String  MS_DELETE_REL_ORDER_DEVICE_BY_ORDER_ID="MS_DELETE_REL_ORDER_DEVICE_BY_ORDER_ID";
	final String MS_DELETE_REL_ORDER_DEVICE_BY_RELID="MS_DELETE_REL_ORDER_DEVICE_BY_RELID";
	final String MS_UPDATE_REL_ORDER_DEVICE_BY_RELID="MS_UPDATE_REL_ORDER_DEVICE_BY_RELID";
	//-------------------------file----------------------------------------------------------------------------------------
	final String  MS_INSERT_REL_OREDER_FILE="MS_INSERT_REL_OREDER_FILE";
	final String  MS_FILE_ORDER_FILE_BY_ORDER_ID="MS_FILE_ORDER_FILE_BY_ORDER_ID";
	final String  MS_DELETE_ORDER_FILE_BY_ORDER_ID="MS_DELETE_ORDER_FILE_BY_ORDER_ID";
	final String  MS_DELETE_ORDER_FILE_BY_FILE_ID="MS_DELETE_ORDER_FILE_BY_FILE_ID";
	//--------------------device ITEM ------------------------------------------------------------------------------------
	final String MS_INSERT_DEVICEITEM="MS_INSERT_DEVICEITEM";
	//--------------------index ------------------------------------------------------------------------------------------
	final String MS_SELECT_ORDER_INDEX="MS_SELECT_ORDER_INDEX";
	final String MS_SELECT_ORDER_INDEX_ST="MS_SELECT_ORDER_INDEX_ST";
	
	
	/**
	 * 
	 * @param whereCause
	 * @return
	 */
	List<OrderVO> findOrderId(OrderVO whereCause);
	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	long insertOrder(OrderVO whereCause);
	/**
	 * 批量创建订单
	 * update by jerry.x 2016年11月7日 
	*@param whereCause
	*@return 
	 *
	 */
	long batchInsertOrder(List<OrderVO> whereCause);
	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	long updateOrder(OrderVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	long deleteOrder(OrderVO whereCause);

	/**
	 * 分页查询订单 update by jerry.x 2016年10月26日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findOrderPage(OrderVO whereCause);
	/**
	 * 
	 * update by jerry.x 2016年10月26日 
	*@param whereCause
	*@return 
	 *
	 */
	OrderVO getOrderByID(long whereCause);
	/**
	 * 
	 * update by jerry.x 2016年11月23日 
	*@param projectId
	*@return 
	 *
	 */
	List<OrderVO> findOrderByProjectId(long projectId);
	/**
	 * 手机端查询订单
	 * @param whereCause
	 * @return
	 */
	List<OrderVO> findOrderForApp(OrderVO whereCause);
	/**
	 * 首页查询订单
	 * @param whereCause
	 * @return
	 */
	List<OrderVO> findOrderForIndex(OrderVO whereCause);
	/**
	 * 首页订单统计
	 * @param whereCause
	 * @return
	 */
	List<IndexOrderStVO> findOrderSTForIndex(OrderVO whereCause);
	/**
	 * 更新订单用户评价
	 * @param whereCause
	 * @return
	 */
	int updateOrderUserAppraise(OrderVO whereCause);
	
	/**
	 * 更新施工位置
	 * @param whereCause
	 * @return
	 * @version update by xh 2017年8月29日
	 */
	int updateOrderWorkAddress(OrderVO whereCause);
	/**
	 * 根据订单编号查询订单 
	 * update by jerry.x 2017年02月06日
	 * @param orderNo
	 * @return
	 */
	OrderVO getOrderByOrderNo(String orderNo);
	
	/**
	 * 320207	这个也是修改订单施工进度
	 * @param whereCause
	 * @return
	 */
	int updateOrderWorkProgress(OrderVO whereCause);
	/**
	 * 320206	这个也是修改订单中的开工时间的
	 * @param whereCause
	 * @return
	 */
	int updateOrderWorkBeginDate(OrderVO whereCause);
	/**
	 * 存储文件
	 * @param whereCause
	 * @return
	 */
	long insertOrderFile(OrderFileVO whereCause);
	/**
	 * 删除文件
	 * @param whereCause
	 * @return
	 */
	int deleteOrderFile(OrderFileVO whereCause);
}

package org.evm.biz.morder.service;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.biz.morder.entity.MOrderFileVO;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.OrderVO;

public interface IMOrderDbService {
	public final String morderFileDic = "\\upload\\morder\\";
	// ---------------------------------------------------------------------------------------------------------------
	final String MS_FIND_MORDER_PAGE = "MS_FIND_MORDER_PAGE";
	final String MS_FIND_MORDER_CNT = "MS_FIND_MORDER_CNT";
	final String MS_INSERT_MORDER = "MS_INSERT_MORDER";
	final String MS_UPDATE_MORDER = "MS_UPDATE_MORDER";
	final String MS_DELETE_MORDER = "MS_DELETE_MORDER";
	final String MS_GET_MORDER_BYID = "MS_GET_MORDER_BYID";
	final String MS_FIND_MORDER_EXCEL_EXPORT = "MS_FIND_MORDER_EXCEL_EXPORT";
	//TODO:根据汇聚箱编号进行导入
	final String MS_FIND_MORDER_EXCEL_IMPORT="MS_FIND_MORDER_EXCEL_IMPORT";
	final String MS_FIND_DEVICEITEM_EXCEL_IMPORT="MS_FIND_DEVICEITEM_EXCEL_IMPORT";
	final String MS_UPDATE_MORDER_FALUTDESC_EXCEL_IMPORT="MS_UPDATE_MORDER_FALUTDESC_EXCEL_IMPORT";
	// -------APP-----------------------------------------------------------------------------------------------------
	final String MS_UPDATE_USERPROPOSAL = "MS_UPDATE_USERPROPOSAL";
	final String MS_UPDATE_MAINTAIN_BEGIN_DATE = "MS_UPDATE_MAINTAIN_BEGIN_DATE";
	final String MS_UPDATE_MAINTAIN_SPEED = "MS_UPDATE_MAINTAIN_SPEED";
	final String MS_UPDATE_FAULT_TYPE = "MS_UPDATE_FAULT_TYPE";
	final String MS_UPDATE_MAINTAIN_RESULTDESC = "MS_UPDATE_MAINTAIN_RESULTDESC";
	final String MS_DELETE_MORDER_UPD_DATE = "MS_DELETE_MORDER_UPD_DATE";
	final String MS_INSERT_REL_MOREDER_FILE = "MS_INSERT_REL_MOREDER_FILE";
	final String MS_UPDATE_MAINTAIN_UPDDATE = "MS_UPDATE_MAINTAIN_UPDDATE";
	final String MS_DELETE_MORDER_FILE_BY_FILE_ID = "MS_DELETE_MORDER_FILE_BY_FILE_ID";
	final String MS_UPDATE_WORDERCNT="MS_UPDATE_WORDERCNT";
	// -----------------------file==================================
	final String MS_DELETE_MORDER_FILE_BY_MORDER_ID = "MS_DELETE_MORDER_FILE_BY_MORDER_ID";

	/**
	 * 各种条件查询 update by jerry.x 2016年10月24日
	 * 
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	List<MOrderVO> findAllMorder(MOrderVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int insertMOrder(MOrderVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int updateMOrder(MOrderVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 *
	 */
	int deleteMOrder(MOrderVO whereCause);

	/**
	 * 分页查询 update by jerry.x 2016年10月24日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findAllMorderPage(MOrderVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月26日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	MOrderVO getMorderById(MOrderVO whereCause);

	/**
	 * APP 查询所有运维订单
	 * 
	 * @param whereCause
	 * @return
	 */
	List<MOrderVO> findAllMorderForApp(MOrderVO whereCause);

	/**
	 * APP修改某一维修/维护订单的评价信息。
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateUserProposal(MOrderVO whereCause);

	/**
	 * 修改维修开工时间。
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateMaintainBeginDate(MOrderVO whereCause);

	/**
	 * 修改维修进度。
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateMaintainSpeed(MOrderVO whereCause);

	/**
	 * 修改故障类型
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateFaultType(MOrderVO whereCause);

	/**
	 * 修改维修结果描述
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateMaintainResultdesc(MOrderVO whereCause);

	/**
	 * App上传图片
	 * 
	 * @param whereCause
	 * @return
	 */
	long insertMorderImg(MOrderFileVO whereCause);

	/**
	 * 根据文件ID删除
	 * 
	 * @param whereCause
	 * @return
	 */
	int deleteMorderImg(MOrderFileVO whereCause);
	/**
	 * 修改维修人数
	 * @param whereCause
	 * @return
	 */
	int updateMorderWorkerCnt(MOrderVO whereCause);
	/**
	 * excel导出的查询
	 * @param whereCause
	 * @return
	 * @version update by x 2018年1月18日
	 */
	List<MOrderVO> findAllMorderForExport(MOrderVO whereCause);
	/**
	 * excel导入预览查询
	 * @param whereCause
	 * @return
	 * @version update by xh 2018年2月23日
	 */
	List<MOrderVO> findAllMorderForImport(List<MOrderVO> whereCause);
	/**
	 * excel 导入批量插入
	 * @param whereCause
	 * @return
	 * @version update by xh 2018年2月24日
	 */
	long batchInsertMOrder(List<MOrderVO> whereCause);
	/**
	 * 校验导入的设备是否存在
	 * @param whereCause
	 * @return
	 * @version update by xh 2018年3月1日
	 */
	List<String> findDeviceItemByImportData(List<String> whereCause);
	/**
	 * 如果存在维修订单的更新描述
	 * @param whereCause
	 * @return
	 * @version update by xh 2018年3月1日
	 */
	long batchUpdateMorderFaultDescByImort(List<MOrderVO> whereCause);
 
}

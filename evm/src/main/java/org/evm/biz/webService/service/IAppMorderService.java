package org.evm.biz.webService.service;

import java.util.List;

import org.evm.biz.morder.entity.MOrderFileVO;
import org.evm.biz.morder.entity.MOrderVO;
import org.evm.biz.order.entity.OrderFileVO;

/**
 * 运维订单管理
 * 
 * @author jerry.x update 2016年10月31日 下午8:56:40
 */
public interface IAppMorderService {
	/**
	 * 
	 * @param whereCause
	 * @return
	 */
	MOrderVO getLastMoarder(MOrderVO whereCause);
	/**
	 * 查询全部维修/维护订单信息。100300
	 * 参数：待定；pageNum：页号，pageSize 每页数量
	 * 问题：模糊查询，按什么？
	 * @param para
	 * @return
	 */
	List<MOrderVO> findAllMorderList(MOrderVO para);
	/**
	 * 查询某一个维修/维护订单的详细信息。100302
	 * 参数：morderId运维订单ID
	 * @param para
	 * @return
	 */
	MOrderVO getMOrderVO(MOrderVO para);
	/**
	 * 修改某一维修订单的评价信息。310305
	 * 参数：morderId运维订单ID;userProposal用户评价userScore用户评分updDate;更新时间
	 * @param para
	 * @return
	 */
	int updateUserProposal(MOrderVO para);
	/**
	 * 修改维修开工时间。330306
	 *  参数：morderId 运维订单ID；maintainBeginDate 维修开始日期；updDate 更新时间
	 * @param para
	 * @return
	 */
	int updateMaintainBeginDate(MOrderVO para);
	/**
	 * 修改维修进度。330307
	 * 参数：morderId 运维订单ID；maintainSpeed 维修进度；updDate 更新时间
	 * @param para
	 * @return
	 */
	int updateMaintainSpeed(MOrderVO para);
	 /**
	  * 修改维修结果描述330309
	  * 参数：morderId 运维订单ID；maintainResultdesc 维修结果描述；updDate 更新时间
	  * @param para
	  * @return
	  */
	int updateMaintainResultdesc(MOrderVO para);
	/**
	 * 修改故障类型330308
	 *参数：morderId 运维订单ID； faultType;故障类别；updDate 更新时间
	 * @param para
	 * @return
	 */
	int updateFaultType(MOrderVO para);
	/**
	 * 上传维修资料
	 * @param whereCause
	 * @return
	 */
	long uploadMOrderWorkImg(MOrderFileVO whereCause);
	/**
	 * 删除运维图片
	 * @param whereCause
	 * @return
	 */
	int deleteMOrderWorkImg(MOrderFileVO whereCause);
	/**
	 * 修改维修人数
	 * @param para
	 * @return
	 */
	int updateMorderWorkerCnt(MOrderVO para);
}

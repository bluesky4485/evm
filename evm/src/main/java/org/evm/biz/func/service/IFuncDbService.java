package org.evm.biz.func.service;

import java.util.List;

import org.evm.biz.func.entity.AppServiceVO;
import org.evm.biz.func.entity.FuncVO;
import org.evm.core.entity.PageResult;

public interface IFuncDbService {
	final String MS_SQL_FIND_FUNC_PAGE = "MS_SQL_FIND_FUNC_PAGE";
	final String MS_SQL_FIND_FUNC_PAGE_CNT = "MS_SQL_FIND_FUNC_PAGE_CNT";
	// ---------------------------------------------------------------------------------------------------------
	final String MS_SQL_FIND_FUNC_BY_UNAME = "MS_SQL_FIND_FUNC_BY_UNAME";
	final String MS_SQL_FIND_FUNC_BY_UID_APP = "MS_SQL_FIND_FUNC_BY_UID_APP";
//------------------------------------------------------------------------
	final String MS_SQL_FIND_SERVICE_FUNCID="MS_SQL_FIND_SERVICE_FUNCID";
	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findFuncPage(FuncVO whereCause);

	/**
	 * 判断用户是否有登录权限
	 * 
	 * @param uname
	 * @param upwd
	 * @return
	 */
	List<FuncVO> getConnectFuncByUName(String systemID, String uname);

	/**
	 * 
	 * @param serviceId
	 * @param uid
	 * @return
	 */
	List<FuncVO> getFuncByUidForApp(String serviceId, String uid);

	/**
	 *查询 权限和服务ID的关系
	 * @param whereCause
	 * @return
	 */
	List<AppServiceVO> getServiceFuncVO(AppServiceVO whereCause);
}

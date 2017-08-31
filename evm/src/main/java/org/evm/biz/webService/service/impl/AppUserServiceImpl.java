package org.evm.biz.webService.service.impl;

import java.util.List;

import org.evm.biz.func.entity.FuncVO;
import org.evm.biz.func.service.IFuncDbService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.order.service.IOrderDbService;
import org.evm.biz.phoneapp.service.IAppDbService;
import org.evm.biz.user.entity.UserVO;
import org.evm.biz.user.service.ILoginUserService;
import org.evm.biz.user.service.IUserDbService;
import org.evm.biz.webService.entity.WsEntity;
import org.evm.biz.webService.service.IAppUserService;

public class AppUserServiceImpl implements IAppUserService {
	/**
	 * 用户增删改查服务
	 */
	IUserDbService userDbService;
	/**
	 * 用户登录服务
	 */
	ILoginUserService loginUserService;
	/**
	 * 项目统计
	 */
	IAppDbService appDbService;
	/**
	 * 订单统计
	 */
	IOrderDbService orderDbService;

	IFuncDbService funcDbService;

	public void setOrderDbService(IOrderDbService orderDbService) {
		this.orderDbService = orderDbService;
	}

	public void setUserDbService(IUserDbService userDbService) {
		this.userDbService = userDbService;
	}

	public void setLoginUserService(ILoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}

	public void setAppDbService(IAppDbService appDbService) {
		this.appDbService = appDbService;
	}

	public void setFuncDbService(IFuncDbService funcDbService) {
		this.funcDbService = funcDbService;
	}

	/**
	 * 登录 update by jerry.x 2016年10月31日
	 * 
	 * @param userName
	 * @param pwd
	 * @return
	 *
	 */
	@Override
	// @SystemServiceLog(description = "APP用户登录", bizTypeId = BizOptType.update,
	// functionId = FunctionMap.APP_UPDPWD)
	public UserVO login(UserVO paraEntity) {
		UserVO logvo = loginUserService.loginValidateApp(paraEntity.getUname(), paraEntity.getUpwd());

		if (logvo == null) {
			return null;
		} else {
			logvo = userDbService.getUserById(logvo.getUid());
			logvo.setAppDataStVO(appDbService.getAppDataStVO(logvo.getUid()));
			List<OrderVO> appOrderList = null;
			OrderVO whereCause = new OrderVO();
			if (paraEntity.getPageNum() == 0 || paraEntity.getPageSize() == 0) {
				whereCause.setPageSize(10);
				whereCause.setPageNum(1);
			} else {
				whereCause.setPageNum(paraEntity.getPageNum());
				whereCause.setPageSize(paraEntity.getPageSize());
			}
			whereCause.setInsUser(String.valueOf(logvo.getUid()));
			if (paraEntity.getSystemId() == 100) {
				appOrderList = appDbService.findAppOrderByProjectUser(whereCause);
			} else if (paraEntity.getSystemId() == 200) {
				appOrderList = appDbService.findAppOrder(whereCause);
			} else if (paraEntity.getSystemId() == 300) {
				appOrderList = appDbService.findAppMOrder(whereCause);
			}
			logvo.setAppOrderList(appOrderList);
			return logvo;
		}
	}

	@Override
	@SystemServiceLog(description = "APP更新密码", bizTypeId = BizOptType.update, functionId = FunctionMap.APP_UPDPWD)
	public int updateUserPwd(UserVO whereCause) {
		// TODO Auto-generated method stub
		return this.userDbService.updateUserPwd(whereCause);
	}

	@Override
	public boolean canConnect(String systemId, String uname) {
		// TODO Auto-generated method stub
		boolean res = false;
		List<FuncVO> funcList = funcDbService.getConnectFuncByUName(systemId, uname);
		if (funcList == null) {
			return false;
		} else {
			return funcList.size() > 0 ? true : false;
		}
	}

	@Override
	public boolean hasFuncForApp(WsEntity entity) {
		// TODO Auto-generated method stub
		List<FuncVO> list = this.funcDbService.getFuncByUidForApp(entity.getServiceId(), entity.getUid());
		if (list == null) {
			return false;
		} else {
			// 未查询到任何权限
			return list.size() > 0 ? true : false;
		}

	}
}

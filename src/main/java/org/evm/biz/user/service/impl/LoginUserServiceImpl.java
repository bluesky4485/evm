package org.evm.biz.user.service.impl;

import java.util.List;

import org.evm.core.consts.EventType;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;
import org.evm.biz.func.entity.FuncVO;
import org.evm.biz.func.service.IFuncDbService;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.log.entity.LogVO;
import org.evm.biz.log.service.ILogDbService;
import org.evm.biz.user.entity.UserVO;
import org.evm.biz.user.service.ILoginUserService;
import org.evm.biz.user.service.IUserDbService;

/**
 * 
 * @author jerry.x update 2016年10月14日 下午12:23:54
 */
public class LoginUserServiceImpl extends BaseDBService implements ILoginUserService {
	private IUserDbService userDbService;
	private ILogDbService logDbService;
	private IFuncDbService funcDbService;

	public void setUserDbService(IUserDbService userDbService) {
		this.userDbService = userDbService;
	}

	public void setLogDbService(ILogDbService logDbService) {
		this.logDbService = logDbService;
	}

	public void setFuncDbService(IFuncDbService funcDbService) {
		this.funcDbService = funcDbService;
	}

	@Override
	/**
	 * 验证用户登录 update by jerry.x 2016年10月10日
	 * 
	 * @param userName
	 * @param passwd
	 * @return
	 *
	 */
	public UserVO loginValidate(String userName, String passwd) {
		// TODO Auto-generated method stub
		UserVO result = null;
		UserVO whereCause = new UserVO();
		whereCause.setUname(userName);
		whereCause.setUpwd(passwd);
		try {
			result = (UserVO) super.get("MS_SQL_USER_LOGIN", whereCause);
			if (result != null) {
				LogVO logvo = new LogVO();
				logvo.setLogContent("用户登录");
				logvo.setInsUser(result.getUid() + "");
				logvo.setLogBizId(Long.parseLong(FunctionMap.WEB_CONNECT));
				logvo.setLogBizType(BizOptType.query);
				logvo.setLogPara("登录名:" + userName);
				logDbService.insertLog(logvo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("用户登录异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("用户登录异常！", e);
		}

		return result;
	}

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
	public UserVO loginValidateApp(String userName, String passwd) {

		UserVO result = null;
		UserVO whereCause = new UserVO();
		whereCause.setUname(userName);
		whereCause.setUpwd(passwd);
		try {
			result = (UserVO) super.get("MS_SQL_USER_LOGIN", whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("APP用户登录异常!参数：" + whereCause.toString(), e);
		}
		return result;
	}
}

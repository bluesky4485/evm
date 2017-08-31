package org.evm.biz.webService.service;

import org.evm.biz.user.entity.UserVO;
import org.evm.biz.webService.entity.WsEntity;

/**
 * 用户接口
 * 
 * @author jerry.x update 2016年10月31日 下午8:55:11
 */
public interface IAppUserService {
	/**
	 * 登录 update by jerry.x 2016年10月31日
	 * 用户登录300001
	 * 参数：uname：登录名；upwd：登录密码
	 * 
	 * @param userName 
	 * @param pwd
	 * @return
	 *
	 */
	UserVO login(UserVO paraEntity);

	/**
	 * 用户密码修改300002
	 * 参数： uid 用户id，upwd 原密码；newPwd 新密码 ；updDate登录时带过去的更新时间
	 * @param uid
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	int updateUserPwd(UserVO whereCause);
	
	boolean  canConnect(String systemId,String uname);
	/**
	 * 权限校验()
	 * @param entity
	 * @return
	 */
	 boolean hasFuncForApp(WsEntity entity);
}

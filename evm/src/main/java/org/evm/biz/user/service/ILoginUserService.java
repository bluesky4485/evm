package org.evm.biz.user.service;

import org.evm.biz.user.entity.UserVO;

public interface ILoginUserService {
	static String CURRENT_USER = "current_user_key";

	/**
	 * 验证用户登录 update by jerry.x 2016年10月10日
	 * 
	 * @param userName
	 * @param passwd
	 * @return
	 *
	 */
	UserVO loginValidate(String userName, String passwd);
	/**
	 * 手机APP登录
	 * @param userName
	 * @param passwd
	 * @return
	 */
	UserVO loginValidateApp(String userName, String passwd);
	/**
	 * 
	 * @param systemId
	 * @param uname
	 * @return
	 */
	boolean canConnect(String systemId, String uname);
}

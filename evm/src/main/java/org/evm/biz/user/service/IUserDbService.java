package org.evm.biz.user.service;

import java.util.List;

import org.evm.core.entity.PageResult;
import org.evm.biz.user.entity.UserVO;

public interface IUserDbService {
	final String MS_FIND_USER_COND = "MS_FIND_USER_COND";
	final String MS_INSERT_USER = "MS_INSERT_USER";
	final String MS_UPD_USER = "MS_UPDATE_USER";
	final String MS_FIND_USER_PAGE = "MS_FIND_USER_PAGE";
	final String MS_FIND_USER_CNT = "MS_FIND_USER_CNT";
	final String MS_DELETE_USER = "MS_DELETE_USER";
	final String MS_FIND_USER_BYID = "MS_FIND_USER_BYID";
	// --------------------------------------------------------------
	final String MS_INSERT_USER_ROLE_REL = "MS_INSERT_USER_ROLE_REL";
	final String MS_DELETE_USER_ROLE_REL_BY_UID = "MS_DELETE_USER_ROLE_REL_BY_UID";

	// --------------APP 更新用户密码------------------------------------------------
	final String MS_UPDATE_USER_PWD = "MS_UPDATE_USER_PWD";

	/**
	 * 
	 * update by jerry.x 2016年10月14日
	 *
	 */
	int insertUser(UserVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月14日
	 * 
	 * @return
	 *
	 */
	int updateUser(UserVO whereCause);

	/**
	 * lhy
	 * 
	 * @param whereCause
	 * @return
	 */
	int deleteUser(UserVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年10月14日
	 * 
	 * @return
	 *
	 */
	List<UserVO> findUsers(UserVO whereCause);

	/**
	 * 分页查询用户 update by jerry.x 2016年10月18日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findUsersPage(UserVO whereCause);

	/**
	 * 
	 * @param uid
	 * @return
	 */
	UserVO getUserById(long uid);

	/**
	 * 
	 * update by jerry.x 2016年11月10日
	 * 
	 * @param name
	 * @return
	 *
	 */
	UserVO getUserByName(String name);

	/**
	 * 更新用户密码
	 * 
	 * @param whereCause
	 * @return
	 */
	int updateUserPwd(UserVO whereCause);
}

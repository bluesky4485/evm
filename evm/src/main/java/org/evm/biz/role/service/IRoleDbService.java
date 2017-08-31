package org.evm.biz.role.service;

import java.util.List;

import org.evm.biz.role.entity.RoleVO;
import org.evm.core.entity.PageResult;

public interface IRoleDbService {
	final String MS_SQL_FIND_ROLE_PAGE = "MS_SQL_FIND_ROLE_PAGE";
	final String MS_SQL_FIND_ROLE_PAGE_CNT = "MS_SQL_FIND_ROLE_PAGE_CNT";
	final String MS_SQL_INSERT_ROLE = "MS_SQL_INSERT_ROLE";
	final String MS_SQL_UPDATE_ROLE = "MS_SQL_UPDATE_ROLE";
	final String MS_SQL_DELETE_ROLE = "MS_SQL_DELETE_ROLE";
	final String MS_SQL_FIND_ROLE_BY_ROLEID = "MS_SQL_FIND_ROLE_BY_ROLEID";
	final String MS_SQL_FIND_ROLE_BY_ROLENAME = "MS_SQL_FIND_ROLE_BY_ROLENAME";
	final String MS_SQL_FIND_ROLE_ALL="MS_SQL_FIND_ROLE_ALL";
	// role func
	final String MS_SQL_INSERT_REL_ROLE_FUNC = "MS_SQL_INSERT_REL_ROLE_FUNC";
	final String MS_SQL_DELETE_REL_ROLE_FUNC = "MS_SQL_DELETE_REL_ROLE_FUNC";
	final String MS_SQL_FIND_REL_ROLE_FUNC_BY_ROLEID="MS_SQL_FIND_REL_ROLE_FUNC_BY_ROLEID";

	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int insertRole(RoleVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int updateRole(RoleVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	int deleteRole(RoleVO whereCause);

	/**
	 * 
	 * update by jerry.x 2016年11月14日
	 * 
	 * @param whereCause
	 * @return
	 *
	 */
	PageResult findRolePage(RoleVO whereCause);
	/**
	 * 
	 * update by jerry.x 2016年11月17日 
	*@param whereCause
	*@return 
	 *
	 */
	RoleVO getRoleById(RoleVO whereCause);
	/**
	 * 
	 * update by jerry.x 2016年11月17日 
	*@param whereCause
	*@return 
	 *
	 */
	RoleVO getRoleByName(String roleName);
	/**
	 * 
	 * update by jerry.x 2016年11月18日 
	*@return 
	 *
	 */
	List<RoleVO>  findAllRoleIdName();
}

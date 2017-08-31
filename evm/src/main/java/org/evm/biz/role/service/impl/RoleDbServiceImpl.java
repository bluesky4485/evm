package org.evm.biz.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.evm.biz.func.entity.FuncVO;
import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.role.entity.RelRoleFuncVO;
import org.evm.biz.role.entity.RoleVO;
import org.evm.biz.role.service.IRoleDbService;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.service.BaseDBService;

import com.alibaba.fastjson.JSON;

public class RoleDbServiceImpl extends BaseDBService implements IRoleDbService {

	@Override
	@SystemServiceLog(description = "插入角色信息", bizTypeId = BizOptType.add, functionId = FunctionMap.MIS_ADD)
	public int insertRole(RoleVO whereCause) {
		// TODO Auto-generated method stub
		RoleVO roleVo = this.getRoleByName(whereCause.getRoleName());
		if (roleVo != null) {
			throw new SmartDBAccessException("角色名称已经存在!");
		}
		int roleId = 0;
		try {
			List<RelRoleFuncVO> rellist = new ArrayList<RelRoleFuncVO>();
			List<FuncVO> list = null;
			if (!whereCause.getFuncStr().equals("")) {
				list = JSON.parseArray(whereCause.getFuncStr(), FuncVO.class);
			}
			roleId = (Integer) super.insert(MS_SQL_INSERT_ROLE, whereCause);
			if (list != null && list.size() > 0) {
				for (FuncVO fun : list) {
					RelRoleFuncVO rf = new RelRoleFuncVO();
					rf.setRoleId(roleId);
					rf.setFuncId(fun.getFuncId());
					rellist.add(rf);
				}
				super.batchInsert(MS_SQL_INSERT_REL_ROLE_FUNC, rellist);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SmartDBAccessException("存储角色异常!");
		}
		return roleId;
	}

	@Override
	@SystemServiceLog(description = "更新角色信息", bizTypeId = BizOptType.update, functionId = FunctionMap.MIS_UPDATE)
	public int updateRole(RoleVO whereCause) {
		// TODO Auto-generated method stub
		// return (Integer) super.update(MS_SQL_UPDATE_ROLE, whereCause);
		int res = 0;
		try {
			List<RelRoleFuncVO> rellist = new ArrayList<RelRoleFuncVO>();
			List<FuncVO> list = null;
			if (!whereCause.getFuncStr().equals("")) {
				list = JSON.parseArray(whereCause.getFuncStr(), FuncVO.class);
			}
			res = (Integer) super.update(MS_SQL_UPDATE_ROLE, whereCause);
			if (res == 0)
				throw new SmartDBAccessException("更新失败！");
			if (list != null && list.size() > 0) {
				for (FuncVO fun : list) {
					RelRoleFuncVO rf = new RelRoleFuncVO();
					rf.setRoleId(whereCause.getRoleId());
					rf.setFuncId(fun.getFuncId());
					rellist.add(rf);
				}
				super.delete(MS_SQL_DELETE_REL_ROLE_FUNC, whereCause.getRoleId());
				super.batchInsert(MS_SQL_INSERT_REL_ROLE_FUNC, rellist);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SmartDBAccessException("存储角色异常!");
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "删除角色信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.MIS_DELETE)
	public int deleteRole(RoleVO whereCause) {
		// TODO Auto-generated method stub
		return (Integer) super.update(MS_SQL_DELETE_ROLE, whereCause);
	}

	@SuppressWarnings("unchecked")
	@Override
	@SystemServiceLog(description = "查询角色信息", bizTypeId = BizOptType.query, functionId = FunctionMap.MIS_QUERY)
	public PageResult findRolePage(RoleVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<RoleVO> rows = null;
		int total = super.getCount(MS_SQL_FIND_ROLE_PAGE_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_SQL_FIND_ROLE_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	public RoleVO getRoleById(RoleVO whereCause) {
		// TODO Auto-generated method stub
		return (RoleVO) super.get(MS_SQL_FIND_ROLE_BY_ROLEID, whereCause.getRoleId());
	}

	@Override
	public RoleVO getRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return (RoleVO) super.get(MS_SQL_FIND_ROLE_BY_ROLENAME, roleName);
	}

	@Override
	public List<RoleVO> findAllRoleIdName() {
		// TODO Auto-generated method stub
		return super.find(MS_SQL_FIND_ROLE_ALL, null);
	}

}

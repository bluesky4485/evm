package org.evm.biz.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.evm.biz.func.util.BizOptType;
import org.evm.biz.func.util.FunctionMap;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.user.entity.UserRoleRelVO;
import org.evm.biz.user.entity.UserVO;
import org.evm.biz.user.service.IUserDbService;
import org.evm.core.entity.PageResult;
import org.evm.core.exception.SmartDBAccessException;
import org.evm.core.exception.SmartFunctionException;
import org.evm.core.service.BaseDBService;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class UserDbServiceImpl extends BaseDBService implements IUserDbService {

	@Override
	@SystemServiceLog(description = "创建用户信息", bizTypeId = BizOptType.add, functionId = FunctionMap.MIS_ADD)
	public int insertUser(UserVO whereCause) {
		// TODO Auto-generated method stub
		List<Long> roles = JSON.parseArray(whereCause.getRoleName(), Long.class);
		List<UserRoleRelVO> urList = null;
		int res = 0;
		try {
			res = (Integer) super.insert(MS_INSERT_USER, whereCause);
			if (res > 0) {
				urList = new ArrayList<UserRoleRelVO>();
				for (int i = 0; roles != null && i < roles.size(); i++) {
					UserRoleRelVO urvo = new UserRoleRelVO();
					urvo.setUserId(whereCause.getUid());
					urvo.setRoleId(roles.get(i));
					urvo.setInsUser(whereCause.getUpdUser());
					urvo.setUpdUser(whereCause.getUpdUser());
					urList.add(urvo);
				}
				super.batchInsert(MS_INSERT_USER_ROLE_REL, urList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("插入设备信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("插入设备信息异常", e);
		}
		return res;
	}

	@Override
	@SystemServiceLog(description = "更新用户信息", bizTypeId = BizOptType.update, functionId = FunctionMap.MIS_UPDATE)
	public int updateUser(UserVO whereCause) {
		// TODO Auto-generated method stub
		List<Long> roles = JSON.parseArray(whereCause.getRoleName(), Long.class);
		List<UserRoleRelVO> urList = null;
		int res = 0;
		res = super.update(MS_UPD_USER, whereCause);
		if(res==0){
			throw new SmartFunctionException("数据已旧！");
		}
		if (res != 0) {
			urList = new ArrayList<UserRoleRelVO>();
			for (int i = 0; roles != null && i < roles.size(); i++) {
				UserRoleRelVO urvo = new UserRoleRelVO();
				urvo.setUserId(whereCause.getUid());
				urvo.setRoleId(roles.get(i));
				urvo.setInsUser(whereCause.getUpdUser());
				urvo.setUpdUser(whereCause.getUpdUser());
				urList.add(urvo);
			}
			super.delete(MS_DELETE_USER_ROLE_REL_BY_UID, whereCause.getUid());
			super.batchInsert(MS_INSERT_USER_ROLE_REL, urList);
		}
		return res;
	}

	@Override
	public List<UserVO> findUsers(UserVO whereCause) {
		// TODO Auto-generated method stub
		List<UserVO> list = super.find(MS_FIND_USER_COND, whereCause);
		return list;
	}

	@Override
	@SystemServiceLog(description = "查询用户信息", bizTypeId = BizOptType.query, functionId = FunctionMap.MIS_QUERY)
	public PageResult findUsersPage(UserVO whereCause) {
		// TODO Auto-generated method stub
		PageResult pr = new PageResult();
		List<UserVO> rows = null;
		int total = super.getCount(MS_FIND_USER_CNT, whereCause);
		pr.setTotal(total);
		if (total > 0) {
			rows = super.find(MS_FIND_USER_PAGE, whereCause);
			pr.setRows(rows);
		}
		return pr;
	}

	@Override
	@SystemServiceLog(description = "删除用户信息", bizTypeId = BizOptType.delete, functionId = FunctionMap.MIS_DELETE)
	public int deleteUser(UserVO whereCause) {
		int res = 0;
		try {
			res = super.update(MS_DELETE_USER, whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("删除用户信息异常!参数：" + whereCause.toString(), e);
			throw new SmartDBAccessException("删除用户信息异常", e);
		}
		if(res==0){
			throw new SmartFunctionException("数据已旧！");
		}
		return res;
	}

	@Override
	public UserVO getUserById(long uid) {
		// TODO Auto-generated method stub
		return (UserVO) super.get(MS_FIND_USER_BYID, uid);
	}

	@Override
	public UserVO getUserByName(String name) {
		// TODO Auto-generated method stub
		UserVO whereCause = new UserVO();
		whereCause.setUname(name);
		UserVO res = (UserVO) super.get(MS_FIND_USER_COND, whereCause);
		return res;
	}

	@Override
	public int updateUserPwd(UserVO whereCause) {
		// TODO Auto-generated method stub
		return super.update(MS_UPDATE_USER_PWD, whereCause);
	}

}

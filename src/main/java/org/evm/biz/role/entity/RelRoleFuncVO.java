package org.evm.biz.role.entity;

import org.evm.core.entity.AbstractEntity;

public class RelRoleFuncVO extends AbstractEntity{
	private int roleId;
	private String funcId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

}

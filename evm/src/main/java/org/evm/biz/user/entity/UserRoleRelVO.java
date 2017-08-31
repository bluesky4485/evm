package org.evm.biz.user.entity;

import org.evm.core.entity.AbstractEntity;

public class UserRoleRelVO extends AbstractEntity {
	private long userId;
	private long roleId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}

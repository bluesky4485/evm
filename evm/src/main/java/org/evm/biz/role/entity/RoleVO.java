package org.evm.biz.role.entity;

import java.util.List;

import org.evm.biz.func.entity.FuncVO;
import org.evm.core.entity.AbstractEntity;

public class RoleVO extends AbstractEntity {
	/**
	 * 角色ID
	 */
	private int roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色备注
	 */
	private String roleRemark;
	/**
	 * 权限串
	 */
	private String funcStr;
	/**
	 * 权限
	 */
	private List<FuncVO> funcList;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public List<FuncVO> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<FuncVO> funcList) {
		this.funcList = funcList;
	}

	public String getFuncStr() {
		return funcStr;
	}

	public void setFuncStr(String funcStr) {
		this.funcStr = funcStr;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.roleId != 0)
			sb.append("角色ID=" + this.roleId).append(";");
		if (this.roleName != null)
			sb.append("角色名称=" + this.roleName).append(";");
		if (this.roleRemark != null)
			sb.append("角色备注=" + this.roleRemark).append(";");
		// sb.append("updUser=" + super.updUser).append(";");
		// sb.append("updDate=" + super.updDate).append(";");
		// sb.append("insUser=" + super.insUser).append(";");
		// sb.append("insDate=" + super.updDate).append(";");

		return sb.toString();
	}
}

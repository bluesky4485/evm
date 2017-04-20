package org.evm.biz.project.entity;

import org.evm.core.entity.AbstractEntity;

public class RelProjectUserVO extends AbstractEntity {
	private long projectId;
	private long userId;
	private String ufullName;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUfullName() {
		return ufullName;
	}

	public void setUfullName(String ufullName) {
		this.ufullName = ufullName;
	}

}

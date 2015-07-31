package com.shizm.model;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class RoleGroupRelation extends BaseModel {
	private String RoleId;
	private String GroupId;
	
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	public String getGroupId() {
		return GroupId;
	}
	public void setGroupId(String groupId) {
		GroupId = groupId;
	}
}

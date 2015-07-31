package com.shizm.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Role extends BaseModel {

	private String RoleName;
	
	@Transient
	private List<AuthorityGroup> lsAuthorityGroup;
	
	
	public String getRoleName() {
		return RoleName;
	}
	
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	
	public List<AuthorityGroup> getLsAuthorityGroup() {
		return lsAuthorityGroup;
	}
	
	public void setLsAuthorityGroup(List<AuthorityGroup> lsAuthorityGroup) {
		this.lsAuthorityGroup = lsAuthorityGroup;
	}
}

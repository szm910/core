package com.shizm.model;

import java.util.List;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class AuthorityGroup extends BaseModel {
	
	private String groupName;
	private List<Authority>  lsAuthority;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Authority> getLsAuthority() {
		return lsAuthority;
	}
	public void setLsAuthority(List<Authority> lsAuthority) {
		this.lsAuthority = lsAuthority;
	}
}

package com.shizm.model;

import javax.persistence.Entity;


@SuppressWarnings("serial")
@Entity
public class AuthorityGroupRelation extends BaseModel {
	
	private String AuthorityGroupId;
	private String AuthorityId;

	public String getAuthorityGroupId() {
		return AuthorityGroupId;
	}
	public void setAuthorityGroupId(String authorityGroupId) {
		AuthorityGroupId = authorityGroupId;
	}
	public String getAuthorityId() {
		return AuthorityId;
	}
	public void setAuthorityId(String authorityId) {
		AuthorityId = authorityId;
	}
	
}

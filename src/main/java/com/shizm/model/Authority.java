package com.shizm.model;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Authority extends BaseModel {
	
	private String authorityName;
	private String authorityValue;
	private String authorityAlias;
	
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getAuthorityValue() {
		return authorityValue;
	}
	public void setAuthorityValue(String authorityValue) {
		this.authorityValue = authorityValue;
	}
	public String getAuthorityAlias() {
		return authorityAlias;
	}
	public void setAuthorityAlias(String authorityAlias) {
		this.authorityAlias = authorityAlias;
	}
}

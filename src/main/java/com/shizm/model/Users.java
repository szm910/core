package com.shizm.model;

import javax.persistence.Entity;

@Entity
public class Users extends BaseModel {
	
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

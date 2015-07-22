package com.shizm.model;

import javax.persistence.Entity;

@Entity(name="Users")
public class User extends BaseModel {
	
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}

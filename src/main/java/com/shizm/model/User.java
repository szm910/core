package com.shizm.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


@SuppressWarnings("serial")
@Entity
@Table(name="Users")
public class User extends BaseModel {
	
	@NotEmpty(message="name.not.empty")
    private String userName;
	
    @Range(min=0, max=150,message="{age.not.inrange}")
	private int age;
    
    private String realName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}	
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", age=" + age + ", password=" + password + "]";
	}
}

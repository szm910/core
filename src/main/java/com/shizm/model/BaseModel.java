package com.shizm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实体的基类
 * @author shizm
 *
 */
@MappedSuperclass
public class BaseModel implements Serializable {
	
	@Id
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
    @Column(name="id")
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//时间戳
	@Version
	private Date version;
	public Date getVersion() {
		return version;
	}
	public void setVersion(Date version) {
		this.version = version;
	}

	
}

package com.changgou.system.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * role实体类
 * @author 黑马架构师2.5
 *
 */
@Table(name="tb_role")
public class Role implements Serializable {

	@Id
	private Integer id;//ID


	
	private String name;//角色名称

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



}

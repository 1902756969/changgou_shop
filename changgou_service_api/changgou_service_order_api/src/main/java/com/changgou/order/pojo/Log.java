package com.changgou.order.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * log实体类
 * @author 黑马架构师2.5
 *
 */
@Table(name="undo_log")
public class Log implements Serializable {

	@Id
	private Long id;//id


	
	private Long branchId;//branch_id
	private String xid;//xid
	private byte[] rollbackInfo;//rollback_info
	private Integer logStatus;//log_status
	private java.util.Date logCreated;//log_created
	private java.util.Date logModified;//log_modified
	private String ext;//ext

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getXid() {
		return xid;
	}
	public void setXid(String xid) {
		this.xid = xid;
	}

	public byte[] getRollbackInfo() {
		return rollbackInfo;
	}
	public void setRollbackInfo(byte[] rollbackInfo) {
		this.rollbackInfo = rollbackInfo;
	}

	public Integer getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}

	public java.util.Date getLogCreated() {
		return logCreated;
	}
	public void setLogCreated(java.util.Date logCreated) {
		this.logCreated = logCreated;
	}

	public java.util.Date getLogModified() {
		return logModified;
	}
	public void setLogModified(java.util.Date logModified) {
		this.logModified = logModified;
	}

	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}



}

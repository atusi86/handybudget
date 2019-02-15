package com.handybudget.database.domains;

import java.util.Date;

public class MeasuringDevicePosition {

	private int id;
	private int device_id;
	private int position;
	private int deleted;
	private Date create_timestamp;
	private Date update_timestamp;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDevice_id() {
		return device_id;
	}
	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public Date getCreate_timestamp() {
		return create_timestamp;
	}
	public void setCreate_timestamp(Date create_timestamp) {
		this.create_timestamp = create_timestamp;
	}
	public Date getUpdate_timestamp() {
		return update_timestamp;
	}
	public void setUpdate_timestamp(Date update_timestamp) {
		this.update_timestamp = update_timestamp;
	}
	
	
	
	
}

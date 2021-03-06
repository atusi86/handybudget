package com.handybudget.database.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MeasuringDevicePosition {

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private MeasuringDevice measuringDevice;
	private int position;
	private int deleted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date create_timestamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date update_timestamp;

	public MeasuringDevicePosition() {
	}

	public MeasuringDevicePosition(MeasuringDevice measuringDevice, int position) {
		super();
		this.measuringDevice = measuringDevice;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public MeasuringDevice getMeasuringDevice() {
		return measuringDevice;
	}

	public void setMeasuringDevice(MeasuringDevice measuringDevice) {
		this.measuringDevice = measuringDevice;
	}

}

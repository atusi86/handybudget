package com.handybudget.database.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MeasuringDevice {

	@Id
	private int id;

	@ManyToOne
	private User user;

	@OneToOne
	@JoinColumn(name = "type_id")
	private MeasuringDeviceType measuringDeviceType;

	private String identifier;
	private String description;
	private int deleted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date create_timestamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date update_timestamp;

	public MeasuringDevice() {
	}

	public MeasuringDevice(User user, MeasuringDeviceType measuringDeviceType, String identifier, String description) {
		super();
		this.user = user;
		this.measuringDeviceType = measuringDeviceType;
		this.identifier = identifier;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MeasuringDeviceType getMeasuringDeviceType() {
		return measuringDeviceType;
	}

	public void setMeasuringDeviceType(MeasuringDeviceType measuringDeviceType) {
		this.measuringDeviceType = measuringDeviceType;
	}

}

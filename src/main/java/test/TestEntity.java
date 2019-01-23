package test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testentity")
public class TestEntity {

	@Id
	private int id;
	private String firstname;
	private String lastname;
	
	
	
	public TestEntity() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "TestEntity [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	
	
	
}

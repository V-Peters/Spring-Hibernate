package spring_hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meeting")
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date_time")
	private String datetime;
	
	@Column(name = "display")
	private boolean display;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public boolean isDisplay() {
		return display;
	}
	
	public void setDisplay(boolean display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", name=" + name + ", datetime=" + datetime + ", display=" + display + "]";
	}
	
}

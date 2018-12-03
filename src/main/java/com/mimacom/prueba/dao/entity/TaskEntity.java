package com.mimacom.prueba.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_task;
	private String title;
	private String detail;
	private java.sql.Date creation_date;
	private java.sql.Date modification_date;
	private String state;
	@ManyToOne
	@JoinColumn(name="id_user")
	private UserEntity user;

	public Integer getId_task() {
		return id_task;
	}

	public void setId_task(Integer id_task) {
		this.id_task = id_task;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public java.sql.Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(java.sql.Date creation_date) {
		this.creation_date = creation_date;
	}

	public java.sql.Date getModification_date() {
		return modification_date;
	}

	public void setModification_date(java.sql.Date modification_date) {
		this.modification_date = modification_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String toString() {
		return "User id: " + user.getId_user() + " name: " + user.getUsername() + " task id:" + id_task + "task title"
				+ title;
	}
}

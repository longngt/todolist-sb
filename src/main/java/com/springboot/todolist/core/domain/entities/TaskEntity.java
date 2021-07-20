package com.springboot.todolist.core.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Task")
public class TaskEntity extends BaseEntity {

	@Column(name = "Content")
	private String content;

	@Column(name = "Status")
	private int status;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

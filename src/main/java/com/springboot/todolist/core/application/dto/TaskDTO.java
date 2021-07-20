package com.springboot.todolist.core.application.dto;

public class TaskDTO extends BaseDTO {
	private String content;
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

package com.springboot.todolist.core.domain.enums;

public enum TaskStatusEnum {
	DONE(1), UNDONE(0);

	public final int label;

	private TaskStatusEnum(int label) {
		this.label = label;
	}

}

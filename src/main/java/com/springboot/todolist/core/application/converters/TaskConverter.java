package com.springboot.todolist.core.application.converters;

import org.springframework.stereotype.Component;

import com.springboot.todolist.core.application.dto.TaskDTO;
import com.springboot.todolist.core.domain.entities.TaskEntity;

@Component
public class TaskConverter {
	public TaskDTO toDTO(TaskEntity taskEntity) {
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setContent(taskEntity.getContent());
		taskDTO.setStatus(taskEntity.getStatus());
		taskDTO.setCreatedAt(taskEntity.getCreatedAt());
		taskDTO.setUpdatedAt(taskEntity.getUpdatedAt());
		if (taskEntity.getId() != null) {
			taskDTO.setId(taskEntity.getId());
		}
		return taskDTO;
	}

	public TaskEntity toEntity(TaskDTO taskDTO) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setContent(taskDTO.getContent());
		taskEntity.setStatus(taskDTO.getStatus());
		taskEntity.setCreatedAt(taskDTO.getCreatedAt());
		taskEntity.setUpdatedAt(taskDTO.getUpdatedAt());
		if (taskDTO.getId() != null) {
			taskEntity.setId(taskDTO.getId());
		}
		return taskEntity;
	}

	public TaskEntity toEntity(TaskDTO taskDTO, TaskEntity existedEntity) {
		existedEntity.setContent(taskDTO.getContent());
		existedEntity.setStatus(taskDTO.getStatus());
		existedEntity.setUpdatedAt(taskDTO.getUpdatedAt());
		return existedEntity;
	}
}

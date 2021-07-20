package com.springboot.todolist.core.domain.interfaces.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.springboot.todolist.core.application.dto.TaskDTO;

public interface ITaskService {

	TaskDTO saveATask(TaskDTO task);

	String deleteATask(Long id);

	List<TaskDTO> getAllTasks(Pageable pageable);
}

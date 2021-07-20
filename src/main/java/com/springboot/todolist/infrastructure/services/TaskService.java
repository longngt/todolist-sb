package com.springboot.todolist.infrastructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.todolist.core.application.converters.TaskConverter;
import com.springboot.todolist.core.application.dto.TaskDTO;
import com.springboot.todolist.core.domain.entities.TaskEntity;
import com.springboot.todolist.core.domain.enums.TaskStatusEnum;
import com.springboot.todolist.core.domain.interfaces.repositories.ITaskRepository;
import com.springboot.todolist.core.domain.interfaces.services.ITaskService;

@Service
public class TaskService implements ITaskService {

	@Autowired
	private ITaskRepository taskRepository;
	@Autowired
	private TaskConverter taskConverter;

	// Create a new task or update an existed one
	@Override
	public TaskDTO saveATask(TaskDTO task) {
		TaskEntity taskEntity = taskConverter.toEntity(task);
		List<TaskEntity> taskListData = taskRepository.findAll();
		List<TaskEntity> taskFiltered = taskListData.stream().filter(t -> t.getContent().contains(task.getContent()))
				.collect(Collectors.toList());
		if (taskFiltered.size() >= 2) {
			task.setContent("Error: Overwhelming existed data");
			return task;
		}
		// Update section
		if (task.getId() != null) {
			Optional<TaskEntity> optionalExistedTaskEntity = taskRepository.findById(taskEntity.getId());
			if (optionalExistedTaskEntity.isPresent()) {
				TaskEntity convertedUpdateTaskEntity = taskConverter.toEntity(task, optionalExistedTaskEntity.get());
				TaskEntity updatedTaskEntity = taskRepository.save(convertedUpdateTaskEntity);
				return taskConverter.toDTO(updatedTaskEntity);
			}
		}

		task.setStatus(TaskStatusEnum.UNDONE.label);
		TaskEntity newTask = taskRepository.save(taskConverter.toEntity(task));
		return taskConverter.toDTO(newTask);
	}

	// Delete a task by Id
	@Override
	public String deleteATask(Long id) {
		String message;
		try {
			taskRepository.deleteById(id);
			message = String.format("Deleted task: Id = %s", id);
		} catch (Exception e) {
			message = String.format("Error handling delete process because: %s", e);
		}

		return message;

	}

	// Get list of tasks
	@Override
	public List<TaskDTO> getAllTasks(Pageable pageable) {
		List<TaskDTO> responseList = new ArrayList<TaskDTO>();
		Page<TaskEntity> taskList = taskRepository.findAll(pageable);
		for (TaskEntity taskEntity : taskList) {
			responseList.add(taskConverter.toDTO(taskEntity));
		}
		return responseList;
	}

}

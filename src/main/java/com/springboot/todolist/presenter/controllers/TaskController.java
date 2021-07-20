package com.springboot.todolist.presenter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todolist.core.application.dto.TaskDTO;
import com.springboot.todolist.infrastructure.services.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping(path = "/api/task")
	public List<TaskDTO> getAllTask(@RequestParam int perPage, @RequestParam int page) {
		Pageable pageable = PageRequest.of(page - 1, perPage);
		List<TaskDTO> taskList = taskService.getAllTasks(pageable);

		return taskList;
	}

	@PostMapping(path = "/api/task")
	public TaskDTO createATask(@RequestBody TaskDTO model) {
		TaskDTO createdTask = taskService.saveATask(model);
		/*
		 * Response<TaskDTO> response; response.setStatusCode(HttpStatus.OK.value());
		 * response.setMessage("Success"); response.setData(createdTask);
		 */

		return createdTask;
	}

	@PutMapping(path = "/api/task/{id}")
	public TaskDTO updatedATask(@RequestBody TaskDTO model, @PathVariable Long id) {
		model.setId(id);
		TaskDTO updatedTask = taskService.saveATask(model);
		return updatedTask;
	}

	@DeleteMapping(path = "/api/task/{id}")
	public String deleteATaskById(@PathVariable Long id) {
		String deletedTask = taskService.deleteATask(id);
		return deletedTask;
	}

}

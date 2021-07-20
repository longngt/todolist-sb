package com.springboot.todolist.core.domain.interfaces.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.todolist.core.domain.entities.TaskEntity;

public interface ITaskRepository extends JpaRepository<TaskEntity, Long>, PagingAndSortingRepository<TaskEntity, Long> {
	Page<TaskEntity> findAll(Pageable pageable);
}

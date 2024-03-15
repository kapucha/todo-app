package com.capgemini.training.todoapp.task.dataaccess.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.training.todoapp.task.dataaccess.entity.TaskListEntity;

public interface TaskListRepository extends JpaRepository<TaskListEntity, Long>{
	
	List<TaskListEntity> findAllByName(String name);
	
	@Query("select task from TaskListEntity task where task.name like :name")
	List<TaskListEntity> findUsingQuery(@Param("name") String name);
	
	List<TaskListEntity> findByNameContainingIgnoreCase(String name);
	
	//Task 2.3) Find TaskList without any items - use NamedQuery and bind it to interface method
	List<TaskListEntity> findTasksWithoutItemsUsingNamedQuery();

}

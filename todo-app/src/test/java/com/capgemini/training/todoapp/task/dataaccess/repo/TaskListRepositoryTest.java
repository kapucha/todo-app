package com.capgemini.training.todoapp.task.dataaccess.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.capgemini.training.todoapp.task.dataaccess.entity.TaskListEntity;

@DataJpaTest
public class TaskListRepositoryTest {
	
	@Autowired
	private TaskListRepository taskListRepository;
	
	@Test
	public void findAllTest() {
		//Given
		//When
		List<TaskListEntity> result = taskListRepository.findAll();
		
		//Then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(3);
	}
	
	@Test
	public void findByNameIgnoringCase() {
		//Given
		//When
		List<TaskListEntity> result = taskListRepository.findByNameContainingIgnoreCase("tester tasks");
		
		//Then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(1);
	}
	
	// 1) Find all TaskLists with partially given name, ignoring upper and lowercase
	@Test
	public void findByPartialNameIgnoringCase() {
		//Given
		//When
		List<TaskListEntity> result = taskListRepository.findByNameContainingIgnoreCase("TASK");
		
		//Then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(3);
	}
	
	@Test
	public void findByNameWithQuery() {
		//Given
		//When
		List<TaskListEntity> result = taskListRepository.findUsingQuery("%Tasks%");
		
		//Then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(3);
	}
	
	@Test
	public void findByNameWithNamedQuery() {
		//Given
		//When
		List<TaskListEntity> result = taskListRepository.findTasksWithoutItemsUsingNamedQuery();
		
		//
		//Then
		//TODO
		//assertThat(result).isEmpty();
	}
}

package com.capgemini.training.todoapp.task.dataaccess.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.capgemini.training.todoapp.task.dataaccess.entity.TaskItemEntity;

@DataJpaTest
public class TaskItemRepositoryTest {
	
	@Autowired
	TaskItemRepository taskItemRepository;
	
	// 2) Find all TaskItems by completion and deadline - essentially we want to find and which are not completed and with exceeded deadline
	
	@Test
	public void findAllWithDeadlineBefore() {
		//Given
		LocalDateTime localDate = LocalDateTime.of(2024, 6, 1, 0, 0);
		Instant instantDate = localDate.atZone(ZoneId.of("Europe/Warsaw")).toInstant();
		
		//When
		List<TaskItemEntity> result1 = taskItemRepository.findByDeadlineBefore(instantDate);
		
		//Then
		assertThat(result1).isNotEmpty();
		assertThat(result1).hasSize(2);
		
		//Given
		localDate = LocalDateTime.of(2024, 12, 4, 0, 0);
		instantDate = localDate.atZone(ZoneId.of("Europe/Warsaw")).toInstant();
		
		//When
		List<TaskItemEntity> result2 = taskItemRepository.findByDeadlineBefore(instantDate);
		
		//Then
		assertThat(result2).isNotEmpty();
		assertThat(result2).hasSize(3);
	}
	
	@Test
	public void findAllCompleted() {
		//Given	
		//When
		List<TaskItemEntity> result = taskItemRepository.findAllByCompleted(false);
		
		//Then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(4);
	}
	
	@Test
	public void findAllNotCompleted() {
		//Given	
		//When
		List<TaskItemEntity> result = taskItemRepository.findAllByCompleted(true);
		
		//Then
		assertThat(result).isEmpty();
	}
	
	@Test
	public void findNotCompletedWithDeadlineExceded() {
		//Given	
		//When
		List<TaskItemEntity> result = taskItemRepository.findUncompletedWithDeadlineExceeded();
		
		//Then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(4);
	}
	
	@Test
	public void findTasksWithDeadlineBetweenDates() {
		//Given	
		LocalDateTime localDateFrom = LocalDateTime.of(2024, 4, 1, 0, 0);
		Instant instantDateFrom = localDateFrom.atZone(ZoneId.of("Europe/Warsaw")).toInstant();
		
		LocalDateTime localDateTo = LocalDateTime.of(2024, 12, 10, 0, 0);
		Instant instantDateTo = localDateTo.atZone(ZoneId.of("Europe/Warsaw")).toInstant();
		//When
		List<TaskItemEntity> result = taskItemRepository.findWithDeadlineBetween(instantDateFrom, instantDateTo);
		
		//Then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(4);
	}

	@Test
	public void findTasksWithDeadlineBetweenDatesOnly2Items() {
		//Given	
		LocalDateTime localDateFrom = LocalDateTime.of(2024, 11, 1, 0, 0);
		Instant instantDateFrom = localDateFrom.atZone(ZoneId.of("Europe/Warsaw")).toInstant();
		
		LocalDateTime localDateTo = LocalDateTime.of(2024, 12, 10, 0, 0);
		Instant instantDateTo = localDateTo.atZone(ZoneId.of("Europe/Warsaw")).toInstant();
		//When
		List<TaskItemEntity> result = taskItemRepository.findWithDeadlineBetween(instantDateFrom, instantDateTo);
		
		//Then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(2);
	}
}

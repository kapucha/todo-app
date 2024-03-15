package com.capgemini.training.todoapp.task.logic.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.training.todoapp.task.common.TaskItemEto;
import com.capgemini.training.todoapp.task.dataaccess.entity.TaskItemEntity;
import com.capgemini.training.todoapp.task.dataaccess.repo.TaskItemRepository;

@ExtendWith(MockitoExtension.class)
public class FindItemUcImplTest {
	
	@InjectMocks
    private FindItemUcImpl findItemUcImpl;

    @Mock
    private TaskItemRepository taskItemRepository;
    
    private static final LocalDateTime LOCAL_DATE = LocalDateTime.of(2024, 6, 1, 0, 0);
    private static final Instant DEADLINE = LOCAL_DATE.atZone(ZoneId.of("Europe/Warsaw")).toInstant();

    @Test
    void findAllItems() {
        // Given
		
    	List<TaskItemEntity> list = new ArrayList<TaskItemEntity>();

    	TaskItemEntity entity = new TaskItemEntity();
		entity.setId(10L);
		entity.setName("item 1");
		entity.setVersion(0);
		entity.setCompleted(false);
		entity.setDeadline(DEADLINE);
		list.add(entity);
		
    	TaskItemEntity entity2 = new TaskItemEntity();
		entity2.setId(20L);
		entity2.setName("item 2");
		entity2.setVersion(0);
		entity2.setCompleted(false);
		entity2.setDeadline(DEADLINE);
		list.add(entity2);

    	Mockito.when(taskItemRepository.findAll()).thenReturn(list);
    	
    	// When
    	List<TaskItemEto> foundItems = findItemUcImpl.findAllItems();
    	
    	// Then
    	assertThat(foundItems).isNotEmpty();
    	assertThat(foundItems).hasSize(2);
    }
    
    @Test
    void findAllItemsWhenEmptyList() {
        // Given
    	List<TaskItemEntity> list = new ArrayList<TaskItemEntity>();
     	
    	Mockito.when(taskItemRepository.findAll()).thenReturn(list);
    	
    	// When
    	List<TaskItemEto> foundItems = findItemUcImpl.findAllItems();
    	
    	// Then
    	assertThat(foundItems).isEmpty();
    	assertThat(foundItems).hasSize(0);
    }

    @Test
    void findItemn() {
        // given
    	TaskItemEntity entity = new TaskItemEntity();
		entity.setId(10L);
		entity.setName("item 1");
		entity.setVersion(0);
		entity.setCompleted(false);
		entity.setDeadline(DEADLINE);
	
        when(taskItemRepository.findById(10L)).thenReturn(Optional.of(entity));

        // when
        Optional<TaskItemEto> result = findItemUcImpl.findItem(10L);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().name()).isEqualTo("item 1");
    }
    
    @Test
    void findItemNegative() {
        // given
      	when(taskItemRepository.findById(200L)).thenReturn(Optional.empty()); 

        // when
        Optional<TaskItemEto> result = findItemUcImpl.findItem(200L);

        // then
        assertThat(result).isEqualTo(Optional.empty());
    }
    
    @Test
    void findItemNegativeWithIdNull() {
        // given
        // when
        Optional<TaskItemEto> result = findItemUcImpl.findItem(null);

        // then
        assertThat(result).isEqualTo(Optional.empty());
    }


}

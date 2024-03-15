package com.capgemini.training.todoapp.task.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.training.todoapp.task.common.TaskItemEto;
import com.capgemini.training.todoapp.task.dataaccess.entity.TaskItemEntity;
import com.capgemini.training.todoapp.task.dataaccess.repo.TaskItemRepository;
import com.capgemini.training.todoapp.task.logic.ManageItemUc;

@Service
@Transactional
public class ManageItemUcImpl implements ManageItemUc{
	
	@Autowired
	private TaskItemRepository taskItemRepository;

	@Override
	public TaskItemEto saveItem(TaskItemEto itemEto) {
		TaskItemEntity entity = toItemEntity(itemEto);
		TaskItemEntity saved = taskItemRepository.saveAndFlush(entity);
		
		return toItemEto(saved);
	}

	@Override
	public void deleteItem(Long id) {
		if(id == null) {
			return;
		} 
		taskItemRepository.deleteById(id);
	}
	
	private TaskItemEntity toItemEntity(TaskItemEto eto) {
		TaskItemEntity entity = new TaskItemEntity();
		entity.setId(eto.id());
		entity.setName(eto.name());
		entity.setVersion(eto.version());
		entity.setCompleted(eto.completed());
		entity.setDeadline(eto.deadline());
		
		return entity;
	}
	
	private TaskItemEto toItemEto(TaskItemEntity entity) {
		return TaskItemEto.builder().id(entity.getId()).version(entity.getVersion()).deadline(entity.getDeadline())
				.completed(entity.isCompleted()).name(entity.getName()).build();
	}

}

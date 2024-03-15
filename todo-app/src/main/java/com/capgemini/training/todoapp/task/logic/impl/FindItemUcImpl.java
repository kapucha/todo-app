package com.capgemini.training.todoapp.task.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.training.todoapp.task.common.TaskItemEto;
import com.capgemini.training.todoapp.task.dataaccess.entity.TaskItemEntity;
import com.capgemini.training.todoapp.task.dataaccess.repo.TaskItemRepository;
import com.capgemini.training.todoapp.task.logic.FindItemUc;

@Service
@Transactional
public class FindItemUcImpl implements FindItemUc {

	private final TaskItemRepository taskItemRepository;
	
	public FindItemUcImpl(TaskItemRepository taskItemRepository) {
		this.taskItemRepository = taskItemRepository;
	}

	@Override
	public List<TaskItemEto> findAllItems() {
		List<TaskItemEntity> entities = taskItemRepository.findAll();
		List<TaskItemEto> etos = new ArrayList<TaskItemEto>();
		for (TaskItemEntity entity : entities) {
			TaskItemEto eto = toItemEto(entity);
			etos.add(eto);
		}
		return etos;
	}

	@Override
	public Optional<TaskItemEto> findItem(Long id) {
		if (id == null) {
			return Optional.empty();
		}

		Optional<TaskItemEntity> entity = taskItemRepository.findById(id);
		if (entity.isPresent()) {
			return Optional.of(toItemEto(entity.get()));
		}
		return Optional.empty();
	}

	private TaskItemEto toItemEto(TaskItemEntity entity) {
		return TaskItemEto.builder().id(entity.getId()).version(entity.getVersion()).deadline(entity.getDeadline())
				.completed(entity.isCompleted()).name(entity.getName()).build();
	}

}

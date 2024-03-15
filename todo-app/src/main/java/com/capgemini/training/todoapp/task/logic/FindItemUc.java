package com.capgemini.training.todoapp.task.logic;

import java.util.List;
import java.util.Optional;

import com.capgemini.training.todoapp.task.common.TaskItemEto;

public interface FindItemUc {
	
    List<TaskItemEto> findAllItems();

    Optional<TaskItemEto> findItem(Long id);

}

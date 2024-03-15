package com.capgemini.training.todoapp.task.logic;

import com.capgemini.training.todoapp.task.common.TaskItemEto;

public interface ManageItemUc {
	
    TaskItemEto saveItem(TaskItemEto itemEto);

    void deleteItem(Long id);

}

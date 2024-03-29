package com.capgemini.training.todoapp.task.dataaccess.entity;

import java.util.List;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TASK_LIST")
@Setter
@Getter
//Task 2.3) Find TaskList without any items - use NamedQuery and bind it to interface method
@NamedQuery(name = "TaskListEntity.findTasksWithoutItemsUsingNamedQuery",
        query = "select list from TaskListEntity list join TaskItemEntity item on list.id != item.taskList.id")
public class TaskListEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;
	
	private String name;
	
	@OneToMany(mappedBy = "taskList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TaskItemEntity> items;

}

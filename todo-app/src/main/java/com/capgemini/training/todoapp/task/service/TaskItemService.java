package com.capgemini.training.todoapp.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.training.todoapp.task.common.TaskItemEto;
import com.capgemini.training.todoapp.task.logic.FindItemUc;
import com.capgemini.training.todoapp.task.logic.ManageItemUc;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class TaskItemService {

	@Autowired
	private ManageItemUc manageItemUc;
	
	@Autowired
	private FindItemUc findItemUc;
	
	@GetMapping("/{id}")
	TaskItemEto findItem(@PathVariable("id") Long id) {
	    return findItemUc.findItem(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	                    "Person with id " + id + " does not exist."));
	}

	@GetMapping("/")
	List<TaskItemEto> findAllItems() {
		 return findItemUc.findAllItems();
	}

	@PostMapping("/")
	TaskItemEto saveItem(@RequestBody TaskItemEto item) {
		return manageItemUc.saveItem(item);
	}

	@DeleteMapping("/{id}")
	void deleteItem(@PathVariable("id") Long id) {
	    manageItemUc.deleteItem(id);
	}
}


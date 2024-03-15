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

import com.capgemini.training.todoapp.task.common.PersonEto;
import com.capgemini.training.todoapp.task.logic.FindPersonUc;
import com.capgemini.training.todoapp.task.logic.ManagePersonUc;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonService {

	@Autowired
	private ManagePersonUc managePersonUc;
	
	@Autowired
	private FindPersonUc findPersonUc;
	
	@GetMapping("/{id}")
	PersonEto findPerson(@PathVariable("id") Long id) {
	    return findPersonUc.findPerson(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	                    "Person with id " + id + " does not exist."));
	}

	@GetMapping("/")
	List<PersonEto> findAllPersons() {
		 return findPersonUc.findAllPersons();
	}

	@PostMapping("/")
	PersonEto savePerson(@RequestBody PersonEto person) {
		return managePersonUc.savePerson(person);
	}

	@DeleteMapping("/{id}")
	void deletePerson(@PathVariable("id") Long id) {
	    managePersonUc.deletePerson(id);
	}
}

package com.capgemini.training.todoapp.task.logic;

import java.util.List;
import java.util.Optional;

import com.capgemini.training.todoapp.task.common.PersonEto;

public interface FindPersonUc {
	
    List<PersonEto> findAllPersons();

    Optional<PersonEto> findPerson(Long id);

}

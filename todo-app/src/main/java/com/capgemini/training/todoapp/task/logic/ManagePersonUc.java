package com.capgemini.training.todoapp.task.logic;

import com.capgemini.training.todoapp.task.common.PersonEto;

public interface ManagePersonUc {

    PersonEto savePerson(PersonEto personEto);

    void deletePerson(Long id);
    
    PersonEto findPerson(Long id);
}

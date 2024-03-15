package com.capgemini.training.todoapp.task.logic.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.capgemini.training.todoapp.task.common.PersonEto;
import com.capgemini.training.todoapp.task.logic.ManagePersonUc;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ManagePersonUcImplTest {
	

    @Autowired
    private ManagePersonUc managePersonUc;

    @Test
    public void savePerson_shouldCreatePerson() {
        // Given
    	PersonEto newPerson = PersonEto.builder().id(1L).version(0).email("email@test.com").build();
    	
    	// When
    	PersonEto savedPerson = managePersonUc.savePerson(newPerson);
    	
    	//Then
    	assertThat(savedPerson).isNotNull();
    	assertThat(savedPerson.email()).isEqualTo("email@test.com");
    	
    }

    @Test
    public void savePerson_shouldUpdatePerson() {
        // Given
    	Long existingID = -1L;
    	PersonEto personAlreadyInDB = managePersonUc.findPerson(existingID);
    	assertThat(personAlreadyInDB).isNotNull();
    	assertThat(personAlreadyInDB.email()).isEqualTo("ba@capgemini.com");
    	
    	PersonEto newPerson = PersonEto.builder().id(existingID).version(0).email("new_email@test.com").build();
    	
    	// When
    	PersonEto savedPerson = managePersonUc.savePerson(newPerson);
    	
    	PersonEto updatedPerson = managePersonUc.findPerson(existingID);
    	
    	//Then
    	assertThat(savedPerson).isNotNull();
    	assertThat(savedPerson.email()).isEqualTo("new_email@test.com");
    	assertThat(savedPerson.id()).isEqualTo(existingID);
    	
    	assertThat(updatedPerson.email()).isEqualTo("new_email@test.com");;
    	assertThat(updatedPerson.id()).isEqualTo(existingID);
    	assertThat(updatedPerson).isEqualTo(savedPerson);
    }

    @Test
    public void deletePerson() {
        // Given
    	Long existingID = -2L;
    	PersonEto personAlreadyInDB = managePersonUc.findPerson(existingID);
    	assertThat(personAlreadyInDB).isNotNull();
    	    	
    	// When
    	managePersonUc.deletePerson(existingID);
    	
    	//Then
    	assertThat(managePersonUc.findPerson(existingID)).isNull();
    }
    
    @Test
    public void deletePersonWithIdNull() {
        // Given  	    	
    	// When
    	managePersonUc.deletePerson(null);
    	
    	//Then
    	//assertThat(managePersonUc.findPerson(null)).isNull();
    }


}

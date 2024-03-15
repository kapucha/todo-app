package com.capgemini.training.todoapp.task.logic.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.training.todoapp.task.common.PersonEto;
import com.capgemini.training.todoapp.task.dataaccess.entity.PersonEntity;
import com.capgemini.training.todoapp.task.dataaccess.repo.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class FindPersonUcImplTest {
	
	@InjectMocks
    private FindPersonUcImpl findPersonUc;

    @Mock
    private PersonRepository personRepository;
    
    @Test
    void findAllPersons() {
        // Given
    	List<PersonEntity> list = new ArrayList<PersonEntity>();

    	PersonEntity entity1 = new PersonEntity();
		entity1.setId(1L);
		entity1.setVersion(0);
		entity1.setEmail("qwertz");
		list.add(entity1);

    	PersonEntity entity2 = new PersonEntity();
		entity2.setId(2L);
		entity2.setVersion(0);
		entity2.setEmail("uiop");
		list.add(entity2);
    	
    	Mockito.when(personRepository.findAll()).thenReturn(list);
    	// When
    	List<PersonEto> foundPeople = findPersonUc.findAllPersons();
    	
    	// Then
    	assertThat(foundPeople).isNotEmpty();
    	assertThat(foundPeople).hasSize(2);
    }
    
    @Test
    void findAllPersonWhenEmptyList() {
        // Given
    	List<PersonEntity> list = new ArrayList<PersonEntity>();
     	
    	Mockito.when(personRepository.findAll()).thenReturn(list);
    	// When
    	List<PersonEto> foundPeople = findPersonUc.findAllPersons();
    	
    	// Then
    	assertThat(foundPeople).isEmpty();
    	assertThat(foundPeople).hasSize(0);
    }

    @Test
    void findPerson() {
        // given
    	PersonEntity entity = new PersonEntity();
		entity.setId(1L);
		entity.setVersion(0);
		entity.setEmail("qwertz");
        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        // when
        Optional<PersonEto> result = findPersonUc.findPerson(1L);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().email()).isEqualTo("qwertz");
    }
    
    @Test
    void findPersonNegative() {
        // given
      	when(personRepository.findById(2L)).thenReturn(Optional.empty()); 

        // when
        Optional<PersonEto> result = findPersonUc.findPerson(2L);

        // then
        assertThat(result).isEqualTo(Optional.empty());
    }
    
    @Test
    void findPersonNegativeWithIdNull() {
        // given
        // when
        Optional<PersonEto> result = findPersonUc.findPerson(null);

        // then
        assertThat(result).isEqualTo(Optional.empty());
    }

}

package com.capgemini.training.todoapp.task.dataaccess.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.capgemini.training.todoapp.task.dataaccess.entity.PersonEntity;

@DataJpaTest
public class PersonRepositoryTest {
	
	@Autowired
	private PersonRepository personRepository;
	
	//Task 2.1) Find person using email - use Spring Query Method 
	@Test
	public void findPersionByEmail() {
		
		//Given
		String email = "ba@capgemini.com";
		
		//When
		List<PersonEntity> resultList = personRepository.findAllByEmail(email);
		
		//Then
		assertThat(resultList).isNotEmpty();
		assertThat(resultList).hasSize(1);			
	}
	
	@Test
	public void findPersionByEmailNegative() {
		
		//Given
		String email = "test@capgemini.com";
		
		//When
		List<PersonEntity> resultList = personRepository.findAllByEmail(email);
		
		//Then
		assertThat(resultList).isEmpty();		
	}

}

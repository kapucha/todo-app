package com.capgemini.training.todoapp.task.dataaccess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.training.todoapp.task.dataaccess.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>{
	
	//List<PersonEntity> findAllByEmail(String email);

}

package com.capgemini.training.todoapp.task.logic.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.training.todoapp.task.common.PersonEto;
import com.capgemini.training.todoapp.task.dataaccess.entity.PersonEntity;
import com.capgemini.training.todoapp.task.dataaccess.repo.PersonRepository;
import com.capgemini.training.todoapp.task.logic.ManagePersonUc;

import lombok.Builder;

@Service
@Transactional
public class ManagePersonUcImpl implements ManagePersonUc {

	private final PersonRepository personRepository;

	public ManagePersonUcImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public PersonEto savePerson(PersonEto personEto) {

		PersonEntity personEntity = toPersonEntity(personEto);
		personEntity = personRepository.saveAndFlush(personEntity);
		return toPersonEto(personEntity);
	}

	@Override
	public void deletePerson(Long id) {
		if(id == null) {
			return;
		}
		personRepository.deleteById(id);
	}

	@Override
	public PersonEto findPerson(Long id) {
		Optional<PersonEntity> foundPerson = personRepository.findById(id);
		
		if(foundPerson.isPresent()) {
			return toPersonEto(foundPerson.get());
		}
		else {
			System.out.println(String.format("Person with id %s was not found", id));
			return null;
		}
	}

	private PersonEntity toPersonEntity(PersonEto personEto) {
		PersonEntity entity = new PersonEntity();
		entity.setId(personEto.id());
		entity.setVersion(personEto.version());
		entity.setEmail(personEto.email());
		return entity;
	}

	private PersonEto toPersonEto(PersonEntity personEntity) {
		return PersonEto.builder().id(personEntity.getId()).version(personEntity.getVersion())
				.email(personEntity.getEmail()).build();
	}

}

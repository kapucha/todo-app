package com.capgemini.training.todoapp.task.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.training.todoapp.task.common.PersonEto;
import com.capgemini.training.todoapp.task.dataaccess.entity.PersonEntity;
import com.capgemini.training.todoapp.task.dataaccess.repo.PersonRepository;
import com.capgemini.training.todoapp.task.logic.FindPersonUc;

@Service
@Transactional
public class FindPersonUcImpl implements FindPersonUc {

	private final PersonRepository personRepository;

	public FindPersonUcImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<PersonEto> findAllPersons() {
		List<PersonEntity> foundPeople = personRepository.findAll();

		List<PersonEto> resultList = new ArrayList<PersonEto>();
		for (PersonEntity personEntity : foundPeople) {
			PersonEto person = toPersonEto(personEntity);
			resultList.add(person);
		}

		return resultList;
	}

	@Override
	public Optional<PersonEto> findPerson(Long id) {
		if (id == null) {
			return Optional.empty();
		}
		Optional<PersonEntity> entity = personRepository.findById(id);
		if (entity.isPresent()) {
			PersonEto person = toPersonEto(entity.get());
			return Optional.of(person);
		} else
			return Optional.empty();
	}

	private PersonEntity toPersonEntity(PersonEto personEto) {
//		PersonEntity entity = PersonEntity.builder().id(personEto.id()).version(personEto.version()).email(personEto.email()).build();
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

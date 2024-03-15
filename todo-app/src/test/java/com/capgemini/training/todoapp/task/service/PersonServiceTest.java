package com.capgemini.training.todoapp.task.service;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;	

import com.capgemini.training.todoapp.task.common.PersonEto;
import com.capgemini.training.todoapp.task.logic.FindPersonUc;
import com.capgemini.training.todoapp.task.logic.ManagePersonUc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PersonService.class)
public class PersonServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FindPersonUc findPersonUc;

	@MockBean
	private ManagePersonUc managePersonUc;

	@Test
	public void findPerson_ShouldReturnPerson() throws Exception {
		// Given
		Long personId = 1L;
		PersonEto person = PersonEto.builder().id(personId).version(1).email("test@example.com").build();
		Mockito.when(findPersonUc.findPerson(personId)).thenReturn(Optional.of(person));

		// When
		// Then
		mockMvc.perform(get("/person/{id}", personId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value(person.id()))//
				.andExpect(jsonPath("$.email").value(person.email()));
	}

	@Test
	public void findPerson_NotFound() throws Exception {
		// Given
		Long personId = 1L;
		Mockito.when(findPersonUc.findPerson(personId)).thenReturn(Optional.empty());

		// When
		// Then
		mockMvc.perform(get("/person/{id}", personId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())//
				.andExpect(jsonPath("$.id").doesNotExist())//
				.andExpect(jsonPath("$.email").doesNotExist());
	}

	@Test
	public void findAllPositive() throws Exception {
		// Given
		ArrayList<PersonEto> list = new ArrayList<>(Arrays.asList(//
				PersonEto.builder().id(10L).version(0).email("uiop").build(), //
				PersonEto.builder().id(20L).version(0).email("jklö").build(), //
				PersonEto.builder().id(30L).version(0).email("nm,.").build(), //
				PersonEto.builder().id(40L).version(0).email("yxcv").build(), //
				PersonEto.builder().id(50L).version(0).email("asdf").build()));
		Mockito.when(findPersonUc.findAllPersons()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
				.andExpect(jsonPath("$[0].id").value(list.get(0).id()))//
				.andExpect(jsonPath("$[0].email").value(list.get(0).email()))//
				.andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
	public void findAll_EmptyList() throws Exception {
		// Given
		ArrayList<PersonEto> list = new ArrayList<>();
		Mockito.when(findPersonUc.findAllPersons()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void savePersonPositive() throws Exception {

		// Given
		PersonEto person = PersonEto.builder().id(20L).version(1).email("saveperson@example.com").build();

		ObjectMapper objectMapper = new ObjectMapper();
		String newPersonJson = objectMapper.writeValueAsString(person);
		Mockito.when(managePersonUc.savePerson(person)).thenReturn(person);

		// When
		// Then
		mockMvc.perform(post("/person/")//
				.contentType(MediaType.APPLICATION_JSON).content(newPersonJson))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value(person.id()))//
				.andExpect(jsonPath("$.email").value(person.email()));
	}

	@Test
	public void savePersonNegative() throws Exception {

		// TODO

		// Given
		ArrayList<PersonEto> list = new ArrayList<>();
		Mockito.when(findPersonUc.findAllPersons()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void deletePersonPositive() throws Exception {

		// TODO

		// Given
		ArrayList<PersonEto> list = new ArrayList<>();
		Mockito.when(findPersonUc.findAllPersons()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void deletePersonNegative() throws Exception {

		// TODO

		// Given
		ArrayList<PersonEto> list = new ArrayList<>();
		Mockito.when(findPersonUc.findAllPersons()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(0)));
	}
}

package com.capgemini.training.todoapp.task.service;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

import com.capgemini.training.todoapp.task.common.TaskItemEto;
import com.capgemini.training.todoapp.task.logic.FindItemUc;
import com.capgemini.training.todoapp.task.logic.ManageItemUc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TaskItemService.class)
public class TaskItemServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FindItemUc findItemUc;

	@MockBean
	private ManageItemUc manageItemUc;

	private static final LocalDateTime LOCAL_DATE = LocalDateTime.of(2024, 6, 1, 0, 0);
	private static final Instant DEADLINE = LOCAL_DATE.atZone(ZoneId.of("Europe/Warsaw")).toInstant();
	
	//TODO poprawic testy

//	@Test
	public void findItem_ShouldReturnItem() throws Exception {
		// Given
		Long itemId = -1L;
		TaskItemEto item = TaskItemEto.builder().id(itemId).version(1).name("item name").completed(false)
				.deadline(DEADLINE).build();
		Mockito.when(findItemUc.findItem(itemId)).thenReturn(Optional.of(item));

		// When
		// Then
		mockMvc.perform(get("/person/{id}", itemId).contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value(item.id()))//
				.andExpect(jsonPath("$.name").value(item.name()));
	}

//@Test
	public void findItem_NotFound() throws Exception {
		// Given
		Long itemId = 1L;
		Mockito.when(findItemUc.findItem(itemId)).thenReturn(Optional.empty());

		// When
		// Then
		mockMvc.perform(get("/person/{id}", itemId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())//
				.andExpect(jsonPath("$.id").doesNotExist())//
				.andExpect(jsonPath("$.name").doesNotExist());
	}

	//@Test
	public void findAllPositive() throws Exception {
		// Given
		ArrayList<TaskItemEto> list = new ArrayList<>(Arrays.asList(//
				TaskItemEto.builder().id(10L).version(0).name("uiop").build(), //
				TaskItemEto.builder().id(20L).version(0).name("jklö").build(), //
				TaskItemEto.builder().id(30L).version(0).name("nm,.").build(), //
				TaskItemEto.builder().id(40L).version(0).name("yxcv").build(), //
				TaskItemEto.builder().id(50L).version(0).name("asdf").build()));
		Mockito.when(findItemUc.findAllItems()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
				.andExpect(jsonPath("$[0].id").value(list.get(0).id()))//
				.andExpect(jsonPath("$[0].email").value(list.get(0).name()))//
				.andExpect(jsonPath("$", hasSize(5)));
	}

//	@Test
	public void findAll_EmptyList() throws Exception {
		// Given
		ArrayList<TaskItemEto> list = new ArrayList<>();
		Mockito.when(findItemUc.findAllItems()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(0)));
	}

	//@Test
	public void saveItemPositive() throws Exception {

		// Given
		TaskItemEto item = TaskItemEto.builder().id(20L).version(1).name("save item").build();

		ObjectMapper objectMapper = new ObjectMapper();
		String newItemJson = objectMapper.writeValueAsString(item);
		Mockito.when(manageItemUc.saveItem(item)).thenReturn(item);

		// When
		// Then
		mockMvc.perform(post("/person/")//
				.contentType(MediaType.APPLICATION_JSON).content(newItemJson))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value(item.id()))//
				.andExpect(jsonPath("$.name").value(item.name()));
	}

	//@Test
	public void saveItemNegative() throws Exception {

		// TODO

		// Given
		ArrayList<TaskItemEto> list = new ArrayList<>();
		Mockito.when(findItemUc.findAllItems()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(0)));
	}

	//@Test
	public void deleteItemPositive() throws Exception {

		// TODO

		// Given
		ArrayList<TaskItemEto> list = new ArrayList<>();
		Mockito.when(findItemUc.findAllItems()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(0)));
	}

	//@Test
	public void deleteItemNegative() throws Exception {

		// TODO

		// Given
		ArrayList<TaskItemEto> list = new ArrayList<>();
		Mockito.when(findItemUc.findAllItems()).thenReturn(list);

		// When
		// Then
		mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
				.andExpect(jsonPath("$", hasSize(0)));
	}
}

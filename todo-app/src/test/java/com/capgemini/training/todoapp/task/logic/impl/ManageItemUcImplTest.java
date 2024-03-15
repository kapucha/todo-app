package com.capgemini.training.todoapp.task.logic.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.capgemini.training.todoapp.task.common.TaskItemEto;
import com.capgemini.training.todoapp.task.logic.ManageItemUc;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ManageItemUcImplTest {

	@Autowired
	private ManageItemUc manageItemUc;

	private static final LocalDateTime LOCAL_DATE = LocalDateTime.of(2024, 6, 1, 0, 0);
	private static final Instant DEADLINE = LOCAL_DATE.atZone(ZoneId.of("Europe/Warsaw")).toInstant();

	@Test
	public void saveItem_shouldCreateItem() {
		// Given
		TaskItemEto newItem = TaskItemEto.builder().id(1L).version(0).name("item name").completed(false)
				.deadline(DEADLINE).build();

		// When
		TaskItemEto savedItem = manageItemUc.saveItem(newItem);

		// Then
		assertThat(savedItem).isNotNull();
		assertThat(savedItem.name()).isEqualTo("item name");
	}

	@Test
	public void saveItem_shouldUpdateItem() {
//TODO
	}

	@Test
	public void deletePerson() {
//TODO
	}

	@Test
	public void deletePersonWithIdNull() {

	}// TODO

}

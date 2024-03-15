package com.capgemini.training.todoapp.task.common;

import lombok.Builder;

@Builder
public record TaskListEto (	Long id, int version, String name){}

package com.capgemini.training.todoapp.task.common;

import java.time.Instant;

import lombok.Builder;

@Builder
public record TaskItemEto (	Long id, int version, String name, boolean completed, Instant deadline) {}

package com.capgemini.training.todoapp.task.common;

import lombok.Builder;

@Builder
public record PersonEto (Long id, int version, String email) {}
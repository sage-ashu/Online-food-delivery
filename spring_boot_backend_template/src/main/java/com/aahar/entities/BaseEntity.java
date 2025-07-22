package com.aahar.entities;


import java.time.LocalDateTime;

public class BaseEntity {
	private Long id;
	private LocalDateTime isCreatedAt;
	private LocalDateTime isUpdatedAt;
	private boolean isDeleted;
}


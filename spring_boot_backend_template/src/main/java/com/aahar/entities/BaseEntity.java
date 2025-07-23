package com.aahar.entities;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	@Column(name="creation_date")
	private LocalDateTime isCreatedAt;
	@UpdateTimestamp
	@Column(name="updated_on")
	private LocalDateTime isUpdatedAt;
	private boolean isDeleted;
}


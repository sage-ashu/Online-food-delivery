package com.aahar.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ApiResponse {
 private LocalDateTime date;
 private String msg;
 public ApiResponse(String msg){
	 this.msg=msg;
	 this.date =LocalDateTime.now();
 }
}

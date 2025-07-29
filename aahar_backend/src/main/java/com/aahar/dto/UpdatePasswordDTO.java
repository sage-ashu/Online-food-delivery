package com.aahar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDTO {
	
	public String oldPassword;
	public String newPassword;
	
}

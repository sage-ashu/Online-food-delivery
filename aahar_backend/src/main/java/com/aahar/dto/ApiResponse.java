package com.aahar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//tanmay bsdk don't touch my dto 
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}

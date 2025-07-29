package com.aahar.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddOrderDTO {
	private Long customerAddressId;
	private Long reaturantId;
	List<AddOrderDetailsDTO> details=new ArrayList<>();
	
}

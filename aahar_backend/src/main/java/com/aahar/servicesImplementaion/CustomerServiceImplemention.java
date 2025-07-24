package com.aahar.servicesImplementaion;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.CustomerDao;
import com.aahar.dto.AddressDTO;
import com.aahar.services.CustomerService;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class CustomerServiceImplemention implements CustomerService {
	//now adding its CTOR DI
	public final CustomerDao customerDao;
	@Override
	public AddressDTO addAddress(AddressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}

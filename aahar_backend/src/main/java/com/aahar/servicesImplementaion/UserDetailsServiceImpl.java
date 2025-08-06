package com.aahar.servicesImplementaion;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dao.CustomerDao;
import com.aahar.entities.Customer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	public final CustomerDao customerDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerDao.findByEmail(username);
		if(customer==null)
			throw new ResourceNotFoundException("This user does not exist" + username);
		return customer;
	}
}

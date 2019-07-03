package com.kltn.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kltn.DTO.UserPrinciple;
import com.kltn.Model.User;
import com.kltn.Repository.IUserRepository;


@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private IUserRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = accountRepository.getUser(username);
		return UserPrinciple.build(user);
	}

}

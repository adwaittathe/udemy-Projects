package com.objectclass;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service 
public class UserService implements IUserService {
	


	@Override
	public User createUser(UserDetailsModel userDetails) {
		User user = new User();
		user.setEmail(userDetails.getEmail());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);		
		return user;
	}
	

}

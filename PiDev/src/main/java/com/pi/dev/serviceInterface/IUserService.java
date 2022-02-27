package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.User;

public interface IUserService {

	List<User> findAll();

	User addUser(User user);

	User updateUser(User user, Long userID);

	void deleteUserById(Long userID);
	
}

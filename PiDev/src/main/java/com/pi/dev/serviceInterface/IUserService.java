package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.User;
import com.pi.dev.service.UserNotFoundException;

public interface IUserService {

	List<User> findAll();

	User addUser(User user);

	User updateUser(User user, Long userID);

	void deleteUserById(Long userID);
	
	void addFollow(Long followedId, Long followerId);
	
	void removeFollow(Long followedId, Long followerId);
	
	void updatePassword(User user, String newPassword) ;
	
	User getByResetPasswordToken(String token);
	
	void updateResetPasswordToken(String token, String email)throws UserNotFoundException;
}

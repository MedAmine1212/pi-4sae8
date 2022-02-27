package com.pi.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.BadWordFilter;
import com.pi.dev.models.Post;
import com.pi.dev.models.User;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.IPostService;
import com.pi.dev.serviceInterface.IUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceimpl implements IUserService{

	
	@Autowired
	UserRepository userRepository;
	
	
	
	//afficher la liste des users
	@Override
	public List<User> findAll() {
		return  userRepository.findAll();
	}
	
	
	//ajouter un user
	@Override
	public User addUser(User user) {
		
	return userRepository.save(user);
	}
	
	@Override
	public User updateUser(User user, Long postId) {
		
			user.setId(postId);
			return userRepository.save(user);
		
	}
	
	//effacer un user
	@Override
	public void deleteUserById(Long userID) {
		userRepository.deleteById(userID);
		
	}
	
	
	
}

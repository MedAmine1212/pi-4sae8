package com.pi.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pi.dev.models.BadWordFilter;
import com.pi.dev.models.Post;
import com.pi.dev.models.User;
import com.pi.dev.payload.response.MessageResponse;
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
	public User updateUser(User user, Long userID) {
			user.setId(userID);
			return userRepository.save(user);
		
	}
	
	//effacer un user
	@Override
	public void deleteUserById(Long userID) {
		userRepository.deleteById(userID);
		
	}

	
	public void addFollow(Long followedId, Long followerId){
		User follower = new User(); 
		follower= userRepository.findById(followerId).get();
		

		User followed = new User(); 
		followed= userRepository.findById(followedId).get();
		if ((followed.getFollowedBy().contains(followerId)==false))
//			&&(followerId != followedId)
		{
			followed.getFollowedBy().add(followerId);
			 userRepository.save(followed);
		}
	}
	
	public void removeFollow(Long followedId, Long followerId){
		User follower = new User(); 
		follower= userRepository.findById(followerId).get();
		User followed = new User(); 
		followed= userRepository.findById(followedId).get();
		if (followed.getFollowedBy().contains(followerId)==true)
		{
			followed.getFollowedBy().remove(followerId);
			 userRepository.save(followed);
		}
	}

}

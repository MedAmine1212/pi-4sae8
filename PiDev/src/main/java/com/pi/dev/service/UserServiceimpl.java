package com.pi.dev.service;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.Subscription;
import com.pi.dev.models.TypeSubscription;
import com.pi.dev.models.User;
import com.pi.dev.repository.ContributorRepository;
import com.pi.dev.repository.SubscriptionRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.IUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceimpl implements IUserService{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ContributorRepository contributorRepository;

	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	
	//afficher la liste des users
	@Override
	public List<User> findAll() {
		return  userRepository.findAll();
	}
	

	

	//ajouter un user
	@Override
	public User addUser(User user) {
		Subscription subscription = new Subscription();
		subscription.setTypeSubscription(TypeSubscription.Silver);
		subscription.setStartDate(java.time.LocalDate.now());
		subscription.setEndDate(java.time.LocalDate.now().plusMonths(1));
		subscription.setPrice(0);
		subscription.setUser(user);
		user= userRepository.save(user);
		subscriptionRepository.save(subscription);
        return user;
	
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

	@Override
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
	@Override
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
	
	@Override
	 public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
	        User user = userRepository.findByEmail(email);
	        if (user != null) {
	        	user.setResetPasswordToken(token);
	            userRepository.save(user);
	        } else {
	            throw new UserNotFoundException("Could not find any customer with the email " + email);
	        }
	    }
	     
	 
	 
	 @Override
	    public User getByResetPasswordToken(String token) {
	        return userRepository.findByResetPasswordToken(token);
	    }
	     
	    
	    
	    @Override
	    public void updatePassword(User user, String newPassword) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        user.setPassword(encodedPassword);
	        user.setResetPasswordToken(null);
	        userRepository.save(user);
	    }
	    
	    @Override
	    public void upgradeToContributer(Long id){
	    	User user = userRepository.getById(id);
	    	Contributor contributor = new Contributor();
	    	contributor.setName(user.getUsername());
	    	contributor.setEmail(user.getEmail());
	    	contributor.setPhone(user.getPhone());
	        contributorRepository.save(contributor);

	    	
	    	
	    }
	    @Override
	    public void addRateToUser(Long idLikedUser, Long idLikeUser , Integer rate){
	    	User likedUser= userRepository.getById(idLikedUser);
	    	User likeUser= userRepository.getById(idLikeUser);

	    	if (rate ==1){
	    		likedUser.getRatedBy().put(likeUser, 1);
		        userRepository.save(likedUser);
	    	}
	    	else if(rate == -1){
	    		likedUser.getRatedBy().put(likeUser, -1);
		        userRepository.save(likedUser);

	    	}
	    	else{
	    		likedUser.getRatedBy().put(likeUser, 0);
		        userRepository.save(likedUser);
	    	}

	    	
	    }

}

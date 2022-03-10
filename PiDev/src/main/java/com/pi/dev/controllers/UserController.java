package com.pi.dev.controllers;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.Subscription;
import com.pi.dev.models.User;
import com.pi.dev.serviceInterface.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Manage users")
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;


    @PostMapping("/post")
    @ApiOperation(value = "ajouter user")
	@ResponseBody
	public User add(@RequestBody User user) {
	        return userService.addUser(user);
	    }





    @ApiOperation(value = "Update user")
	@PostMapping("updateUser/{userID}")
	@ResponseBody
	User updateUser(@RequestBody User user,@PathVariable Long userID){
		return userService.updateUser(user, userID);
	}

    @GetMapping("/getRole")
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }


    @DeleteMapping("/delete/{userID}")
    public void deleteUserById(@PathVariable Long userID) {
    	userService.deleteUserById(userID);

    }



    @ApiOperation(value = "add follow")
	@PostMapping("addfollow/{followedId}/{followerId}")
	@ResponseBody
	public void  addFollow(@PathVariable Long followedId,@PathVariable Long followerId){
		 userService.addFollow(followedId, followerId);
	}



    @ApiOperation(value = "remove follow")
  	@PostMapping("removefollow/{followedId}/{followerId}")
  	@ResponseBody
  	public void  removeFollow(@PathVariable Long followedId,@PathVariable Long followerId){
  		 userService.removeFollow(followedId, followerId);
  	}





    @ApiOperation(value = "upgrade to contributer")
    @PostMapping("/upgradeToContributer{id}")
	@ResponseBody
	public void upgradeToContributer(@PathVariable Long id) {
	         userService.upgradeToContributer(id);
	    }


    @ApiOperation(value = "add rate to user")
    @PostMapping("/addRateToUser")
	@ResponseBody
    public void addRateToUser(Long idLikedUser,  Long idlikeUser , Integer rate){
    	userService.addRateToUser(idLikedUser,idlikeUser, rate);

    }
}

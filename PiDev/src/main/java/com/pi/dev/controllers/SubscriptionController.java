package com.pi.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.Role;
import com.pi.dev.models.Subscription;
import com.pi.dev.serviceInterface.ISubscriptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Manage subscriptions")
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	ISubscriptionService subscriptionService;
	
	
	
    @PostMapping("/add")
	@ApiOperation(value = "add subscription")
	@ResponseBody
	    public Subscription add(@RequestBody Subscription subscription) {
	        return subscriptionService.addSubscription(subscription);
	    }
    
    
    
    @ApiOperation(value = "Update subscription")
	@PostMapping("updateSubscription/{SubscriptionID}")
	@ResponseBody
	Subscription updateSubscription(@RequestBody Subscription subscription,@PathVariable Long SubscriptionID){
		return subscriptionService.updateSubscription(subscription, SubscriptionID);
	}
    
    @GetMapping("/getRole")
    @ResponseBody
    public List<Subscription> findAll() {
        return subscriptionService.findAll();
    }
    

    @DeleteMapping("/delete/{SubscriptionID}")
    public void deleteSubscriptionById(@PathVariable Long SubscriptionID) {
    	subscriptionService.deleteSubscriptionById(SubscriptionID);
    
    }
}

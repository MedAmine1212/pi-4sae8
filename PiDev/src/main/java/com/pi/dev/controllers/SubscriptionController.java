package com.pi.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    
    
    @ApiOperation(value = "end subscription")
  	@PostMapping("endSubscription/{SubscriptionID}")
  	@ResponseBody
  	public void EndSubscription(@PathVariable Long SubscriptionID){
		 subscriptionService.endSubscription(SubscriptionID);

  	}
    
    
    
    
    @ApiOperation(value = "Upgrade subscription To Premium")
  	@PostMapping("ToPremium/{SubscriptionID}")
  	@ResponseBody
  	public void UpgradeToPremium(@PathVariable Long SubscriptionID){
		 subscriptionService.upgradeToPremium(SubscriptionID);
  	}
    
    
    
   
    
    
    
    @ApiOperation(value = "Upgrade subscription To Gold")
  	@PostMapping("ToGold/{SubscriptionID}")
  	@ResponseBody
  	public void upgradeToGold(@PathVariable Long SubscriptionID){
		 subscriptionService.upgradeToGold(SubscriptionID);
  	}

	// @Scheduled(cron = "@weekly")
	@Scheduled(fixedRate = 100000)
    public void upgr(){
    	subscriptionService.upgradeSupscription();
    }
}

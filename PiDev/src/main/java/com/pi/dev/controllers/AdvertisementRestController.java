package com.pi.dev.controllers;

import com.pi.dev.models.Advertisement;
import com.pi.dev.serviceInterface.IAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Manage advertisements")
@RequestMapping("/advertisement")
public class AdvertisementRestController {

	@Autowired
	IAdvertisementService advertisementService;

    //http://localhost:8085/swagger-ui/index.html

	@ApiOperation(value = "Add advertisement")
	@PostMapping("/addAdvertisement")
	@RequestBody
	Advertisement addAdvertisement(@RequestBody Advertisement ad){
		return advertisementService.addAdvertisement(ad);
	}

	@ApiOperation(value = "Delete advertisement")
	@DeleteMapping("/deleteAdvertisement/{adId}")
	@RequestBody
	Boolean deleteAd(@PathVariable Long adId){
		return advertisementService.deleteAd(adId);
	}

	@ApiOperation(value = "Find ads for user")
	@GetMapping("/findAdsForUser/{userId}")
	@RequestBody
	List<Advertisement> findAdsForUser(@PathVariable Long userId){
		return advertisementService.findAdsForUser(userId);
	}
}

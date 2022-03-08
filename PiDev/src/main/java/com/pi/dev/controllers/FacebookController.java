package com.pi.dev.controllers;

import com.pi.dev.serviceInterface.IFacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/facebook")
public class FacebookController {

	@Autowired
	private IFacebookService facebookService;

	@GetMapping("/welcome")
	public List<String> welcome() {
		List<String> urls = new ArrayList<>();
		urls.add("http://localhost:8085/facebook/generateFacebookAuthorizeUrl");
		urls.add("http://localhost:8085/facebook/connect");
		urls.add("http://localhost:8085/facebook/getUserData");
		return urls;
	}
	@GetMapping("/generateFacebookAuthorizeUrl")
	public String generateFacebookAuthorizeUrl() {
		return facebookService.generateFacebookAuthorizeUrl();
	}

	@GetMapping("/connect")
	public void generateFacebookAccessToken(@RequestParam("code") String code) {
		facebookService.generateFacebookAccessToken(code);
	}

	@GetMapping("/getUserData")
	public String getUserData() {
		return facebookService.getUserData();
	}
}

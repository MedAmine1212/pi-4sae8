package com.pi.dev.controllers;

import java.util.List;

import com.pi.dev.models.Post;
import com.pi.dev.serviceInterface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(tags = "Manage posts")
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	IPostService postService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	@ApiOperation(value = "Get posts list")
	@GetMapping("/getAllPosts")
	@ResponseBody
	public List<Post> getPosts() {
		return postService.findAll();
	}

	


}

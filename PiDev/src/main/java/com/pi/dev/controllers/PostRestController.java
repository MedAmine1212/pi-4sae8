package com.pi.dev.controllers;

import java.util.List;

import com.pi.dev.models.PostLike;
import com.pi.dev.models.Post;
import com.pi.dev.models.Rating;
import com.pi.dev.serviceInterface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(tags = "Manage posts")
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	IPostService postService;

    //http://localhost:8085/swagger-ui/index.html
	@ApiOperation(value = "Get posts list")
	@GetMapping("/getAllPosts")
	@ResponseBody
	public List<Post> getPosts() {
		return postService.findAll();
	}

	@ApiOperation(value = "Add post")
	@PostMapping("/addPost")
	Post addPost(@RequestBody Post post){
		return postService.addPost(post);
	}

	@ApiOperation(value = "Update Post")
	@PostMapping("/updatePost/{postId}")
	@ResponseBody
	Post updatePost(@RequestBody Post post,@PathVariable Long postId){
		return postService.updatePost(post, postId);
	}

	@ApiOperation(value = "Delete Post")
	@DeleteMapping("/deletePost/{postId}")
	@ResponseBody
	boolean deletePost(@PathVariable Long postId){
		return postService.deletePost(postId);
	}

	@ApiOperation(value = "Add like to Post")
	@PostMapping("/addLikeToPost")
	@ResponseBody
	boolean addLikeToPost(@RequestBody PostLike postLike){
		return postService.addLikeToPost(postLike);
	}

	@ApiOperation(value = "Remove like from post")
	@GetMapping("/removeLikeFromPost/{postId}/{userId}")
	@ResponseBody
	boolean removeListFromPost(@PathVariable Long postId,@PathVariable Long userId){
		return postService.removeLikeFromPost(postId, userId);
	}

	@ApiOperation(value = "Add rating to post")
	@PostMapping("/addRatingToPost")
	@ResponseBody
	boolean ratePost(@RequestBody Rating rating){
		return postService.ratePost(rating);
	}
	@ApiOperation(value = "Add rating to post")
	@GetMapping("/getFilteredPosts/{filterType}/{userId}/{offset}")
	@ResponseBody
	List<Post> getFilteredPosts(@PathVariable String filterType, @PathVariable Long userId, @PathVariable int offset ) {
		 return postService.getFilteredPosts(filterType, userId, offset);
	}


}

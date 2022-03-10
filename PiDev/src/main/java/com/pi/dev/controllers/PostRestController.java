package com.pi.dev.controllers;

import java.util.List;

import com.pi.dev.models.*;
import com.pi.dev.serviceInterface.IPostService;
import com.pi.dev.storage.StorageFileNotFoundException;
import com.pi.dev.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = "Manage posts")
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	IPostService postService;

	@Autowired
	StorageService storageService;

    //http://localhost:8085/swagger-ui/index.html
	@ApiOperation(value = "Get posts list")
	@GetMapping("/getAllPosts")
	@ResponseBody
	public List<Post> getPosts() {
		return postService.findAll();
	}

	@ApiOperation(value = "Search post")
	@GetMapping("/searchPosts/{text}")
	@ResponseBody
	public List<Post> searchPosts(@PathVariable String text) {
		return postService.searchPosts(text);
	}

	@ApiOperation(value = "Add post")
	@PostMapping("/addPost")
	Post addPost(@RequestBody Post post){
		return postService.addPost(post);
	}


	@ApiOperation(value = "Vote poll")
	@PostMapping("/votePoll")
	void pollVote(@RequestBody FieldVote fieldVote){
		postService.votePoll(fieldVote);
	}

	@ApiOperation(value = "Update Post")
	@PostMapping("/updatePost/{postId}")
	@ResponseBody
	Post updatePost(@RequestBody Post post, @PathVariable Long postId){
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
	boolean removeLikeFromPost(@PathVariable Long postId,@PathVariable Long userId){
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


	@ApiOperation(value = "Get post file")
	@GetMapping("/getPostFile/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}


	@ApiOperation(value = "Upload post file")
	@PostMapping("/uploadPostFile/{postId}")
	public void handleFileUpload(@PathVariable Long postId, @RequestParam("file") MultipartFile file) {

		storageService.store(file, postId);
	}

	@ApiOperation(value = "Remove post file")
	@GetMapping("/deletePostFile/{postId}/{fileName}")
	public void handleFileUpload(@PathVariable String fileName, @PathVariable Long postId) {
		storageService.deleteFile(fileName);
		postService.removeFile(fileName, postId);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/displayPieChart")
	public PostsStat pieChart() {
		return postService.getChart();
	}

}

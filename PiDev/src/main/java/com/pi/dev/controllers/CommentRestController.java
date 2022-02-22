package com.pi.dev.controllers;

import com.pi.dev.models.Comment;
import com.pi.dev.models.CommentReact;
import com.pi.dev.serviceInterface.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Manage Comments")
@RequestMapping("/comment")
public class CommentRestController {

	@Autowired
	ICommentService commentService;

    //http://localhost:8085/swagger-ui/index.html

	@ApiOperation(value = "Get filtered Comments by Post")
	@GetMapping("/getFilteredComments/{filterType}/{postId}")
	@ResponseBody
	List<Comment> getFilteredPosts(@PathVariable String filterType, @PathVariable Long postId	) {
		return commentService.getFilteredComments(filterType, postId);
	}

	@ApiOperation(value = "Add Comment")
	@PostMapping("/addComment")
	@ResponseBody
	Comment addComment(@RequestBody Comment comment){
		return commentService.addComment(comment);
	}

	@ApiOperation(value = "Update Comment")
	@PostMapping("updateComment/{commentId}")
	@ResponseBody
	Comment updateComment(@RequestBody Comment comment,@PathVariable Long commentId){
		return commentService.updateComment(comment, commentId);
	}

	@ApiOperation(value = "Delete Comment")
	@DeleteMapping("/deleteComment/{commentId}")
	@ResponseBody
	boolean deleteComment(@PathVariable Long commentId){
		return commentService.deleteComment(commentId);
	}

	@ApiOperation(value = "Add react to comment")
	@PostMapping("/addReactToComment")
	@ResponseBody
	boolean addReactToComment(@RequestBody CommentReact commentReact){
		return commentService.addReactToComment(commentReact);
	}

	@ApiOperation(value = "Remove like from post")
	@GetMapping("/removeReactFromComment/{commentId}/{userId}")
	@ResponseBody
	boolean removeListFromPost(@PathVariable Long commentId,@PathVariable Long userId){
		return commentService.removeReactFromComment(commentId, userId);
	}




}

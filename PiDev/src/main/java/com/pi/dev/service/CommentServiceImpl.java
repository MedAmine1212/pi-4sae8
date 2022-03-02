package com.pi.dev.service;

import com.pi.dev.models.*;
import com.pi.dev.repository.*;
import com.pi.dev.serviceInterface.ICommentService;
import com.pi.dev.serviceInterface.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements ICommentService {

	@Autowired
	PostRepository postRepository;


	@Autowired
	CommentReactRepository commentReactRepository;


	@Autowired
	UserRepository userRepository;

	@Autowired
	CommentRepository commentRepository;

	@Override
	public List<Comment> getFilteredComments(String filterType, Long postId) {
		List<Comment> comments =  commentRepository.findAllByCommentPost(postRepository.findById(postId).get());

		if(filterType.equals("popular")) {
			for(Comment c: comments) {
				c.setListCommentReacts(commentReactRepository.findAllByReactComment(c));
				c.setReactsCount(c.getListCommentReacts().size());
			}
			comments.sort(Comparator.comparing(Comment::getReactsCount));
			Collections.reverse(comments);
		}

		return comments;
	}
	@Override
	public Comment addComment(Comment comment) {
		if (BadWordFilter.badWordsFound(comment.getCommentText()).size() != 0){
			return null;
		} else {
			return commentRepository.save(comment);
		}
	}

	@Override
	public Comment updateComment(Comment comment, Long commentId) {
		BadWordFilter.loadConfigs();
		if (BadWordFilter.badWordsFound(comment.getCommentText()).size() != 0){
			return null;
		} else {
			comment.setCommentId(commentId);
			return commentRepository.save(comment);
		}
	}

	@Override
	public boolean deleteComment(Long commentId) {
		try {
			postRepository.deleteById(commentId);
			return true;
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return  false;
		}
	}

	@Override
	public boolean addReactToComment(CommentReact commentReact) {
		try {
			commentReactRepository.save(commentReact);
			return true;
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return  false;
		}
	}

	@Override
	public boolean removeReactFromComment(Long commentId, Long userId) {
		try {
			commentReactRepository.removeReactFromPost(commentId, userId);
			return true;
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return  false;
		}
	}
}
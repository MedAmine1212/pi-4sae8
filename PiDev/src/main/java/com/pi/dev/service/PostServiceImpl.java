package com.pi.dev.service;

import java.util.List;
import java.util.Optional;

import com.pi.dev.models.BadWordFilter;
import com.pi.dev.models.PostLike;
import com.pi.dev.models.Post;
import com.pi.dev.models.Rating;
import com.pi.dev.repository.LikeRepository;
import com.pi.dev.repository.PostRepository;
import com.pi.dev.repository.RatingRepository;
import com.pi.dev.serviceInterface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {

	@Autowired
	PostRepository postRepository;


	@Autowired
	LikeRepository likeRepository;


	@Autowired
	RatingRepository ratingRepository;


	@Override
	public List<Post> findAll() {
		return  postRepository.findAll();
	}

	@Override
	public Post addPost(Post post) {
		if (!BadWordFilter.badWordsFound(post.getPostContent()).isEmpty() || BadWordFilter.badWordsFound(post.getPostTitle()).isEmpty()){
			return null;
		} else {
			return postRepository.save(post);
		}
	}

	@Override
	public Post updatePost(Post post, Long postId) {
		if (!BadWordFilter.badWordsFound(post.getPostContent()).isEmpty() || BadWordFilter.badWordsFound(post.getPostTitle()).isEmpty()){
			return null;
		} else {
			post.setPostId(postId);
			return postRepository.save(post);
		}
	}

	@Override
	public boolean deletePost(Long postId) {
		try {
			postRepository.deleteById(postId);
			return true;
		} catch (Exception e) {
			return  false;
		}
	}

	@Override
	public boolean addLikeToPost(PostLike like) {
		try {
			likeRepository.save(like);
			return true;
		} catch (Exception e) {
			return  false;
		}
	}

	@Override
	public boolean removeListFromPost(Long postId, Long userId) {
		try {
			likeRepository.removeLikeFromPost(postId, userId);
			return true;
		} catch (Exception e) {
			return  false;
		}
	}

	@Override
	public boolean ratePost(Rating rating) {
		try {
			ratingRepository.save(rating);
			return true;
		} catch (Exception e) {
			return  false;
		}
	}
}
package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.PostLike;
import com.pi.dev.models.Post;
import com.pi.dev.models.Rating;

public interface IPostService {

	List<Post> findAll();

	Post addPost(Post post);

	Post updatePost(Post post, Long postId);

	boolean deletePost(Long postId);

	boolean addLikeToPost(PostLike like);

	boolean removeListFromPost(Long postId, Long userId);

	boolean ratePost(Rating rating);

}

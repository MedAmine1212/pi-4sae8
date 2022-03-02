package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.PostLike;
import com.pi.dev.models.Post;
import com.pi.dev.models.Rating;

public interface IPostService {

	List<Post> findAll();

	List<Post> searchPosts(String text);

	List<Post> getFilteredPosts(String filterType, Long userId, int offset);

	Post addPost(Post post);

	Post updatePost(Post post, Long postId);

	boolean deletePost(Long postId);

	boolean addLikeToPost(PostLike postLike);

	boolean removeLikeFromPost(Long postId, Long userId);

	boolean ratePost(Rating rating);

//	List<String> getPostFiles (Long postId);

}

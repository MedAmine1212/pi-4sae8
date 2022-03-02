package com.pi.dev.serviceInterface;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.pi.dev.models.PostLike;
import com.pi.dev.models.Post;
import com.pi.dev.models.Rating;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

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

	void addFileToPost(String originalFilename, Long postId);
}

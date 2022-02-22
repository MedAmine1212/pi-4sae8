package com.pi.dev.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pi.dev.models.*;
import com.pi.dev.repository.*;
import com.pi.dev.serviceInterface.IKeyWordService;
import com.pi.dev.serviceInterface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	UserRepository userRepository;


	@Autowired
	IKeyWordService keyWordService;


	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	CommentRepository commentRepository;


	@Override
	public List<Post> findAll() {
		return  postRepository.findAll();
	}


	List<String> getWordByUser(User user) {
		List<Comment> commentsByUser = commentRepository.findAllByCommentOwner(user);
		List<String> wordList = new ArrayList<>();
		for(Comment cm: commentsByUser) {
			String[] words=cm.getCommentText().split("\\s");
			for (String w: words) {
				if (!wordList.contains(w.toLowerCase())) {
					if ( keyWordService.checkIfKeyWordExists(w))
						wordList.add(w.toLowerCase());
				}
			}

		}
		return wordList;
	}

	@Override
	public List<Post> getFilteredPosts(String filterType, Long userId, int offset) {

		List<Post> posts;

		if(filterType.equals("recommended")) {
			User user = userRepository.findById(userId).get();
			List<String> wordList = getWordByUser(user);
			return filterPosts(wordList);

		} else if(filterType.equals("popular")) {

			posts = getPostsPage(offset, 10);
			for(Post p: posts) {
				p.setLikesCount(0);
				List<PostLike> postLikes = (likeRepository.findAllByLikePost(p));
				for(PostLike pl : postLikes) {
					if (pl.getTypeLike().equals(TypeLike.UP)) {
						p.setLikesCount(p.getLikesCount()+1);
					} else {
						p.setLikesCount(p.getLikesCount()-1);
					}
				}
			}
			if (!posts.isEmpty()) {
				posts.sort(Comparator.comparing(Post::getLikesCount));
				Collections.reverse(posts);
			}
			return posts;
		} else {

			posts = getPostsPage(offset, 10);
			return posts;
		}
	}
	private List<Post> filterPosts(List<String> wordList) {
		List<Post> posts = findAll();
		List<Post> finalPosts = new ArrayList<Post>();
		for(Post p: posts) {
			boolean keepIt = false;
			for(String word: wordList) {
				if (p.getPostContent().contains(word) || p.getPostTitle().contains(word)) {
					keepIt = true;
					break;
				}
			}
			if(keepIt) {
				finalPosts.add(p);
			}
		}
		return finalPosts;
	}
	private List<Post> getPostsPage(int offset, int postCount) {
		if (offset == 0) {
			return postRepository.getFirstTenPosts(postCount);
		} else {
			return postRepository.findAll(PageRequest.of(offset, postCount)).getContent();

		}
	}
	@Override
	public Post addPost(Post post) {
		BadWordFilter.loadConfigs();
		if (BadWordFilter.badWordsFound(post.getPostContent()).size() != 0 || BadWordFilter.badWordsFound(post.getPostTitle()).size() != 0){
			return null;
		} else {
			String titleAndPost = post.getPostTitle()+" "+post.getPostContent();
			String[] words=titleAndPost.split("\\s");
			for (String w: words) {
				if(w.length() > 3)
					keyWordService.addWord(w);
			}

			}
			return postRepository.save(post);
		}

	@Override
	public Post updatePost(Post post, Long postId) {

		BadWordFilter.loadConfigs();
		if (BadWordFilter.badWordsFound(post.getPostContent()).size() != 0 || BadWordFilter.badWordsFound(post.getPostTitle()).size() != 0){
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
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return  false;
		}
	}

	@Override
	public boolean addLikeToPost(PostLike postLike) {
		try {
			likeRepository.save(postLike);
			return true;
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return  false;
		}
	}

	@Override
	public boolean removeLikeFromPost(Long postId, Long userId) {
		try {
			likeRepository.removeLikeFromPost(postId, userId);
			return true;
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return  false;
		}
	}

	@Override
	public boolean ratePost(Rating rating) {
		try {
			ratingRepository.save(rating);
			return true;
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return  false;
		}
	}

//	@Override
//	public List<String> getPostFiles(Long postId) {
//		return postRepository.findFilesByPost(postId);
//	}
}
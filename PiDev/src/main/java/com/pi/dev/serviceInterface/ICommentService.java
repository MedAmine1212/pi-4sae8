package com.pi.dev.serviceInterface;

import com.pi.dev.models.Comment;
import com.pi.dev.models.CommentReact;
import com.pi.dev.models.PostLike;
import com.pi.dev.models.Rating;

import java.util.List;

public interface ICommentService {

	List<Comment> findAll();

	List<Comment> getFilteredComments(String filterType, Long postId, int offset);

	Comment addComment(Comment comment);

	Comment updateComment(Comment comment, Long commentId);

	boolean deleteComment(Long commentId);

	boolean addReactToComment(CommentReact commentReact);

	boolean removeReactFromComment(Long commentId, Long userId);

}

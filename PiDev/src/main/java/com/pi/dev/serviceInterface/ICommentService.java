package com.pi.dev.serviceInterface;

import com.pi.dev.models.*;

import java.util.List;

public interface ICommentService {

	List<Comment> getFilteredComments(String filterType, Long postId, int offset);

	Comment addComment(Comment comment);

	Comment updateComment(Comment comment, Long commentId);

	boolean deleteComment(Long commentId);

	boolean addReactToComment(CommentReact commentReact);

	boolean removeReactFromComment(Long commentId, Long userId);

}

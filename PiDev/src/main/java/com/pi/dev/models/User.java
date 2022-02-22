package com.pi.dev.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
@Entity
@Data
public class User implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy="postOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Post> listPosts;


	@JsonIgnore
	@OneToMany(mappedBy="commentOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comment> listComments;

	@JsonIgnore
	@OneToMany(mappedBy="likeOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PostLike> listPostLikes;

	@JsonIgnore
	@OneToMany(mappedBy="ratingOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Rating> listRatings;


	@JsonIgnore
	@OneToMany(mappedBy="reactOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<CommentReact> listCommentReacts;


	@JsonIgnore
	@OneToMany(mappedBy="messageOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Message> listMessages;


	@ManyToOne
	private Room actualRoom;
}

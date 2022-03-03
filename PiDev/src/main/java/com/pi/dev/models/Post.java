package com.pi.dev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class Post implements Serializable{
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;

	@Temporal(TemporalType.DATE)
	private Date postDate;
	private String postTitle;
	private String postContent;

	@Transient
	private int likesCount;

	@JsonIgnore
	@ElementCollection
	private List<String> postFiles;

	@ManyToOne
	private User postOwner;

	@JsonIgnore
	@OneToMany(mappedBy="commentPost", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Comment> listComments;


	@JsonIgnore
	@OneToMany(mappedBy="likePost", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<PostLike> listPostLikes;


	@JsonIgnore
	@OneToOne(mappedBy="postPoll", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	private PostPoll poll;

	@JsonIgnore
	@OneToMany(mappedBy="ratingPost", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Rating> listRating;
}

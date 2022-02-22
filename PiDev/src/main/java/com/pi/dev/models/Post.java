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
	@OneToMany(mappedBy="commentPost", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comment> listComments;


	@JsonIgnore
	@OneToMany(mappedBy="likePost", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PostLike> listPostLikes;


	@JsonIgnore
	@OneToMany(mappedBy="ratingPost", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Rating> listRating;
}

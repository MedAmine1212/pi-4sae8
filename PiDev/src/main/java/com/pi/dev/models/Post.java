package com.pi.dev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class Post implements Serializable {
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
	@ElementCollection
	private List<String> postFiles;

	@JsonIgnore
	@ManyToOne
	private User postOwner;

	@OneToMany(mappedBy="post", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comment> listComments;


	@OneToMany(mappedBy="post", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PostLike> listLikes;


	@OneToMany(mappedBy="post", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Rating> listRating;

}

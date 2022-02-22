package com.pi.dev.models;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Inheritance(strategy=InheritanceType.JOINED)
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
	

	@OneToMany(mappedBy="postOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Post> listPosts;


	@OneToMany(mappedBy="commentOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comment> listComments;


	@OneToMany(mappedBy="likeOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PostLike> listLikes;

	@OneToMany(mappedBy="ratingOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Rating> listRatings;


	@OneToMany(mappedBy="reactOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<React> listReacts;


	@OneToMany(mappedBy="messageOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Message> listMessages;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Certification> certif;
	
	@OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL,mappedBy = "createdBy")
	private List<Meeting> meetings;
	
	@OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL,mappedBy = "createdBy")
	private List<Reclamation> reclamations;
}

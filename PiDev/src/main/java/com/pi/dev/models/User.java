package com.pi.dev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.pi.dev.models.Role;

import lombok.*;

@Data
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username"}),
		@UniqueConstraint(columnNames = {"email"})
})
@Entity
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private int phone;
	private String email;
	private Date birthDate;
	
	
	
	  public User(String username, String email, String password) {
		    this.userName = username;
		    this.email = email;
		    this.password = password;
		  }

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
	private Set<Role> roles;
	
	@OneToOne
	private Subscription subscription; 
	
	
	@OneToOne(mappedBy = "userContributor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Contributor contributorAccount;

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
}

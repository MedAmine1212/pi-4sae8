package com.pi.dev.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;
  private String firstName;
  private String lastName;
  private int phone;
  private int rate;
  private boolean state;
  

  private String resetPasswordToken;
  
	@JsonIgnore
  @ElementCollection
  private Map<User,Integer> ratedBy;
  
  	@JsonIgnore
	@ElementCollection
	private List<Long> followedBy;
  
  
  
  public boolean getState() {
	    return state;
	  }
  
  
  

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password, boolean state) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.state = state;
  }

  public User(String username, String email, String password) {
	    this.username = username;
	    this.email = email;
	    this.password = password;
	  }




	@OneToOne
	private Subscription subscription; 
  	@JsonIgnore
	@OneToOne(mappedBy = "userContributor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Contributor contributorAccount;
  	@JsonIgnore
	@OneToMany(mappedBy="postOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Post> listPosts;

  	@JsonIgnore
	@OneToMany(mappedBy="commentOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comment> listComments;

  	@JsonIgnore
	@OneToMany(mappedBy="likeOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PostLike> listLikes;
	
	
  	@JsonIgnore
	@OneToMany(mappedBy="ratingOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Rating> listRatings;

  	@JsonIgnore
	@OneToMany(mappedBy="reactOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<React> listReacts;

  	@JsonIgnore
	@OneToMany(mappedBy="messageOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Message> listMessages;
  	@JsonIgnore
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Certification> certif;
  	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL,mappedBy = "createdBy")
	private List<Meeting> meetings;
  	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL,mappedBy = "createdBy")
	private List<Reclamation> reclamations;

}

package com.pi.dev.models;

import java.io.Serializable;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi.dev.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
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
		@OneToMany(mappedBy="reactOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
		private List<CommentReact> listReacts;


		@JsonIgnore
		@OneToMany(mappedBy="messageOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
		private List<Message> listMessages;

		@JsonIgnore
		@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Certification> certif;

		@ManyToOne
		private Room actualRoom;

		private boolean isRoomOwner;


		@JsonIgnore
		@OneToOne(mappedBy = "userContributor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
		private Contributor contributorAccount;

		@JsonIgnore
		@OneToOne(mappedBy = "userTrainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
		private Trainer trainerAccount;

		@OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL,mappedBy = "createdBy",  orphanRemoval = true)
		private List<Meeting> meetings;

		@OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL,mappedBy = "createdBy",  orphanRemoval = true)
		private List<Reclamation> reclamations;

	@JsonIgnore
	@OneToMany(mappedBy="userVote", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<FieldVote> listVotes;



	@OneToOne
	CharitySubscription CharitySubscription ;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Events> Evenements ;



    @Enumerated(EnumType.STRING)
	private ReputationLevel reputationLevel;

	private int reputationPoints;






		@NotFound(action = NotFoundAction.IGNORE)
		@OneToMany(mappedBy="user", cascade=CascadeType.ALL ,fetch = FetchType.LAZY)
	    private List<Candidacy> listCandidacyUser;

		@NotFound(action = NotFoundAction.IGNORE)
		@JsonIgnore
		@ElementCollection(fetch = FetchType.LAZY)
		private List<String> skills;

		@NotFound(action = NotFoundAction.IGNORE)
		@JsonIgnore
		@ManyToMany
		@JoinTable(
		  name = "fav_job",
		  joinColumns = @JoinColumn(name = "user_id"),
		  inverseJoinColumns = @JoinColumn(name = "joboffer_id"))
		Set<JobOffer> favJobs;
		@Enumerated(EnumType.STRING)
		private Locations userLocation;


}

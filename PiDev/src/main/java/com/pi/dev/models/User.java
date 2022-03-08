package com.pi.dev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi.dev.models.Role;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "User", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username"}),
		@UniqueConstraint(columnNames = {"email"})
})

@Inheritance(strategy=InheritanceType.JOINED)
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
		@Temporal(TemporalType.DATE)
		private Date birthDate;


	    @ElementCollection
		private List<String> searchHistory;

		@ElementCollection
		private List<String> skills;

		/* @ElementCollection
		private List<String> skills; */

		@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JoinTable(name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
		private Set<Role> roles;


		@OneToOne
		private Subscription subscription;


	    @JsonIgnore
		@OneToMany(mappedBy="postOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
		private List<Post> listPosts;


		@JsonIgnore
		@OneToMany(mappedBy="commentOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
		private List<Comment> listComments;


		@JsonIgnore
		@OneToMany(mappedBy="likeOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
		private List<PostLike> listLikes;

		@JsonIgnore
		@OneToMany(mappedBy="ratingOwner", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
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





    @Enumerated(EnumType.STRING)
	private ReputationLevel reputationLevel;

	private int reputationPoints;





}

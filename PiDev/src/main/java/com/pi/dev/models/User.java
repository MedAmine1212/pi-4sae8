package com.pi.dev.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
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
		
		
		@NotFound(action = NotFoundAction.IGNORE)
		@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	    private List<Candidacy> listCandidacyUser;
}

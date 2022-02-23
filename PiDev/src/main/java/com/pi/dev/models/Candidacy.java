package com.pi.dev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidacy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotFound(action = NotFoundAction.IGNORE)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long candidacyId;
	@Temporal(TemporalType.DATE)
	private Date candidacyDate;
	private int candidacyStatus =1;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@MapsId("jobOfferId")
	@JoinColumn(name="jobOffer_id")
    private JobOffer jobOffer;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="user_id")
    private User user;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(mappedBy = "candidacy")
	@MapsId("interviewId")
	@JoinColumn(name="interview_id")
	private Interview interview;
	

	
	

}

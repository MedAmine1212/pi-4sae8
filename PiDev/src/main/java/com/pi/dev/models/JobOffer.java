package com.pi.dev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobOffer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	private String jobName;
	private String jobDescription;
	@Temporal(TemporalType.DATE)
	private Date jobPostDate;
	@Temporal(TemporalType.DATE)
	private Date jobExpirationDate;
	private int jobAvailable;
	@Enumerated(EnumType.STRING)
	private Locations location;

	@NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
	@JoinColumn(name="contributor_id")
    private Contributor contributor;
    
	
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy="jobOffer", cascade=CascadeType.ALL)
    private List<Candidacy> listCandidacyOffer;
    
    
    
    
	
	

}

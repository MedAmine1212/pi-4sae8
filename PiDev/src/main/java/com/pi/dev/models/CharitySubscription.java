package com.pi.dev.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class CharitySubscription {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCharity;
	
	private String Name ;
	private String LastName ;
	
	@Temporal(TemporalType.DATE)
	private Date Birthdate ;
	
	private int CIN ;
	
	@Enumerated(EnumType.STRING)
	private CivilStatus CivilStatus ; 
	
	private int NbrChildren ;
	
	@Enumerated(EnumType.STRING)
	private EducationLevel EducationLevel ; 
	
	@Enumerated(EnumType.STRING)
	private ScientificCertificate ScientificCertificate ;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL , mappedBy="CharitySubscriptions")
	private Set<User> users ;
	
	@JsonIgnore
	@ManyToOne
	Jackpots cagnotte;

	


	

}

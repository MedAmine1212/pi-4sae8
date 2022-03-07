package com.pi.dev.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(length = 8)
	private int CIN ;
	
	@Enumerated(EnumType.STRING)
	private health health ;
	
	@Enumerated(EnumType.STRING)
	private CivilStatus CivilStatus ; 
	
	private int NbrChildren ;
	
	private int NbrChildrenMinor ;
	
	private int NbrChildrenHandicap ;
	
	@Enumerated(EnumType.STRING)
	private EducationLevel EducationLevel ; 
	
	@Enumerated(EnumType.STRING)
	private ScientificCertificate ScientificCertificate ;
	
	private int score; 
	
	@JsonIgnore
	@OneToOne
	private User user ;
	
	@JsonIgnore
	@ManyToOne
	Jackpots cagnotte;

	


	

}

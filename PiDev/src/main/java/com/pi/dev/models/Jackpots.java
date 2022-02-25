package com.pi.dev.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Jackpots {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJackpot;
	
	private float amount ;
	
	@Temporal(TemporalType.DATE)
	private Date TransactionDate;
	
	@Temporal(TemporalType.DATE)
	private Date OpeningDate ;
	
	@Temporal(TemporalType.DATE)
	private Date ClosingDate ;
	
	private int Rib;
	
	private float score;
	
	

	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL , mappedBy="cagnottes")
	private Set<Contributor> contributers ;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL , mappedBy="cagnotte")
	private Set<CharitySubscription> charitySubscriptions ;
	
	
	
}

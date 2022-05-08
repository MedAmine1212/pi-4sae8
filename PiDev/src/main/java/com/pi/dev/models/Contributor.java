package com.pi.dev.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contributor")
public class Contributor   {
	

	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long contributorId;
	 private String name;
	 private String adress;
	 private String domain;
	 private String legalStatus;
	 private int phone;
	 private String email;
	 
		@Enumerated(EnumType.STRING)
		private TypeContributor TypeContributor;
	
		
		@OneToOne(cascade = CascadeType.DETACH)
	    private User userContributor;
	

}

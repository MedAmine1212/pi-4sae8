package com.pi.dev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
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
public class Contributor extends User  {
	

	
	
	 private String adress;
	 private String domain;
	 private String legalStatus;
	
	 
		@Enumerated(EnumType.STRING)
		private TypeContributor TypeContributor;
	
	

}

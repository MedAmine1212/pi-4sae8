package com.pi.dev.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
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




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adress;
	private String domain;
	private String legalStatus;


	




	@OneToMany(mappedBy="contributor", cascade=CascadeType.ALL)
	private List<JobOffer> listJobOffers;




}

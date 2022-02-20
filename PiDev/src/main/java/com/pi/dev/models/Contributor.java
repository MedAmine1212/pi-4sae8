package com.pi.dev.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contributor extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int contributorId;
	private String contributorName;
	@OneToMany(mappedBy="contributor", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<JobOffer> listJobOffers;
}

package com.pi.dev.models;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contributor implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contributorId;

    private String adress;
    private String domain;
    private String legalStatus;


    @Enumerated(EnumType.STRING)
    private TypeContributor TypeContributor;

   
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(cascade = CascadeType.DETACH)
    private User userContributor;

    @NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(mappedBy="contributor", cascade=CascadeType.ALL)
	private List<JobOffer> listJobOffers;




}

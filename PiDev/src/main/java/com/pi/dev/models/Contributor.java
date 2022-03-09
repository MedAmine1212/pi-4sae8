package com.pi.dev.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Contributor implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contributorId;

    private String adress;
    private String domain;
    private String legalStatus;
    private Date disponibiltyStart;
    private Date disponibiltyOver;


    @Enumerated(EnumType.STRING)
    private TypeContributor TypeContributor;

    @JsonIgnore
    @OneToMany(mappedBy = "advertisementOwner", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Advertisement> advertisements;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(cascade = CascadeType.DETACH)
    private User userContributor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "consultedBy")
    private List<Meeting> meetings;

    @ManyToOne
    Jackpots cagnottes ;

    @NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(mappedBy="contributor", cascade=CascadeType.ALL)
	private List<JobOffer> listJobOffers;

}
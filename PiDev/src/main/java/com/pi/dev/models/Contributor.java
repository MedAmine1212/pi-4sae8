package com.pi.dev.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Date disponibiltyStart;
    private Date disponibiltyOver;


    @Enumerated(EnumType.STRING)
    private TypeContributor TypeContributor;

    @JsonIgnore
    @OneToMany(mappedBy="advertisementOwner", fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
    private List<Advertisement> advertisements;

    @OneToOne(cascade = CascadeType.DETACH)
    private User userContributor;

    @OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL,mappedBy = "consultedBy")
    private List<Meeting> meetings;



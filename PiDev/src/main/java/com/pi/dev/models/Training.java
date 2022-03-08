package com.pi.dev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Training implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingId;
	@Temporal(TemporalType.DATE)
    private Date startDate;
	@Temporal(TemporalType.DATE)
    private Date endDate;
    private String Subject;
    private String domain;
    private int maxNbrParticipants;
    
    @ElementCollection
    private Map<User,Boolean> satisfaction;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
	private Quiz quiz;

    @OneToMany(mappedBy = "training",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Certification> certif;



    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    private Trainer trainer;
    

}

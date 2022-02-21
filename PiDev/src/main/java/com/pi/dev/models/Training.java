package com.pi.dev.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Training implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingId;
    private Date startDate;
    private Date endDate;
    private String Subject;
    private int maxNbrParticipants;

    
    
    
    @OneToMany(mappedBy = "training",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Certification> certif;
}

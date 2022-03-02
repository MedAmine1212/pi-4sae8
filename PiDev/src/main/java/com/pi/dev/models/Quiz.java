package com.pi.dev.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Quiz implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;
    private float score;
    private boolean isActive;
    
    
    @OneToMany(mappedBy="quiz",cascade = CascadeType.ALL)
   	private List<Questions> questions;
    
    @JsonIgnore
    @OneToOne(mappedBy="quiz")
	private Training training;
}

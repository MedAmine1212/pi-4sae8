package com.pi.dev.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="Certification")
@Data
@ToString
public class Certification implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @EmbeddedId
	private UserTrainingKey id;
    
    @ManyToOne()
	@MapsId("userId")
	@JoinColumn(name="user_id")
	private User user;
    
    @ManyToOne()
	@MapsId("trainingId")
	@JoinColumn(name="training_id")
	private Training training;
    
	private double score;

    
    

}

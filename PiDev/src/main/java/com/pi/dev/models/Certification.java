package com.pi.dev.models;

import java.awt.image.BufferedImage;
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="Certification")
@Data
public class Certification implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn( name = "user_id" )
    @ToString.Exclude
    @JsonProperty( access = Access.WRITE_ONLY )
	private User user;
    
    @ManyToOne
    @JoinColumn( name = "training_id" )
    @ToString.Exclude
    @JsonProperty( access = Access.WRITE_ONLY )
	private Training training;
    
	private double score;
	private Boolean Succeeded;

	

}

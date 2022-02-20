package com.pi.dev.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class UserTrainingKey implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name="user_id")
	private Long userId;
	
	@Column(name="training_id")
	private Long trainingId;

}

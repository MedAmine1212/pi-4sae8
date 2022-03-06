package com.pi.dev.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class Trainer implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainerId;

    private String domain;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.DETACH)
    private User userTrainer;

    
    @OneToMany(mappedBy="trainer",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings;

   
    @Enumerated(EnumType.STRING)
	private ReputationLevel reputationLevel;

	private int reputationPoints;


}
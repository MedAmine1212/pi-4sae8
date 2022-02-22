package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Certification implements Serializable {

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

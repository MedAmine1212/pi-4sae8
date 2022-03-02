package com.pi.dev.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class UserTrainingKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="user_id")
    private Long userId;

    @Column(name="training_id")
    private Long trainingId;

}

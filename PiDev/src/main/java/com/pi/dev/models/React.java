package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class React implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reactId;


    @JsonIgnore
    @ManyToOne
    private Comment comment;


    @ManyToOne
    @JsonIgnore
    private User reactOwner;


    @Enumerated(EnumType.STRING)
    private TypeReact typeReact;
}

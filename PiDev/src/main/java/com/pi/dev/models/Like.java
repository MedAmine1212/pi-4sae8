package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Like implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;


    @JsonIgnore
    @ManyToOne
    private Post post;


    @ManyToOne
    @JsonIgnore
    private User likeOwner;


    @Enumerated(EnumType.STRING)
    private TypeLike typeLike;
}

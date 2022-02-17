package com.pi.dev.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @JsonIgnore
    @ManyToOne
    private Post post;


    @ManyToOne
    @JsonIgnore
    private User ratingOwner;

    private int rating;

}

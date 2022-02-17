package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;


    @JsonIgnore
    @ManyToOne
    private Post post;


    @ManyToOne
    @JsonIgnore
    private User commentOwner;

    @OneToMany(mappedBy="comment", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<React> listReacts;
}

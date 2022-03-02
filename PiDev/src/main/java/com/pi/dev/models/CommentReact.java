package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class CommentReact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentReactId;


    @ManyToOne
    private Comment reactComment;


    @ManyToOne
    private User reactOwner;


    @Enumerated(EnumType.STRING)
    private TypeReact typeReact;
}

package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentText;

    @ManyToOne
    private Post commentPost;


    @ManyToOne
    private User commentOwner;

    @JsonIgnore
    @OneToMany(mappedBy="reactComment", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<CommentReact> listCommentReacts;

    @Transient
    private int reactsCount;
}

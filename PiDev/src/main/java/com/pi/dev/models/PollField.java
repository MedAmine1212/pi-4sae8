package com.pi.dev.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class PollField implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollFieldId;

    @ManyToOne
    private PostPoll postPoll;

    private String field;

    @OneToMany(mappedBy="voteField", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    private List<FieldVote> votes;



}

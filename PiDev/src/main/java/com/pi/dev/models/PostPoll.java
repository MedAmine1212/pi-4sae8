package com.pi.dev.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class PostPoll implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postPollId;

    @OneToOne
    private Post postPoll;

    @OneToMany(mappedBy="postPoll", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    private List<PollField> fields;
}

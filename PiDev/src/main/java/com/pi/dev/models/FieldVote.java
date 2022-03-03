package com.pi.dev.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class FieldVote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollFieldId;

    @ManyToOne
    private PollField voteField;

    @ManyToOne
    private User userVote;



}

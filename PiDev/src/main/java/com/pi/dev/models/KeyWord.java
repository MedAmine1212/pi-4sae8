package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class KeyWord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyWordId;
    private String keyWord;

    @Temporal(TemporalType.DATE)
    private Date addedDate;
    private int count;
}

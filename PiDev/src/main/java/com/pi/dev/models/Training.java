package com.pi.dev.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Training implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingId;
    @OneToMany(mappedBy = "training",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Certification> certif;


}

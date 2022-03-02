package com.pi.dev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.ToString;
@Entity
@Table(name="Meeting")
@Data
@ToString
public class Meeting implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	@Temporal(TemporalType.DATE)
	private Date date;
	@Enumerated(EnumType.STRING)
	private State state;
	@ManyToOne
	private User createdBy;
	@ManyToOne
	private Contributor consultedBy;
	@OneToMany
	(fetch = FetchType.LAZY, cascade= CascadeType.ALL,mappedBy = "meeting")
	private List<Reclamation> reclamations;
	
}

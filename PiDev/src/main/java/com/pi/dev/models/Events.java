package com.pi.dev.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString

public class Events {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvents;
	
	private int NbrParticipant ;
	private int MaxNbrParticipant ;
	
	@Temporal(TemporalType.DATE)
	private Date StartDate ;
	
	@Temporal(TemporalType.DATE)
	private Date EndDate;
	
	private String Name ;
	private String Topic ;
	private String location;
	private String Image ;
	private Places Places ;
	
	@JsonIgnore
	@OneToOne(mappedBy="events" , cascade = CascadeType.ALL, orphanRemoval=true)
	private JackpotEvents jackpotEvents ;
	
	@JsonIgnore
	@ManyToMany(mappedBy="Evenements", cascade = CascadeType.ALL)
	private Set<User> users ;
	
	@JsonIgnore
	@ManyToOne 
	Contributor contributers ;
	
	
	
	

}

package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Room implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;

	private String subject;
	private String postContent;

	@OneToOne
	private User roomOwner;

	@JsonIgnore
	@OneToMany(mappedBy="room", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Message> messages;


	@JsonIgnore
	@OneToMany(mappedBy="actualRoom", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<User> users;

}

package com.pi.dev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

	@JsonIgnore
	@OneToMany(mappedBy="room", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Message> messages;


	@JsonIgnore
	@OneToMany(mappedBy="actualRoom", fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	private List<User> users;

}

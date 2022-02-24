package com.pi.dev.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;




@Entity
@Table(name = "contributor")
@Data
public class Contributor extends User  {
	
	 private String adress;	
	 private String legalStatus;
	 private Date disponibiltyStart;
	 private Date disponibiltyOver;
	 @Enumerated(EnumType.STRING)
	 private TypeContributor TypeContributor;
	 @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,mappedBy = "consultedBy")
	 @NotFound(action = NotFoundAction.IGNORE)
	 private List<Meeting> meetings;
}

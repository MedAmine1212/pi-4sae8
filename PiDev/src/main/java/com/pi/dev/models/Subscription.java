package com.pi.dev.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
@ToString
@Entity
@Table(name = "Subscription" )
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSubscription")

    private long subscriptionId;
	
	private Date startDate;
	private Date endDate;
	private float price;
	
	@Enumerated(EnumType.STRING)
	private TypeSubscription TypeSubscription;

	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne
	private User user ; 
	
}

package com.pi.dev.models;

import java.util.List;

public class EventResponse {
	
	private List<String> name;
	private List<Integer> nbr;
	
	public List<String> getName() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}
	public List<Integer> getNbr() {
		return nbr;
	}
	public void setNbr(List<Integer> nbr) {
		this.nbr = nbr;
	}


}

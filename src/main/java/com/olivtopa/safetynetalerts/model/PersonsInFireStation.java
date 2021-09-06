package com.olivtopa.safetynetalerts.model;

import java.util.List;

public class PersonsInFireStation {
	
	private int nbAdults;
	private int nbChildren;
	private List<Object> persons;
	
	public List<Object> getPersons() {
		return persons;
	}

	public void setPersons(List<Object> persons) {
		this.persons = persons;
	}

	public int getNbChildren() {
		return nbChildren;
	}

	public void setNbChildren(int nbChildren) {
		this.nbChildren = nbChildren;
	}

	public int getNbAdults() {
		return nbAdults;
	}

	public void setNbAdults(int nbAdults) {
		this.nbAdults = nbAdults;
	}

}

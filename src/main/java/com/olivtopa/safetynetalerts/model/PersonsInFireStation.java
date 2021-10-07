package com.olivtopa.safetynetalerts.model;

import java.util.List;

public class PersonsInFireStation {
	
	private int nbAdults;
	private int nbChildren;
	private List<PersonInFireStation> persons;
	
	public List<PersonInFireStation> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonInFireStation> persons) {
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

	@Override
	public String toString() {
		return "PersonsInFireStation [nbAdults=" + nbAdults + ", nbChildren=" + nbChildren + ", persons=" + persons
				+ "]";
	}

}

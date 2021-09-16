package com.olivtopa.safetynetalerts.model;

import java.util.List;

public class FloodAddress {

	private String address;
	private List<FloodPerson> floodPerson;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<FloodPerson> getFloodPerson() {
		return floodPerson;
	}

	public void setFloodPerson(List<FloodPerson> floodPerson) {
		this.floodPerson = floodPerson;
	}

}

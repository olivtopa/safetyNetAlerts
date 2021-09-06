package com.olivtopa.safetynetalerts.model;

import java.time.LocalDate;

public class PersonInFireStation {
	
	private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}	



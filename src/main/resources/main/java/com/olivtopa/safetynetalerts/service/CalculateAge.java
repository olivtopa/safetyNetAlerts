package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;

public class CalculateAge {
	
	

	public int calculateAge(LocalDate birthdate, LocalDate currentDate) {
		return Period.between(birthdate, currentDate).getYears();

	}

}

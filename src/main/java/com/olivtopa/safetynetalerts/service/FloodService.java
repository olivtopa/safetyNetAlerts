package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.FloodAddress;
import com.olivtopa.safetynetalerts.model.FloodPerson;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;

@Service
public class FloodService {

	@Autowired
	private FireStationDAO fireStationDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	private FloodAddress buildFloodAddress(Person person) {

		FloodAddress floodAddress = new FloodAddress();
		floodAddress.setAddress(person.getAddress());
		floodAddress.setFloodPerson(null);

		return floodAddress;
	}

	public List<FloodAddress> buildAddressObject(Person person) {

		List<FloodAddress> floodAddress = personDAO.getAll().stream().map(p -> buildFloodAddress(p))
				.collect(Collectors.toList());

		return floodAddress;

	}

}

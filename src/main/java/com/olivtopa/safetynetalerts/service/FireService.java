package com.olivtopa.safetynetalerts.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.Fire;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.FiresStation;

@Service
public class FireService {

	@Autowired
	private MedicalRecordDAO medicalRecordDAO;
	@Autowired
	private FireStationDAO fireStationDAO;
	@Autowired
	private PersonDAO personDAO;

	private String personLastName;
	private String personPhone;
	private int station;
	private List<String> allergies;

	private Fire buildFire(Person person, FiresStation firesStation, MedicalRecord medicalRecord) {

		Fire fire = new Fire();

		person.setLastName(personLastName);
		person.setPhone(personPhone);
		firesStation.setStation(station);
		medicalRecord.setAllergies(allergies);

		return fire;

	}

	private FiresStation findFiresStation(String address) {
		
		FiresStation findFireStation = fireStationDAO.getAll().stream().filter(a->a.getAddress().equals(address)).map(FiresStation::getStation);
		return findFireStation;
	}
}

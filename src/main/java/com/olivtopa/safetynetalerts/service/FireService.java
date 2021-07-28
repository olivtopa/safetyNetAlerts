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
	@Autowired
	Person person;
	@Autowired
	FiresStation firesStation; 

		private Fire buildFire(Person person, FiresStation firesStation, MedicalRecord medicalRecord) {
				
		Fire fire = new Fire();
		
		final String firstName ;
		final String lastName;
		final int station;
		final List<String> medications;
		final List<String> allergies;

		fire.setFirstName(person.getFirstName());
		fire.setLastName(person.getLastName());
		fire.setStation(firesStation.getStation());
		fire.setMedications(medicalRecord.getMedications());
		fire.setAllergies(medicalRecord.getAllergies());

		return fire;

	}

	public void inhabitantByAddress(String address) {

		personDAO.getAll().stream().filter(a -> a.getAddress() == (address))
				.map(person -> buildFire(person, findFireStation(address), findMedicalRecord()))
				.collect(Collectors.toList());

		

	}

	private FiresStation findFireStation(String address) {

		FiresStation stations = fireStationDAO.getAll().stream()
				.filter(fireStation -> fireStation.getAddress().equals(address)).collect(null);
		return stations;
	}

	private MedicalRecord findMedicalRecord() {
		MedicalRecord medical = medicalRecordDAO.getAll().stream().filter(person -> person.getFirstName().equals(FirstName) && person.getLastName().equals(LastName))
				.map(MedicalRecord::getAllergies).collect(Collectors.toList());
		return null;
	}

}

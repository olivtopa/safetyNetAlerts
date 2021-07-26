package com.olivtopa.safetynetalerts.service;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<Fire> findByAddress(String address) {
		List<String> personPhone = personDAO.getAll().stream().filter(person -> person.getAddress().equals(address))
				.map(Person::getPhone).collect(Collectors.toList());
		List<String> personLastName = personDAO.getAll().stream().filter(person -> person.getAddress().equals(address))
				.map(Person::getLastName).collect(Collectors.toList());
		List<Integer> station = fireStationDAO.getAll().stream().filter(firesStation -> firesStation.getAddress().equals(address))
				.map(FiresStation::getStation).collect(Collectors.toList());
		List<List> medical = medicalRecordDAO.getAll().stream().filter(medic -> medic.getLastName().equals(address))
				.map(MedicalRecord::getAllergies).collect(Collectors.toList());
		
		return null;
		
	}
}

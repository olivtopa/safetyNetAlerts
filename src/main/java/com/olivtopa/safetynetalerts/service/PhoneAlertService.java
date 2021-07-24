package com.olivtopa.safetynetalerts.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.Person;

@Service
public class PhoneAlertService {

	@Autowired
	private FireStationDAO fireStationDAO;

	@Autowired
	FiresStation firesStation;

	@Autowired
	private PersonDAO personDAO;

	public List<String> findPhoneNumberByFireStationNumber(int fireStationNumber) {

		fireStationDAO.getAll().stream().filter(s -> s.getStation() == (fireStationNumber))
				.map(FiresStation::getAddress).collect(Collectors.toList());

		List<String> phoneNumberByAddress = personDAO.getAll().stream()
				.filter(c -> c.getAddress().contains(firesStation.getAddress())).map(Person::getPhone)
				.collect(Collectors.toList());
		return phoneNumberByAddress;

	}
}

package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.ChildAlert;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;

@Service
public class ChildAlertService {

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	private ChildAlert buildChildAlert(Person person, MedicalRecord medicalRecord) {

		ChildAlert childAlert = new ChildAlert();

		childAlert.setFirstName(medicalRecord.getFirstName());
		childAlert.setLastName(medicalRecord.getLastName());
		childAlert.setAddress(person.getAddress());
		childAlert.setFoyer(List < person > composedFoyer());

		LocalDate birthdate = medicalRecord.getBirthdate();
		LocalDate currentdate = LocalDate.now();

		int age;

		CalculateAge calculAge = new CalculateAge();
		age = calculAge.calculateAge(birthdate, currentdate);

		childAlert.setAge(age);

		return null;

	}

	private List<ChildAlert> composedFoyer(String address) {
		List<ChildAlert> childAlert = personDAO.getAll().stream().filter(a -> a.getAddress().equals(address))
				.map(person -> buildChildAlert(person,childresearch())).collect(Collectors.toList());

		return childAlert;
	}

	private MedicalRecord childresearch() {

		MedicalRecord child = medicalRecordDAO.getAll().stream()
				.filter(m -> m.getBirthdate().isAfter(LocalDate.of(2003, 9, 3))).distinct().findAny().orElse(null);

		return child;
	}

}

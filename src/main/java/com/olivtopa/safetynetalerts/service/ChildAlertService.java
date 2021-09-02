package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.util.List;

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
		childAlert.setFoyer(List<person> composedFoyer() );
		
		LocalDate birthdate = medicalRecord.getBirthdate();
		LocalDate currentdate = LocalDate.now();
		
		int age;

		CalculateAge calculAge = new CalculateAge();
		age = calculAge.calculateAge(birthdate, currentdate);

		childAlert.setAge(age);
		
		return null;
		
		
	}

	private int composedFoyer() {
		// TODO Auto-generated method stub
		return 0;
	}

	private MedicalRecord childresearch() {
		
		MedicalRecord child = medicalRecordDAO.getAll().stream().filter(medicalRecord->medicalRecord.getBirthdate().isAfter(LocalDate.now()));
		
		return 0;
	}
	
	
	

}

package com.olivtopa.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;

@Service
public class PhoneAlertService {

	@Autowired
	private FireStationDAO fireStationDAO;
	
	public List<FiresStation> getAll() {
		return fireStationDAO.getAll();
	}
}

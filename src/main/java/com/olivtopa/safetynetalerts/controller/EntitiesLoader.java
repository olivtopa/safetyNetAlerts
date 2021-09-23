package com.olivtopa.safetynetalerts.controller;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.FiresStation;

@Service
public class EntitiesLoader {

	ObjectMapper objectMapper = new ObjectMapper();
	FireStationController fireStationController;
	FiresStation firesStation;

	public Entities load(String filename) throws IOException {

		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.readValue(EntitiesLoader.class.getClassLoader().getResource(filename), Entities.class);

	}

public Entities addOnfile(String filename) throws IOException {
		
		
		objectMapper.registerModule(new JavaTimeModule());
		
		
		return objectMapper.writeValue(File.FILENAME,fireStationController.addAStation(firesStation));

	}
}

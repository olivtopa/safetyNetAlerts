package com.olivtopa.safetynetalerts.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.model.Entities;

public class EntitiesLoader {
	
	public String filename = File.FILENAME;

	public static Entities load(String filename) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(EntitiesLoader.class.getClassLoader().getResource(filename), Entities.class);

	}

}

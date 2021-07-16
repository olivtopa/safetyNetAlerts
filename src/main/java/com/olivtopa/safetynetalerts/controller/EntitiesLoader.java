package com.olivtopa.safetynetalerts.controller;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivtopa.safetynetalerts.model.Entities;

public class EntitiesLoader {
	
	static File file = new File("src/test/resources/data.json");

	public void EntitiesLoad(String filename) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		Entities entities = objectMapper.readValue(file, Entities.class);

	}

}

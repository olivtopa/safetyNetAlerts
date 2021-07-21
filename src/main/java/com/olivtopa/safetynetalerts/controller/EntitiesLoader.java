package com.olivtopa.safetynetalerts.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivtopa.safetynetalerts.model.Entities;

public class EntitiesLoader {
	@Autowired
	EntitiesLoader entitiesLoader;

	public Entities load(String filename) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(EntitiesLoader.class.getClassLoader().getResource(filename), Entities.class);

	}

}

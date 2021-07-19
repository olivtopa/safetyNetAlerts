package com.olivtopa.safetynetalerts.controllerTest;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.Entities;


public class EntitiesLoaderTest {

	private EntitiesLoader entitiesLoader = new EntitiesLoader();

	@Test
	private void loadFile() throws IOException, JsonMappingException, Exception {

		Entities entities = entitiesLoader.load("src/main/resources/data.json");

		Assertions.assertNotNull(entities);

	}
	
	@Test
	private void loadFileerror() throws IOException, JsonMappingException, Exception {

		Entities entities = entitiesLoader.load("src/main/resources/data.json");

		Assertions.assertNotNull(entities,"the input file could not be loaded");
	}

}

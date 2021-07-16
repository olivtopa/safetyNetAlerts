package com.olivtopa.safetynetalerts.controllerTest;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivtopa.safetynetalerts.model.Entities;

public class EntitiesLoaderTest {

	private static ObjectMapper objectMapper;

	@Test
	public void loadFile() throws IOException, JsonMappingException, Exception {

		Entities entities = new Entities();
		entities = null;
		File file = new File("src/test/resources/data.json");
		entities = objectMapper.readValue(file, Entities.class);
		Assertions.assertNotNull(entities);

	}

}

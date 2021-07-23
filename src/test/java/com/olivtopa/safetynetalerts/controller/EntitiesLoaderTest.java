package com.olivtopa.safetynetalerts.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.Entities;

public class EntitiesLoaderTest {

	 EntitiesLoader entitiesLoader = new EntitiesLoader();

	@Test
	public void loadFile() throws Exception {

		Entities entities = entitiesLoader.load("data.json");

		Assertions.assertNotNull(entities);

	}

	@Test
	public void loadFileerror() throws Exception {

		entitiesLoader.load("data.json");

		Assertions.assertThrows(IllegalArgumentException.class, () -> entitiesLoader.load("dat.json")); //Invalid argument for dat.json expected
	}

}

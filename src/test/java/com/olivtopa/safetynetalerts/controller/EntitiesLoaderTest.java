package com.olivtopa.safetynetalerts.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.Person;

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

	    Assertions.assertThrows(IllegalArgumentException.class, () -> entitiesLoader.load("dat.json")); // Invalid
	    // argument for
	    // dat.json
	    // expected
	  }

	  @Test
	  public void persist() throws IOException {
	    // Given
	    Entities entities = new Entities();
	    Person p = new Person();
	    p.setFirstName("first");
	    p.setLastName("last");
	    p.setAddress("adr1");
	    p.setPhone("0102030405");
	    p.setEmail("firstlast@email.com");
	    p.setCity("City1");
	    p.setZip("00000");
	    entities.setPersons(List.of(p));

	    // When
	    entitiesLoader.write("data_for_persist_test.json",entities);

	    // Then
	    Entities loadedEntities = entitiesLoader.load("data_for_persist_test.json");

	    assertThat(loadedEntities.getPersons()).singleElement().usingRecursiveComparison().isEqualTo(p);
	  }

	}

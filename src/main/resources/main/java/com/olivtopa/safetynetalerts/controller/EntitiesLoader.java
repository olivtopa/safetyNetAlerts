package com.olivtopa.safetynetalerts.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.olivtopa.safetynetalerts.model.Entities;





@Service
public class EntitiesLoader {

	ObjectMapper objectMapper = new ObjectMapper();

	public Entities load(String filename) throws IOException {

		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.readValue(EntitiesLoader.class.getClassLoader().getResource(filename), Entities.class);

	}

	public void write(String filename, Entities entities) {

		try {

			objectMapper.writeValue(new File(URLDecoder.decode(
					Objects.requireNonNull(EntitiesLoader.class.getClassLoader().getResource(filename)).getFile(),
					"UTF-8")), entities);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

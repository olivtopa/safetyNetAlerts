package com.olivtopa.safetynetalerts.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;

import com.olivtopa.safetynetalerts.model.Person;


@Repository
public class PersonDAO {

  public List<Person> getAll() {
    try {
    	EntitiesLoader entitiesLoader = new EntitiesLoader();
      return entitiesLoader.load(File.FILENAME).getPersons();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}

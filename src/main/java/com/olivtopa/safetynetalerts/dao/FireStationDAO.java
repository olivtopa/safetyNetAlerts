package com.olivtopa.safetynetalerts.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.FiresStation;

@Repository
public class FireStationDAO {
	
	@Autowired
	EntitiesLoader entitiesLoader;
	@Autowired FiresStation firesStation;

	public List<FiresStation> getAll() {
		try {
			return entitiesLoader.load(File.FILENAME).getFirestations();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public FiresStation create(FiresStation fireStation) {
		
				
		FiresStation newFiresStation = new FiresStation();
		
		firesStation.setAddress(newFiresStation.getAddress());
		firesStation.setStation(newFiresStation.getStation());
		
		return firesStation;
				
	}

}

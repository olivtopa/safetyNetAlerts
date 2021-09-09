package com.olivtopa.safetynetalerts.service;

import org.springframework.stereotype.Service;
import com.olivtopa.safetynetalerts.model.ChildrenList;

@Service
public class ChildAlertService {

	public Object finalChildrenList(String address) {
		return new ChildrenList();
	}

}

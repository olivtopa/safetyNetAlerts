package com.olivtopa.safetynetalerts.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
class CommunityEmailsServiceTest {

	@InjectMocks
	private CommunityEmailsService communityEmailsService;

	@Mock
	private PersonService personService;

	@Test
	public void unknownCityReturnNoEmail() {

		// Given
		Person person = new Person();
		person.setEmail("email1");
		person.setCity("city1");
		Mockito.when(personService.getAll()).thenReturn(List.of(person));

		// When
		List<String> filteredEmails = communityEmailsService.getEmails("unknown");

		// Then
		Assertions.assertThat(filteredEmails).isEmpty();
	}

	@Test
	public void knownCityReturnsEmail() {
		// Given
		Person matchingPerson = new Person();
		matchingPerson.setEmail("email1");
		matchingPerson.setCity("city1");

		Person filteredOutPerson = new Person();
		filteredOutPerson.setEmail("email2");
		filteredOutPerson.setCity("city2");
		// TODO compléter filteredOutPerson: email & city différents de la première
		// personne

		Mockito.when(personService.getAll()).thenReturn(List.of(matchingPerson, filteredOutPerson));

		// When
		List<String> filteredEmails = communityEmailsService.getEmails("city");

		// Then
		Assertions.assertThat(filteredEmails).containsExactly(matchingPerson.getEmail());
	}

	@Test
	public void noPersons() {
		// Given
		Mockito.when(personService.getAll()).thenReturn(List.of());

		// When
		List<String> filteredEmails = communityEmailsService.getEmails("city");

		// Then
		// TODO compléter
		Assertions.assertThat(filteredEmails).isEmpty();
	}

}

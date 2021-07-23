package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityEmailsControllerIntegrationTest {

  public static final String CITY_NAME = "Culver";
  @Autowired
  private CommunityEmailsController controller;

  @Test
  public void fetch_emails() {

    // Given + When
    List<String> communityEmails = controller.getCommunityEmails(CITY_NAME);

    // Then
    Assertions.assertThat(communityEmails).isNotEmpty();
  }

}

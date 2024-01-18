package com.intelliseven.spring_data_jpa_activity.presentation.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.intelliseven.spring_data_jpa_activity.TestDataUtil;
import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class HearingControllerIntegrationTests {
  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Autowired
  public HearingControllerIntegrationTests(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  public void testThatCreateHearingSuccessfullyReturnsHttp201Created() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    testHearingEntityA.setId(null);
    String hearingJson = objectMapper.writeValueAsString(testHearingEntityA);
    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/hearings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(hearingJson))
        .andExpect(
            MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testThatCreateHearingSuccessfullyReturnsSavedHearing() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    testHearingEntityA.setId(null);
    String hearingJson = objectMapper.writeValueAsString(testHearingEntityA);
    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/hearings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(hearingJson))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.id").isNumber())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.caseName").value("Test Case Name vs. Pipol of da Pelepens"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.date").value("2000-08-18"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value("PROCEED"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.incident").value("Sample test incident naman ngaya"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.proceeding").value("Tas test proceeding man kuno ading usad"));
  }
}

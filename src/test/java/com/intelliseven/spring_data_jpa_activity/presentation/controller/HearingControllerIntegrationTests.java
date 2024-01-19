package com.intelliseven.spring_data_jpa_activity.presentation.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.intelliseven.spring_data_jpa_activity.SpringDataJpaActivityApplication;
import com.intelliseven.spring_data_jpa_activity.TestDataUtil;
import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;
import com.intelliseven.spring_data_jpa_activity.service.HearingService;

@SpringBootTest(classes = SpringDataJpaActivityApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class HearingControllerIntegrationTests {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private HearingService hearingService;

  private ObjectMapper objectMapper;

  @Autowired
  public HearingControllerIntegrationTests() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  public void testThatCreateHearingReturnsHttp201Created() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    testHearingEntityA.setId(null);
    String hearingJson = objectMapper.writeValueAsString(testHearingEntityA);
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/hearings")
            .contentType(MediaType.APPLICATION_JSON).content(hearingJson))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testThatCreateHearingReturnsSavedHearing() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    testHearingEntityA.setId(null);
    String hearingJson = objectMapper.writeValueAsString(testHearingEntityA);
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/hearings")
            .contentType(MediaType.APPLICATION_JSON).content(hearingJson))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
        .andExpect(MockMvcResultMatchers.jsonPath("$.caseName")
            .value("Test Case Name vs. Pipol of da Pelepens"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2000-08-18"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("PROCEED"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.incident").value("Sample test incident naman ngaya"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.proceeding")
            .value("Tas test proceeding man kuno ading usad"));
  }

  @Test
  public void testThatListHearingsReturnsHttp200() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/hearings").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testThatListHearingsReturnsListOfHearings() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    hearingService.save(testHearingEntityA);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/hearings").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].caseName")
            .value("Test Case Name vs. Pipol of da Pelepens"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value("2000-08-18"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("PROCEED"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].incident")
            .value("Sample test incident naman ngaya"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].proceeding")
            .value("Tas test proceeding man kuno ading usad"));
  }

  @Test
  public void testThatGetHearingReturnsHttp200WhenHearingExists() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    hearingService.save(testHearingEntityA);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/hearings/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testThatGetHearingReturnsHttp404WhenNoHearingExists() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/hearings/99").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testThatGetHearingReturnsHearingWhenHearingExists() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    hearingService.save(testHearingEntityA);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/hearings/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.caseName")
            .value("Test Case Name vs. Pipol of da Pelepens"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2000-08-18"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("PROCEED"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.incident").value("Sample test incident naman ngaya"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.proceeding")
            .value("Tas test proceeding man kuno ading usad"));
  }

  @Test
  public void testThatFullUpdateHearingReturnsHttp200WhenHearingExists() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    HearingEntity savedHearing = hearingService.save(testHearingEntityA);

    HearingDto testHearingDto = TestDataUtil.createTestHearingDtoA();
    String testHearingDtoJson = objectMapper.writeValueAsString(testHearingDto);

    mockMvc
        .perform(MockMvcRequestBuilders.put("/api/hearings/" + savedHearing.getId())
            .contentType(MediaType.APPLICATION_JSON).content(testHearingDtoJson))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testThatFullUpdateHearingReturnsHttp404WhenNoHearingExists() throws Exception {
    HearingDto testHearingDtoA = TestDataUtil.createTestHearingDtoA();
    String hearingTestDtoJson = objectMapper.writeValueAsString(testHearingDtoA);

    mockMvc
        .perform(MockMvcRequestBuilders.put("/api/hearings/99")
            .contentType(MediaType.APPLICATION_JSON).content(hearingTestDtoJson))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testThatFullUpdatesExistingHearing() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    HearingEntity savedHearing = hearingService.save(testHearingEntityA);

    HearingDto testHearingDtoB = TestDataUtil.createTestHearingDtoB();
    testHearingDtoB.setId(savedHearing.getId());
    String testHearingDtoBJson = objectMapper.writeValueAsString(testHearingDtoB);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedExpectedDate = testHearingDtoB.getDate().format(formatter);

    mockMvc
        .perform(MockMvcRequestBuilders.put("/api/hearings/" + savedHearing.getId())
            .contentType(MediaType.APPLICATION_JSON).content(testHearingDtoBJson))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedHearing.getId()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.caseName").value(testHearingDtoB.getCaseName()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(formattedExpectedDate))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(testHearingDtoB.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.incident").value(testHearingDtoB.getIncident()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.proceeding").value(testHearingDtoB.getProceeding()));
  }

  @Test
  public void testThatPartialUpdateHearingReturnsHttp200WhenHearingExists() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    HearingEntity savedHearing = hearingService.save(testHearingEntityA);

    HearingDto testHearingDto = TestDataUtil.createTestHearingDtoA();
    testHearingDto.setCaseName("PARTIALLY UPDATED CASE NAME");
    String testHearingDtoJson = objectMapper.writeValueAsString(testHearingDto);

    mockMvc
        .perform(MockMvcRequestBuilders.patch("/api/hearings/" + savedHearing.getId())
            .contentType(MediaType.APPLICATION_JSON).content(testHearingDtoJson))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testThatPartialUpdateHearingReturnsHttp404WhenNoHearingExists() throws Exception {
    HearingDto testHearingDtoA = TestDataUtil.createTestHearingDtoA();
    String hearingTestDtoJson = objectMapper.writeValueAsString(testHearingDtoA);

    mockMvc
        .perform(MockMvcRequestBuilders.patch("/api/hearings/99")
            .contentType(MediaType.APPLICATION_JSON).content(hearingTestDtoJson))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testThatPartialUpdatesExistingHearing() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    HearingEntity savedHearing = hearingService.save(testHearingEntityA);

    HearingDto testHearingDtoB = TestDataUtil.createTestHearingDtoB();
    testHearingDtoB.setId(savedHearing.getId());
    testHearingDtoB.setCaseName("PARTIALLY UPDATED CASE NAME");
    String testHearingDtoBJson = objectMapper.writeValueAsString(testHearingDtoB);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedExpectedDate = testHearingDtoB.getDate().format(formatter);

    mockMvc
        .perform(MockMvcRequestBuilders.put("/api/hearings/" + savedHearing.getId())
            .contentType(MediaType.APPLICATION_JSON).content(testHearingDtoBJson))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedHearing.getId()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.caseName").value("PARTIALLY UPDATED CASE NAME"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(formattedExpectedDate))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(testHearingDtoB.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.incident").value(testHearingDtoB.getIncident()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.proceeding").value(testHearingDtoB.getProceeding()));
  }

  @Test
  public void testThatDeleteHearingReturnsHttp404WhenNoHearingExists() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/api/hearings/99")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testThatDeleteHearingReturnsHttp204WhenHearingExists() throws Exception {
    HearingEntity testHearingEntityA = TestDataUtil.createTestHearingEntityA();
    HearingEntity savedHearing = hearingService.save(testHearingEntityA);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/api/hearings/" + savedHearing.getId())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

}

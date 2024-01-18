package com.intelliseven.spring_data_jpa_activity.persistence.repository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import com.intelliseven.spring_data_jpa_activity.TestDataUtil;
import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HearingEntityRepositoryIntegrationTests {

  private HearingRepo hearingRepo;

  @Autowired
  public HearingEntityRepositoryIntegrationTests(HearingRepo hearingRepo) {
    this.hearingRepo = hearingRepo;
  }

  @Test
  public void testThatHearingCanBeCreatedAndRecalled() {
    HearingEntity hearingEntity = TestDataUtil.createTestHearingEntityA();
    hearingRepo.save(hearingEntity);
    Optional<HearingEntity> result = hearingRepo.findById(hearingEntity.getId());
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(hearingEntity);
  }
}

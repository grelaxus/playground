package com.vk.dal;

import com.vk.dal.config.H2JPACustomPropertiesConfig;
import com.vk.dal.domain.GenericEntity;
import com.vk.dal.repository.GenericEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan ("com.vk.dal")

@SpringBootTest(classes = { Application.class, H2JPACustomPropertiesConfig.class })
public class SpringBootH2IntegrationTest {
  @Autowired
  private GenericEntityRepository genericEntityRepository;

  @Test
  public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
    GenericEntity genericEntity = (GenericEntity) genericEntityRepository.save(new GenericEntity("test"));
    GenericEntity foundEntity = (GenericEntity)genericEntityRepository.findOne(genericEntity.getId());
    assertNotNull(foundEntity);
    assertEquals(genericEntity.getValue(), foundEntity.getValue());
  }
}
package com.vk.dal;

import com.vk.dal.domain.GenericEntity;
import com.vk.dal.repository.GenericEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBootJPAIntegrationTest {
  @Autowired
  private GenericEntityRepository genericEntityRepository;

  @Test
  public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
    GenericEntity genericEntity = (GenericEntity) genericEntityRepository.save(new GenericEntity("test"));
    GenericEntity foundEntity = (GenericEntity) genericEntityRepository.findOne(genericEntity.getId());
    assertNotNull(foundEntity);
    assertEquals(genericEntity.getValue(), foundEntity.getValue());
  }
}

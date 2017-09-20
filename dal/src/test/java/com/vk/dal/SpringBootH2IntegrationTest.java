package com.vk.dal;

import com.vk.dal.config.H2JPACustomPropertiesConfig;
import com.vk.dal.domain.Customer;
import com.vk.dal.domain.GenericEntity;
import com.vk.dal.repository.CustomerRepository;
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

@SpringBootTest(classes = { H2JPACustomPropertiesConfig.class })
public class SpringBootH2IntegrationTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
    Customer customer = customerRepository.save(new Customer ("test"));
    Customer foundCustomer = customerRepository.findOne(customer.getId());
    assertNotNull(foundCustomer);
    assertEquals(customer.getName(), foundCustomer.getName());
  }
}
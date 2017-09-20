package com.vk.dal;

import com.vk.dal.config.MySQLAutoconfiguration;
import com.vk.dal.domain.Customer;
import com.vk.dal.repository.CustomerRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static java.lang.System.exit;

public class Application {
  private static ApplicationContext applicationContext;

  static CustomerRepository customerRepository;

  public static void main(String[] args) {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MySQLAutoconfiguration.class);

    customerRepository = ctx.getBean(CustomerRepository.class);
    runTest();
  }

  private static void runTest () {

    Customer customerJohn = customerRepository.save(new Customer("John"));
    Customer customerAlice = customerRepository.save(new Customer("Alice"));
    Customer foundEntity = customerRepository.findOne(customerJohn.getId());

    System.out.println("\n1.findAll()...");
    for (Customer customer : customerRepository.findAll()) {
      System.out.println(customer.getName());
    }

    System.out.println("Done!");

    exit(0);
  }
}
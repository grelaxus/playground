package com.vk.dal;

import com.vk.dal.domain.Customer;
import com.vk.dal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static java.lang.System.exit;



@SpringBootApplication /*(exclude = {DataSourceAutoConfiguration.class})*//*(exclude = MySQLAutoconfiguration.class)*/
public class Application implements CommandLineRunner {
  private static ApplicationContext applicationContext;

  @Autowired
  DataSource dataSource;

  @Autowired
  CustomerRepository customerRepository;

  public static void main(String[] args) {
    applicationContext = SpringApplication.run(Application.class, args);
  }

  @Transactional/*(readOnly = true)*/
  @Override
  public void run(String... args) throws Exception {

    System.out.println("DATASOURCE = " + dataSource);

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
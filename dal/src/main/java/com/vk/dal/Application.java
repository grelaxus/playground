package com.vk.dal;

import com.vk.dal.config.MySQLAutoconfiguration;
import com.vk.dal.domain.Customer;
import com.vk.dal.repository.CustomerRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class Application {
  private static ApplicationContext applicationContext;

  static CustomerRepository customerRepository;

  public static void main(String[] args) throws InterruptedException {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MySQLAutoconfiguration.class);

    customerRepository = ctx.getBean(CustomerRepository.class);
    runTest();
  }

  private static void runTest () throws InterruptedException {

    long startTime = System.nanoTime();
    Customer customerJohn = customerRepository.save(new Customer("John"));
    long spentTime = System.nanoTime() - startTime;
    System.out.println("Time spent to save one entity: " + TimeUnit.NANOSECONDS.toMillis(spentTime));

    startTime = System.nanoTime();
    Customer customerAlice = customerRepository.save(new Customer("Alice"));
    spentTime = System.nanoTime() - startTime;
    System.out.println("Time spent to save 2nd entity: " + TimeUnit.NANOSECONDS.toMillis(spentTime));

    Customer foundEntity = customerRepository.findOne(customerJohn.getId());

    System.out.println("\n1.findAll()...");
    startTime = System.nanoTime();
    List<Customer> customers = customerRepository.findAll();
    spentTime = System.nanoTime() - startTime;
    for (Customer customer : customers) {
      System.out.println(customer.getName());
    }
    System.out.println("Time spent to find all entities: " + TimeUnit.NANOSECONDS.toMillis(spentTime));

    Thread.sleep(3000);

    startTime = System.nanoTime();
    customers = customerRepository.findAll();
    spentTime = System.nanoTime() - startTime;

    System.out.println("Time spent to find all entities 2nd time: " + TimeUnit.NANOSECONDS.toMillis(spentTime));

    System.out.println("Done!");

//    exit(0);
  }
}
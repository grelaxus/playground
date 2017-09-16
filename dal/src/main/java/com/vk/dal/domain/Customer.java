package com.vk.dal.domain;

import org.hibernate.annotations.AttributeAccessor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AttributeAccessor("field")
@Table(name = "customer")
public class Customer extends GenericEntity {
  @Column(name = "name")
  private String name;

  public Customer() {
  }

  public Customer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

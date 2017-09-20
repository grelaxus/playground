package com.vk.dal.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@MappedSuperclass
public class GenericEntity  implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id",unique=true, nullable=false, insertable=true, updatable=true)
  @Type(type="long")
  private Long id;
  private String value;

  public GenericEntity(String value) {
    this.value = value;
  }

  public GenericEntity(Long id, String value) {
    this.id = id;
    this.value = value;
  }

  public GenericEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}

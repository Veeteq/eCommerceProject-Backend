package com.wojnarowicz.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "countries")
public class Country {

  @Id
  @Column(name = "country_id", nullable = false)
  private Long id;
  
  @NaturalId
  @Column(name = "country_cd")
  private String code;
  
  @Column(name = "name")
  private String name;
  
  @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
  private List<State> states = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<State> getStates() {
    return states;
  }

  public void setStates(List<State> states) {
    this.states = states;
  }
  
  public void addToStates(State state) {
    state.setCountry(this);
    this.states.add(state);
  }
}

package com.wojnarowicz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.wojnarowicz.ecommerce.model.State;

@RepositoryRestResource(collectionResourceRel = "state", path = "states")
public interface StateRepository extends JpaRepository<State, Long>{
  
  Iterable<State> findByCountryCode(@Param(value = "code") String code);

}

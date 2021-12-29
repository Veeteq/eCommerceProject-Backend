package com.wojnarowicz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.wojnarowicz.ecommerce.model.Country;

@RepositoryRestResource(collectionResourceRel = "country", path = "countries")
public interface CountryRepository extends JpaRepository<Country, Long>{

}

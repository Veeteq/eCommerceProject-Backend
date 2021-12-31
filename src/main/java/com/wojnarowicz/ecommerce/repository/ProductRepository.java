package com.wojnarowicz.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.wojnarowicz.ecommerce.model.Product;

@RepositoryRestResource(collectionResourceRel = "product", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {
  
  Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
  
  Page<Product> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

}

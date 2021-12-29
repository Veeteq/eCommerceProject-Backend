package com.wojnarowicz.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.wojnarowicz.ecommerce.model.Product;

@RepositoryRestResource(collectionResourceRel = "product", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {
  
  Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
  
  Page<Product> findByNameContainingIgnoreCase(@RequestParam("name") String name, Pageable pageable);

}

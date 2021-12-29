package com.wojnarowicz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.wojnarowicz.ecommerce.model.ProductCategory;

@RepositoryRestResource(collectionResourceRel = "category", path = "categories")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}

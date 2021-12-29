package com.wojnarowicz.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.wojnarowicz.ecommerce.model.Order;

@RepositoryRestResource(collectionResourceRel = "order", path = "orders")
public interface OrderRepository extends JpaRepository<Order, Long>{

  Page<Order> findByCustomerEmailOrderByDateCreatedDesc(@RequestParam("email") String email, Pageable pageable);
}

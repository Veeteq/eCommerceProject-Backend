package com.wojnarowicz.ecommerce.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE Product SET is_valid=0 WHERE id=?")
@Where(clause = "is_valid=1")
@Data
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
  @SequenceGenerator(sequenceName = "product_seq", allocationSize = 1, name = "product_seq")
  private Long id;
  
  private String sku;
  
  private String name;
  
  private String description;
  
  private BigDecimal unitPrice;

  private String imageUrl;
  
  private boolean active;
  
  private Integer unitsInStock;

  @CreationTimestamp
  private Date dateCreated;
  
  @UpdateTimestamp
  private Date lastUpdated;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", nullable = false)
  private ProductCategory category;
}

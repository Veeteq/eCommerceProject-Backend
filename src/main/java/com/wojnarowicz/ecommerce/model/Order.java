package com.wojnarowicz.ecommerce.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "orders")
public class Order {
  
  @Id
  @Column(name = "order_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
  @SequenceGenerator(sequenceName = "order_seq", allocationSize = 1, name = "order_seq")
  private Long id;
  
  @Column(name = "order_tracking_number")
  private String orderTrackingNumber;
  
  @Column(name = "total_quantity")
  private BigDecimal totalQuantity;
  
  @Column(name = "total_value")
  private BigDecimal totalValue;
  
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
  
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "billing_address_id", referencedColumnName = "address_id")
  private Address billingAddress;
  
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "shipping_address_id", referencedColumnName = "address_id")
  private Address shippingAddress;
  
  @Column(name = "status")
  private String status;
  
  @CreationTimestamp
  @Column(name = "date_created")
  private Date dateCreated;
  
  @UpdateTimestamp
  @Column(name = "date_updated")
  private Date lastUpdated;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
  private Set<OrderItem> orderItems = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderTrackingNumber() {
    return orderTrackingNumber;
  }

  public void setOrderTrackingNumber(String orderTrackingNumber) {
    this.orderTrackingNumber = orderTrackingNumber;
  }

  public BigDecimal getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(BigDecimal totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public BigDecimal getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(BigDecimal totalValue) {
    this.totalValue = totalValue;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
  
  public void addToOrderItems(OrderItem orderItem) {
    orderItem.setOrder(this);
    this.orderItems.add(orderItem);
  }
}

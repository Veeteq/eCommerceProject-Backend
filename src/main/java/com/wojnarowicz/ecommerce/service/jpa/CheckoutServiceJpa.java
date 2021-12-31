package com.wojnarowicz.ecommerce.service.jpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.wojnarowicz.ecommerce.dto.PaymentInfoDTO;
import com.wojnarowicz.ecommerce.dto.PurchaseDTO;
import com.wojnarowicz.ecommerce.dto.PurchaseResponseDTO;
import com.wojnarowicz.ecommerce.model.Address;
import com.wojnarowicz.ecommerce.model.Customer;
import com.wojnarowicz.ecommerce.model.Order;
import com.wojnarowicz.ecommerce.model.OrderItem;
import com.wojnarowicz.ecommerce.repository.CustomerRepository;
import com.wojnarowicz.ecommerce.service.CheckoutService;

@Service
public class CheckoutServiceJpa implements CheckoutService {

  private final CustomerRepository customerRepository;
  
  @Autowired
  public CheckoutServiceJpa(CustomerRepository customerRepository, @Value(value = "${stripe.key.secret}") String apiKey) {
    this.customerRepository = customerRepository;
    
    Stripe.apiKey = apiKey;
  }


  @Override
  @Transactional
  public PurchaseResponseDTO checkout(PurchaseDTO purchase) {
    Order order = purchase.getOrder();
    
    String orderTrackingNumber = generateOrderTrackingNumber();
    order.setOrderTrackingNumber(orderTrackingNumber);
    
    Set<OrderItem> orderItems = purchase.getOrderItems();
    orderItems.forEach(order::addToOrderItems);
    
    Address billingAddress = purchase.getBillingAddress();
    order.setBillingAddress(billingAddress);
    
    Address shippingAddress = purchase.getShippingAddress();
    order.setShippingAddress(shippingAddress);
    
    Customer customer = purchase.getCustomer();
    String customerEmail = customer.getEmail();
    Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerEmail);
    if (optionalCustomer.isPresent()) {
      customer = optionalCustomer.get();
    }
    customer.addToOrders(order);
    
    customerRepository.save(customer);
    
    return new PurchaseResponseDTO(orderTrackingNumber);
  }

  private String generateOrderTrackingNumber() {
    return UUID.randomUUID().toString();
  }


  @Override
  public PaymentIntent createPaymentIntent(PaymentInfoDTO paymentInfo) throws StripeException {
    String pattern = "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
    
    List<String> paymentMethodTypes = List.of("card");
    
    Map<String, Object> params = new HashMap<>();
    params.put("amount",        paymentInfo.getAmount());
    params.put("currency",      paymentInfo.getCurrency());
    params.put("receipt_email", paymentInfo.getReceiptEmail());
    params.put("payment_method_types", paymentMethodTypes);
    params.put("description",   "Shopping at eCommerce on " + LocalDateTime.now().format(df));
    return PaymentIntent.create(params);
  }
}

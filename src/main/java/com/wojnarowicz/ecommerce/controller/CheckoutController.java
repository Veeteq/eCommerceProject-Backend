package com.wojnarowicz.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.wojnarowicz.ecommerce.dto.PaymentInfoDTO;
import com.wojnarowicz.ecommerce.dto.PurchaseDTO;
import com.wojnarowicz.ecommerce.dto.PurchaseResponseDTO;
import com.wojnarowicz.ecommerce.service.CheckoutService;

@RestController
@RequestMapping(path = "/api/checkout")
public class CheckoutController {

  private final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
  private CheckoutService checkoutService;

  @Autowired
  public CheckoutController(CheckoutService checkoutService) {
    this.checkoutService = checkoutService;
  }
  
  @PostMapping(path = "/purchase")
  public PurchaseResponseDTO savePurchase(@RequestBody PurchaseDTO purchase) {
    
    PurchaseResponseDTO purchaseResponseDTO = checkoutService.checkout(purchase);
    
    return purchaseResponseDTO;
  }
  
  @PostMapping(path = "/payment-intent")
  public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoDTO paymentInfo) throws StripeException {
    logger.info("amount: " + paymentInfo.getAmount());
    PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
    
    String paymentIntentJson = paymentIntent.toJson();
    logger.info("stripe response: " + paymentIntentJson);
    
    return new ResponseEntity<String>(paymentIntentJson, HttpStatus.CREATED);
  }
  
}

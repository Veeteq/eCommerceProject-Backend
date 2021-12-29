package com.wojnarowicz.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wojnarowicz.ecommerce.dto.PurchaseDTO;
import com.wojnarowicz.ecommerce.dto.PurchaseResponseDTO;
import com.wojnarowicz.ecommerce.service.CheckoutService;

@RestController
@RequestMapping(path = "/api/checkout")
public class CheckoutController {

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
  
}

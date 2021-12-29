package com.wojnarowicz.ecommerce.service;

import com.wojnarowicz.ecommerce.dto.PurchaseDTO;
import com.wojnarowicz.ecommerce.dto.PurchaseResponseDTO;

public interface CheckoutService {

  public PurchaseResponseDTO checkout(PurchaseDTO purchase);
}

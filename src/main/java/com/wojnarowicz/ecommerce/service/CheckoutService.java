package com.wojnarowicz.ecommerce.service;

import com.wojnarowicz.ecommerce.dto.PaymentInfoDTO;
import com.wojnarowicz.ecommerce.dto.PurchaseDTO;
import com.wojnarowicz.ecommerce.dto.PurchaseResponseDTO;
import com.stripe.exception.StripeException
import com.stripe.model.PaymentIntent

public interface CheckoutService {

  public PurchaseResponseDTO checkout(PurchaseDTO purchase);
  
  public PaymentIntent createPaymentIntent(PaymentInfoDTO paymentInfo) throws StripeException
}

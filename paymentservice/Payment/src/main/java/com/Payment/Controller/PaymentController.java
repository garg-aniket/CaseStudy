package com.Payment.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.Payment.Model.PaymentPOJO;
import com.Payment.Repository.PaymentRepository;
import com.Payment.Service.PaymentService;

import ch.qos.logback.core.joran.spi.ActionException;


@RestController
@RequestMapping("/payments") // Base mapping for all endpoints in this controller
public class PaymentController {
	
	@Autowired
	public PaymentService paymentService; // Autowiring the PaymentService for handling payment logic
	
	
	@Autowired
	public PaymentRepository paymentRepository; // Autowiring the PaymentRepository for database operations
	
	
	/**
     * Retrieves payment details by order ID.
     *
     * @param id The order ID to retrieve payment details for.
     * @return An optional containing PaymentPOJO if found, or throws an ActionException if not found.
     * @throws ActionException if the payment details are not found for the given order ID.
     */
	@GetMapping("/orders/{id}")
	public Optional<PaymentPOJO> getCustomerById(@PathVariable int id) throws ActionException {
		return Optional.of(paymentRepository.findById(id)
				.orElseThrow(() -> new ActionException()));
	}
	
	 /**
     * Processes a payment transaction.
     *
     * @param payment The PaymentPOJO object containing payment information.
     * @return The PaymentPOJO object representing the payment transaction result.
     */
	
	@PostMapping("/payment")
	public PaymentPOJO doPayment(@RequestBody PaymentPOJO payment) {
		return paymentService.doPay(payment);
		
	}

}

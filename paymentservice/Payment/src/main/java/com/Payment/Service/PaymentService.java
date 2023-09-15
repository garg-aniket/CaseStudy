package com.Payment.Service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Payment.Model.PaymentPOJO;
import com.Payment.Repository.PaymentRepository;



@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	// This method is used to process a payment.
	public PaymentPOJO doPay(PaymentPOJO payment) {
		// Set the payment status using the paymentStatus method.
		payment.setPaymentStatus(paymentStatus());
		// Generate a unique transaction ID and set it in the PaymentPOJO.
		payment.setTxId(UUID.randomUUID().toString());
		// Save the payment object to the database using the PaymentRepository.
		return paymentRepository.save(payment);
		
	}
	// This private method generates a random payment status, either "success" or "failure".
	private String paymentStatus() {
		// Use the Random class to generate a random boolean value (true or false).
		// If it's true, the payment status is "success," otherwise it's "failure."
		return new Random().nextBoolean()?"success":"failure";
	}
	
}

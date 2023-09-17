package com.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Payment.Model.PaymentPOJO;
import com.Payment.Repository.PaymentRepository;
import com.Payment.Service.PaymentService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        // Configure the behavior of the mock PaymentRepository
        when(paymentRepository.save(any(PaymentPOJO.class))).thenAnswer(invocation -> {
            PaymentPOJO payment = invocation.getArgument(0);
            payment.setOrderId(1347949); // Simulate saving to the database and setting an ID
            return payment;
        });
    }

    @Test
    public void testDoPay() {
        // Create a sample PaymentPOJO
        PaymentPOJO payment = new PaymentPOJO();
        payment.setAmount(100);

        // Call the doPay method
        PaymentPOJO result = paymentService.doPay(payment);

        // Assertions
        assertEquals("success", result.getPaymentStatus()); // Check payment status
        assertEquals(1347949, result.getOrderId()); // Check if an ID is assigned
        assertEquals(36, result.getTxId().length()); // Check if the transaction ID is 36 characters (UUID length)
    }
}
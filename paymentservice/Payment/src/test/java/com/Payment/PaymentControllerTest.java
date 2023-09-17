package com.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.Payment.Controller.PaymentController;
import com.Payment.Model.PaymentPOJO;
import com.Payment.Repository.PaymentRepository;
import com.Payment.Service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerById() throws Exception {
        int orderId = 1;
        PaymentPOJO paymentPOJO = new PaymentPOJO(); // Create a PaymentPOJO for testing

        when(paymentRepository.findById(orderId)).thenReturn(Optional.of(paymentPOJO));

        mockMvc.perform(MockMvcRequestBuilders.get("/payments/orders/{id}", orderId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDoPayment() throws Exception {
        PaymentPOJO paymentPOJO = new PaymentPOJO(); // Create a PaymentPOJO for testing

        paymentPOJO.setAmount(100); // Set some data for the PaymentPOJO

        // Convert PaymentPOJO to JSON representation
        ObjectMapper objectMapper = new ObjectMapper();
        String paymentJson = objectMapper.writeValueAsString(paymentPOJO);

        when(paymentService.doPay(any(PaymentPOJO.class))).thenReturn(paymentPOJO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payments/payment")
                .content(paymentJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
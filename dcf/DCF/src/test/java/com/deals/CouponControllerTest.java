package com.deals;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.deals.model.Coupon;
import com.deals.service.CouponService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponService couponService;


    @Test
    public void testSubmitCoupon() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setMerchantName("Sample Merchant");
        coupon.setTitle("Sample Coupon");

        // Convert the Coupon object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String couponJson = objectMapper.writeValueAsString(coupon);

        // Mock behavior of couponService methods
        doReturn(true).when(couponService).submitCoupon(any(Coupon.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/admin/coupon/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(couponJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Coupon submitted successfully!"));
    }

    @Test
    public void testGetAllCoupons() throws Exception {
        List<Coupon> coupons = new ArrayList<>();
        // Add sample coupons to the list

        // Mock behavior of couponService methods
        doReturn(coupons).when(couponService).getAllCoupons();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/coupon/getall"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]")); // JSON representation of your coupons list
    }

    @Test
    public void testUpdateCoupon() throws Exception {
        String couponCode = "sampleCode";
        Coupon existingCoupon = new Coupon();
        existingCoupon.setMerchantName("Existing Merchant");
        existingCoupon.setTitle("Existing Coupon");

        // Convert the existingCoupon object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String existingCouponJson = objectMapper.writeValueAsString(existingCoupon);

        // Mock behavior of couponService methods
        doReturn(true).when(couponService).updateCoupon(eq(couponCode), any(Coupon.class));

        Coupon updatedCoupon = new Coupon();
        updatedCoupon.setMerchantName("Updated Merchant");
        updatedCoupon.setTitle("Updated Coupon");

        // Convert the updatedCoupon object to JSON
        String updatedCouponJson = objectMapper.writeValueAsString(updatedCoupon);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/admin/coupon/update/{couponCode}", couponCode)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedCouponJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Coupon updated successfully!"));
    }

    @Test
    public void testDeleteCoupon() throws Exception {
        String couponCode = "sampleCode";
        Coupon existingCoupon = new Coupon();
        existingCoupon.setMerchantName("Existing Merchant");
        existingCoupon.setTitle("Existing Coupon");

        // Convert the existingCoupon object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String existingCouponJson = objectMapper.writeValueAsString(existingCoupon);

        // Mock behavior of couponService methods
        doReturn(existingCoupon).when(couponService).getCouponByCode(couponCode);
        doReturn(true).when(couponService).deleteCoupon(couponCode);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/admin/coupon/delete/{couponCode}", couponCode)
                .contentType(MediaType.APPLICATION_JSON)
                .content(existingCouponJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Coupon deleted successfully!"));
    }

    @Test
    public void testGetTotalSubmissions() throws Exception {
        long totalSubmissions = 42L;

        // Mock behavior of couponService method
        doReturn(totalSubmissions).when(couponService).getTotalSubmissions();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/coupon/total-submissions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(totalSubmissions)));
    }

    @Test
    public void testSearchCoupon() throws Exception {
        String couponCode = "sampleCode";
        Coupon existingCoupon = new Coupon();
        existingCoupon.setMerchantName("Sample Merchant");
        existingCoupon.setTitle("Sample Coupon");

        // Convert the existingCoupon object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String existingCouponJson = objectMapper.writeValueAsString(existingCoupon);

        // Mock behavior of couponService methods
        doReturn(existingCoupon).when(couponService).getCouponByCode(couponCode);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/coupon/search/{couponCode}", couponCode))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(existingCouponJson));
    }
    @Test
    public void testSearchCouponsByMerchantName() throws Exception {
        String merchantName = "Sample Merchant";
        List<Coupon> coupons = new ArrayList<>();
        // Add sample coupons to the list

        // Convert the coupons list to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String couponsJson = objectMapper.writeValueAsString(coupons);

        // Mock behavior of couponService methods
        doReturn(coupons).when(couponService).getCouponsByMerchant(merchantName);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/coupon/search/merchant/{merchantName}", merchantName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(couponsJson));
    }
    }
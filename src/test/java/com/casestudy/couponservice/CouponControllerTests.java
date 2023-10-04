package com.casestudy.couponservice;

import com.casestudy.couponservice.controller.CouponController;
import com.casestudy.couponservice.exception.ResourceNotFoundException;
import com.casestudy.couponservice.model.Coupon;
import com.casestudy.couponservice.service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CouponControllerTests {

    @InjectMocks
    private CouponController couponController;

    @Mock
    private CouponService couponService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCoupon() {
        Coupon couponToCreate = new Coupon();
        Coupon createdCoupon = new Coupon();
        when(couponService.createCoupon(couponToCreate)).thenReturn(createdCoupon);

        ResponseEntity<Coupon> response = couponController.createCoupon(couponToCreate);

        verify(couponService, times(1)).createCoupon(couponToCreate);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() == createdCoupon;
    }

    @Test
    public void testGetCouponById() {
        int couponId = 1;
        Coupon coupon = new Coupon();
        when(couponService.getCouponById(couponId)).thenReturn(coupon);

        ResponseEntity<Coupon> response = couponController.getCouponById(couponId);

        verify(couponService, times(1)).getCouponById(couponId);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == coupon;
    }

    @Test
    public void testGetAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        when(couponService.listAllCoupons()).thenReturn(coupons);

        ResponseEntity<List<Coupon>> response = couponController.getAllCoupons();

        verify(couponService, times(1)).listAllCoupons();
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == coupons;
    }

    @Test
    public void testUpdateCoupon() {
        int couponId = 1;
        Coupon updatedCoupon = new Coupon();
        when(couponService.updateCoupon(updatedCoupon, couponId)).thenReturn(updatedCoupon);

        ResponseEntity<Coupon> response = couponController.updateCoupon(couponId, updatedCoupon);

        verify(couponService, times(1)).updateCoupon(updatedCoupon, couponId);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == updatedCoupon;
    }

    @Test
    public void testDeleteCoupon() {
        int couponId = 1;
        ResponseEntity<Void> response = couponController.deleteCoupon(couponId);

        verify(couponService, times(1)).deleteCoupon(couponId);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }

    @Test
    public void testSearchByTitle() {
        String title = "Sample Coupon";
        List<Coupon> searchResults = new ArrayList<>();
        when(couponService.searchByTitle(title)).thenReturn(searchResults);

        ResponseEntity<List<Coupon>> response = couponController.searchByTitle(title);

        verify(couponService, times(1)).searchByTitle(title);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == searchResults;
    }

    @Test
    public void testHandleResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Coupon not found");
        ResponseEntity<String> response = couponController.handleResourceNotFoundException(ex);

        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
        assert response.getBody().equals("Coupon not found");
    }
}

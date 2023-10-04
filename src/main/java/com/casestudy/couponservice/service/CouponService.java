package com.casestudy.couponservice.service;

import com.casestudy.couponservice.model.Coupon;

import java.util.List;

public interface CouponService {
    
    Coupon createCoupon(Coupon coupon);
    
    Coupon updateCoupon(Coupon coupon, int couponId);
    
    List<Coupon> listAllCoupons();
    
    void deleteCoupon(int couponId);
    
    Coupon getCouponById(int couponId);
    
    int countCoupons();
    
    List<Coupon> searchByTitle(String title);
}

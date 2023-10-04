package com.casestudy.couponservice.service;

import com.casestudy.couponservice.exception.ResourceNotFoundException;
import com.casestudy.couponservice.model.Coupon;
import com.casestudy.couponservice.repo.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepo couponRepo;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepo.save(coupon);
    }

    @Override
    public Coupon updateCoupon(Coupon coupon, int couponId) {
        Optional<Coupon> existingCoupon = couponRepo.findById(couponId);
        if (existingCoupon.isPresent()) {
            coupon.setCouponId(couponId);
            return couponRepo.save(coupon);
        } else {
            throw new ResourceNotFoundException("Coupon not found with Id : " + couponId);
        }
    }

    @Override
    public List<Coupon> listAllCoupons() {
        return couponRepo.findAll();
    }

    @Override
    public void deleteCoupon(int couponId) {
        Optional<Coupon> existingCoupon = couponRepo.findById(couponId);
        if (existingCoupon.isPresent()) {
            couponRepo.delete(existingCoupon.get());
        } else {
            throw new ResourceNotFoundException("Coupon not found with Id : " + couponId);
        }
    }

    @Override
    public Coupon getCouponById(int couponId) {
        Optional<Coupon> coupon = couponRepo.findById(couponId);
        if (coupon.isPresent()) {
            return coupon.get();
        } else {
            throw new ResourceNotFoundException("Coupon not found with Id : " + couponId);
        }
    }

    @Override
    public int countCoupons() {
        return couponRepo.findAll().size();
    }

    @Override
    public List<Coupon> searchByTitle(String title) {
        return couponRepo.findByTitle(title);
    }
}

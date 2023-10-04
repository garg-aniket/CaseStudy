package com.casestudy.couponservice.repo;

import com.casestudy.couponservice.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    // Define a custom query method to search for coupons by title
    @Query("SELECT c FROM Coupon c WHERE c.title = :title")
    List<Coupon> findByTitle(String title);
}

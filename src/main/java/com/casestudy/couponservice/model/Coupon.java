package com.casestudy.couponservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int couponId;

    @NotEmpty(message = "Coupon title is required")
    @Size(min = 4, message = "Coupon title must be at least 4 characters")
    private String title;

    @NotEmpty(message = "Coupon code is required")
    private String code;

    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.01", message = "Discount must be greater than or equal to 0.01")
    private Double discount;

    @NotEmpty(message = "Merchant name is required")
    private String merchant;

    // Constructors, getters, setters, and other methods
}

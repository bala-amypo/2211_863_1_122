package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "discount_codes")
public class DiscountCode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private Boolean active = true;
// Fixes errors in DiscountCodeServiceImpl and SaleTransactionServiceImpl
public String getCode() { return code; }
public void setCode(String code) { this.code = code; }
public BigDecimal getDiscountPercentage() { return discountPercentage; }
public void setDiscountPercentage(BigDecimal discountPercentage) { this.discountPercentage = discountPercentage; }
public Boolean getActive() { return active; }
public void setActive(Boolean active) { this.active = active; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
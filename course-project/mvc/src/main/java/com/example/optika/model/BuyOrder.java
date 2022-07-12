package com.example.optika.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class BuyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long phoneNumber;
    private String message;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = false)
    @JoinColumn(name = "product_id")
    private Product buy;

    public BuyOrder() {
    }

    public BuyOrder(Long phoneNumber, String message, Product product) {
        this.buy = product;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String takeProductName() {
        return buy.getProductName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getBuy() {
        return buy;
    }

    public void setBuy(Product buy) {
        this.buy = buy;
    }
}

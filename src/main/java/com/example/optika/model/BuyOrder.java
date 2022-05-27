package com.example.optika.model;

import javax.persistence.*;


@Entity
@Table(name = "orders")
public class BuyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int phoneNumber;
    private String message;

//    cascade = CascadeType.ALL,
    // targetEntity = BuyOrder.class,
    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = false)
    //cascade = CascadeType.REMOVE, orphanRemoval = true прт удалении с /ордерс удаляет и продукт...
    @JoinColumn(name = "product_id")
    //попробывать удалить связку и добавлять имя через метод takeProductName
//    @Transient
    private Product buy;

    public BuyOrder() {
    }

    public BuyOrder(int phoneNumber, String message, Product product) {
        this.buy = product;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String takeProductName() {
//        return buy != null ? buy.getProductName() : "<someDesc>";
        return buy.getProductName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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

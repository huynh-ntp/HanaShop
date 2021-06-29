package com.example.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tblOrderDetails")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String detailID;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private OrderEntity orderID;

    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductEntity productID;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;
}

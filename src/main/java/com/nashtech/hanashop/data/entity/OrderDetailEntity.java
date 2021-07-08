package com.nashtech.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tblOrderDetails")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailID;


    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductEntity product;


}

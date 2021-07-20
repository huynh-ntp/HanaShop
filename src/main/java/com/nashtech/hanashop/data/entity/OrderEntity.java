package com.nashtech.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tblOrders")
public class OrderEntity {
    @Id
    private String orderID;
    @Column(name = "totalPrice")
    private Float totalPrice;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "orderDate")
    private Date orderDate;
    @Column(name = "typePay")
    private String typePay;
    @ManyToOne()
    @JoinColumn(name = "userName")
    private UserEntity user ;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderDetailEntity> listOrderDetails;

}

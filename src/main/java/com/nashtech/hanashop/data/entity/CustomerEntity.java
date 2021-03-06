package com.nashtech.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tblCustomers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;
    @OneToOne
    @JoinColumn(name = "userName")
    private UserEntity user;

    private String phone;
    @Column(columnDefinition = "TEXT")
    private String address;

}

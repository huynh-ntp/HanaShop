package com.nashtech.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "tblUsers")
@Entity
public class UserEntity {


    @Id
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(columnDefinition = "TEXT")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private RoleEntity role;


    private String email;

    private boolean status;
    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    private List<OrderEntity> listOrders;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<RateEntity> listOfRates;

    @OneToOne(mappedBy = "user")
    private CustomerEntity customerEntity;

}

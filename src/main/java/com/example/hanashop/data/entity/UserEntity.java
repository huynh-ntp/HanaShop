package com.example.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "tblUsers")
@Entity
public class UserEntity {
    @Id
    private String userName;

    private String password;

    private String fullName;


    @ManyToOne
    @JoinColumn(name = "roleID")
    private RoleEntity roleID;

    private String email;

    private boolean status;
    @OneToMany(mappedBy = "userName" ,cascade = CascadeType.ALL)
    private List<OrderEntity> listOrders;


}

package com.example.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tblRoles")
public class RoleEntity {
    @Id
    private String roleID;
    @Column(name = "roleName",nullable = false)
    private String roleName;
    @OneToMany(mappedBy = "roleID",cascade = CascadeType.ALL)
    private List<UserEntity> users ;
}

package com.nashtech.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "tblRates")
public class RateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String rateID;

    @ManyToOne
    @JoinColumn(name = "userName")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductEntity product;

    @Column(name = "numOfStar")
    private Integer numOfStar;

    @Column(name = "description")
    private String description;

    @Column(name = "dateRate")
    private Date dateRate;


}

package com.example.hanashop.data.entity;

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

    @Column(name = "userName")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductEntity productID;

    @Column(name = "numOfStar")
    private Integer numOfStar;

    @Column(name = "description")
    private String description;

    @Column(name = "dateRate")
    private Date dateRate;
}

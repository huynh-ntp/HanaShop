package com.nashtech.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tblProducts")

public class ProductEntity {
    @Id
    private String productID;

    @Column(name = "productName", columnDefinition = "TEXT")
    private String productName;

    @Column(name = "imageSrc", columnDefinition = "TEXT")
    private String imageSrc;

    @Column(name = "desscription",columnDefinition = "TEXT")
    private String desscription;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "updateDate")
    private Date updateDate;


    @ManyToOne
    @JoinColumn(name = "categoryID")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> listOrderDetails;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RateEntity> listOfRates;



}

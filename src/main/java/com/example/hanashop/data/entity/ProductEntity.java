package com.example.hanashop.data.entity;

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

    @Column(name = "productName")
    private String productName;

    @Column(name = "imageSrc")
    private String imageSrc;

    @Column(name = "desscription")
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
    private CategoryEntity categoryID;

    @OneToMany(mappedBy = "productID",cascade = CascadeType.ALL)
    private List<OrderDetailEntity> listOrderDetails;

    @OneToMany(mappedBy = "productID",cascade = CascadeType.ALL)
    private List<RateEntity> listOfRates;
}

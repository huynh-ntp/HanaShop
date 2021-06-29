package com.example.hanashop.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tblCategories")
public class CategoryEntity {
    @Id
    private String categoryID;
    @Column(name = "categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "categoryID",cascade = CascadeType.ALL)
    private List<ProductEntity> listOfProducts;
}

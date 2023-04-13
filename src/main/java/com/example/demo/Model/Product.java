package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    private String prodName;
    private String prodDesc;
    private double prodPrice;
    @Lob
    private byte[] prodImage;

    public Product(Product prod){
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
    }

    public Product(String prodName, String prodDesc, double prodPrice) {
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
    }

    public Product(String prodName, String prodDesc, double prodPrice,byte[] prodImage) {
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
        this.prodImage = prodImage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodName='" + prodName + '\'' +
                ", prodDesc='" + prodDesc + '\'' +
                ", prodPrice=" + prodPrice +
                '}';
    }
}

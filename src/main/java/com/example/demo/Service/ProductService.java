package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public  List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void savetoDB(String name,String desc,double price,MultipartFile file) throws IOException, SQLException {
        Product prod = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName.contains("..")){
            System.out.println("Not valid");
        }
        Blob blob = new SerialBlob(file.getBytes());
        prod.setProdName(name);
        prod.setProdImage(blob);
        prod.setProdDesc(desc);
        prod.setProdPrice(price);
        productRepository.save(prod);
    }
}

package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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

    public void savetoDB(String name,String desc,double price,MultipartFile file){
        Product prod = new Product();
        prod.setProdName(name);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("Not valid");
        }
        try{
            prod.setProdImage(Base64.getEncoder().encodeToString(file.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
        prod.setProdDesc(desc);
        prod.setProdPrice(price);
        productRepository.save(prod);
    }
}

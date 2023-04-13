package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public  List<Product> getProducts() throws SQLException {
        return productRepository.findAll();
    }

    public void savetoDB(String name,String desc,double price,MultipartFile file) throws IOException, SQLException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName.contains("..")){
            System.out.println("Not valid");
        }
        InputStream inputStream = file.getInputStream();
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer))!=1){
            byteOutStream.write(buffer,0,bytesRead);
        }
        byte[] byteArray = byteOutStream.toByteArray();

        Blob img = new SerialBlob(file.getBytes());
        Product prod = new Product(name,desc,price,byteArray);
        productRepository.save(prod);
    }
}

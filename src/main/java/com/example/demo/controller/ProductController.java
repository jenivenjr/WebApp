package com.example.demo.controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;



@Controller
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @CrossOrigin
    @RequestMapping(value = "/getproducts", method = RequestMethod.GET)
    public List<Product> getProducts() throws SQLException {
        return productService.getProducts();
    }

    @CrossOrigin
    @PostMapping("/uploadimage")
    public ResponseEntity<String> uploadImage(@RequestParam("name") String prodname,
                                              @RequestParam("desc")String proddesc,
                                              @RequestParam("price") double price,
                                              @RequestParam(value = "image")MultipartFile image) throws Exception {


        productService.savetoDB(prodname,proddesc,price,image);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}

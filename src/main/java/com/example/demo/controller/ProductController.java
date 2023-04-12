package com.example.demo.controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;
    @Value("${uploadDir}")
    private String uploadFolder;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/getproducts", method = RequestMethod.GET)
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @CrossOrigin
    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("name") String prodname,
                              @RequestParam("desc")String proddesc,
                              @RequestParam("price") double price,
                              @RequestParam(value = "image")MultipartFile image) throws Exception {

        productService.savetoDB(prodname,proddesc,price,image);
        return "done";
    }


}

package com.example.demo.controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final ImageService imageService;

    public ProductController(ProductService productService,ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;

    }
//

    @RequestMapping(value = "/getproducts", method = RequestMethod.GET)
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @CrossOrigin
    @PostMapping("/uploadimage")
    public String uploadImage(@ModelAttribute Product product,
                             @RequestParam(value = "file")MultipartFile file) throws Exception {
        Product prod = new Product(product);
        productService.savetoDB(prod);
        return "done";
    }


}

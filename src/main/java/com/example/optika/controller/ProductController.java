package com.example.optika.controller;

import com.example.optika.model.BuyOrder;
import com.example.optika.model.Product;
import com.example.optika.repository.BuyOrderRepository;
import com.example.optika.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyOrderRepository buyOrderRepository;

    BuyOrderController buyOrderController = new BuyOrderController();

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String main(Model model) {
        Iterable<Product> product = productRepository.findAll();
        model.addAttribute("products", product);
//        return "/products/list_products";
        return "index";
    }

    @PostMapping("/")
    public String addProduct(@RequestParam String productName, @RequestParam int price, @RequestParam String description, @RequestParam("file") MultipartFile file) throws IOException {
        Product product = new Product(productName, price, description);

        saveFile(product, file);
        productRepository.save(product);

//        return "redirect:/products";
        return "redirect:/";
    }

    @GetMapping("/deleteproduct")
    public String deleteHomework(@RequestParam Long productId) {
        productRepository.deleteById(productId);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id).get();

        model.addAttribute("product", product);
        return "/products/edit-product";
    }

    @PostMapping("/update/{id}")
    public String updateHomeWork(@PathVariable("id") long id, @RequestParam("file") MultipartFile file, Product product) throws IOException {
        saveFile(product, file);
        productRepository.save(product);
        return "redirect:/";
    }


    private void saveFile(Product product, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            product.setFilename(resultFilename);
        }
    }
}

package com.example.optika.controller;

import com.example.optika.model.BuyOrder;
import com.example.optika.model.Product;
import com.example.optika.repository.BuyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class BuyOrderController {

    @Autowired
    private BuyOrderRepository buyOrderRepository;

    //сделать отдельную страницу ордеров для админа

    @GetMapping("/orders/{id}")
    public String getAllOrders(Model model) {
        Iterable<BuyOrder> order = buyOrderRepository.findAll();
        model.addAttribute("orders", order);
        return "/order/order-list";
    }

//    @PathVariable("id") long id,
    @PostMapping("/orders/{id}")
    public String addProduct(@RequestParam int phoneNumber, @RequestParam String message, Product product)  {

        BuyOrder buyOrder = new BuyOrder(phoneNumber, message, product);
        buyOrderRepository.save(buyOrder);

        return "redirect:/products";
    }


    // при нажатаии Buy отобразить товар, № тел, сообщение + редірекст на спс страницу
    // создать страницу ордеров для админа
    //перевести БД с инт на лонг

    @GetMapping("/deleteOrder")
    public String deleteBueOrder(@RequestParam Long orderId) {
        buyOrderRepository.deleteById(orderId);
        return "redirect:/products";
    }


}

package com.example.optika.controller;

import com.example.optika.model.BuyOrder;
import com.example.optika.model.Product;
import com.example.optika.repository.BuyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BuyOrderController {

    @Autowired
    private BuyOrderRepository buyOrderRepository;

    @GetMapping("/orders")
    public String allOrders(Model model) {
        Iterable<BuyOrder> order = buyOrderRepository.findAll();
        model.addAttribute("orders", order);
        return "/order/orders";
    }

    @GetMapping("/thx")
    public String thxPage(Model model) {
        return "/order/thx";
    }

    @GetMapping("/orders/{id}")
    public String getAllOrders(Model model) {
        Iterable<BuyOrder> order = buyOrderRepository.findAll();
        model.addAttribute("orders", order);
        return "/order/order-list";
    }

    @PostMapping("/orders/{id}")
    public String addBuyOrder(@RequestParam Long phoneNumber, @RequestParam String message, Product product)  {

        BuyOrder buyOrder = new BuyOrder(phoneNumber, message, product);
        buyOrderRepository.save(buyOrder);

        return "redirect:/thx";
    }

    @GetMapping("/deleteOrder")
    public String deleteBuyOrder(@RequestParam Long orderId) {
        buyOrderRepository.deleteById(orderId);
        return "redirect:/orders";
    }

}

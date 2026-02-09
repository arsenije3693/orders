package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "index";
    }

    @GetMapping("/orders/new")
    public String newOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order-form";
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute Order order) {
        try {
            if (order.getProduct() == null || order.getProduct().trim().isEmpty()) {
                throw new IllegalArgumentException("Product name is required");
            }
            if (order.getQuantity() == null || order.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0");
            }
            if (order.getPrice() == null || order.getPrice() < 0) {
                throw new IllegalArgumentException("Price must be 0 or greater");
            }
            
            System.out.println("Saving order: " + order.getProduct() + ", Qty: " + order.getQuantity() + ", Price: " + order.getPrice());
            Order saved = orderRepository.save(order);
            System.out.println("Order saved successfully with ID: " + saved.getId());
            return "redirect:/";
        } catch (Exception e) {
            System.err.println("Error saving order: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/";
    }
}

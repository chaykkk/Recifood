package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {

    @GetMapping("/purchase")
    public String insertPurchase() {
        return "purchase/insertPurchase";
    }
}

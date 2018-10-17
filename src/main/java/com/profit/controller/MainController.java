package com.profit.controller;

import com.profit.service.calculate.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MainController {

    private final CalculateService calculateService;

    @Autowired
    public MainController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @RequestMapping("/profit")
    public Float getProfit(@RequestParam("buy") @DateTimeFormat(pattern="yyyy-MM-dd") Date buyDate,
                           @RequestParam("sell") @DateTimeFormat(pattern="yyyy-MM-dd") Date sellDate,
                           @RequestParam("amount") Integer amount) {
        return calculateService.calculateProfit(buyDate, sellDate, amount);
    }

}

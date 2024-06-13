package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.modal.AverageResponse;
import com.exam.service.Number;

@RestController
public class ClientController {

    @Autowired
    private Number number;

    @GetMapping("/")
    public String hello() {
        return "hello afford medical task";
    }

    @GetMapping("/numbers/{numberId}")
    public AverageResponse getNumbers(@PathVariable String numberId) {
        System.out.println("Received request for numberId: " + numberId); // Debug line
        return number.fetchAndCalculate(numberId);
    }
}



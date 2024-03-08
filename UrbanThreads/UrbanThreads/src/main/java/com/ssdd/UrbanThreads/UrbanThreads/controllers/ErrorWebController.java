package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorWebController {
    @GetMapping("/error")
    public String err(Model model) {

        return "error";
    }
}

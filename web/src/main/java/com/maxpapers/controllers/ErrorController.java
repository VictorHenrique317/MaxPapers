package com.maxpapers.controllers;

import com.maxpapers.constants.View;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping("error")
    public String error(){
        return View.ERROR;
    }
    @Override
    public String getErrorPath() {
        return null;
    }
}

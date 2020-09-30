package com.maxpapers.controllers;

import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = Mapping.HOME, method = RequestMethod.GET)
public class MainController {

    @GetMapping()
    public String home(){
        return View.HOME;
    }

}

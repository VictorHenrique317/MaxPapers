package com.maxpapers.controllers;

import com.maxpapers.common.Photo;
import com.maxpapers.constants.Attribute;
import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import com.maxpapers.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = Mapping.HOME, method = RequestMethod.GET)
public class MainController {
    private HomeService homeService;

    @Autowired
    public MainController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping()
    public String home(Model model){
        List<Photo> homePagePhotos = homeService.getHomePagePhotos();
        model.addAttribute(Attribute.HOME_PAGE_PHOTOS, homePagePhotos);
        return View.HOME;
    }

}

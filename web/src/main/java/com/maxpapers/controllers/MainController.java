package com.maxpapers.controllers;

import com.maxpapers.common.Photo;
import com.maxpapers.constants.Attribute;
import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import com.maxpapers.services.HomeService;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Controller
@RequestMapping(value = Mapping.HOME, method = RequestMethod.GET)
public class MainController {
    private HomeService homeService;

    @Autowired
    public MainController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping()
    public Callable<String> mainPage(Model model) {
        return ()->{
            List<Photo> homePagePhotos = homeService.getHomePagePhotos().join();
            model.addAttribute(Attribute.HOME_PAGE_PHOTOS, homePagePhotos);
            return View.HOME;
        };
    }

}

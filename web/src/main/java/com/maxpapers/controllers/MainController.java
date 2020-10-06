package com.maxpapers.controllers;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.constants.Attribute;
import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import com.maxpapers.services.HomeService;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Controller
public class MainController {
    private HomeService homeService;

    @Autowired
    public MainController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public Callable<String> mainPage(Model model) {
        return ()->{
            Map<String, List<Photo>> homePagePhotos = homeService.getHomePagePhotos().join();
            model.addAttribute(Attribute.FIRST_COL_HOME_PHOTOS, homePagePhotos.get(Attribute.FIRST_COL_HOME_PHOTOS));
            model.addAttribute(Attribute.SECOND_COL_HOME_PHOTOS, homePagePhotos.get(Attribute.SECOND_COL_HOME_PHOTOS));
            return View.HOME;
        };
    }

    @PostMapping(Mapping.HOME)
    public Callable<String> filterByTheme(@RequestParam("theme") String theme, Model model){
        return ()->{
          Map<String, List<Photo>> photosOfTheme = homeService.queryByTheme(Theme.valueOf(theme)).join();
          model.addAttribute(Attribute.FIRST_COL_HOME_PHOTOS, photosOfTheme.get(Attribute.FIRST_COL_HOME_PHOTOS));
          model.addAttribute(Attribute.SECOND_COL_HOME_PHOTOS, photosOfTheme.get(Attribute.SECOND_COL_HOME_PHOTOS));
          return View.HOME;
        };
    }

}

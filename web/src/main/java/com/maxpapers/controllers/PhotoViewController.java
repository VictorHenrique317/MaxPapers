package com.maxpapers.controllers;

import com.maxpapers.constants.Attribute;
import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import com.maxpapers.services.SearchService;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.Callable;

@Slf4j
@Controller
public class PhotoViewController {
    private SearchService searchService;

    @Autowired
    public PhotoViewController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(Mapping.VIEW)
    public String view(){
        return View.PHOTO_VIEW;
    }

    @PostMapping(Mapping.VIEW)
    public Callable<String> view(@RequestParam("id") int id, Model model){
        return ()->{
            log.info("{} id is {}", Ansi.BLUE, id);
            model.addAttribute(Attribute.PHOTO, searchService.get(id).join());
            return View.PHOTO_VIEW;
        };
    }
}

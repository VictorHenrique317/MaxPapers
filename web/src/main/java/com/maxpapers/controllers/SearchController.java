package com.maxpapers.controllers;

import com.maxpapers.common.Photo;
import com.maxpapers.constants.Attribute;
import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import com.maxpapers.services.SearchService;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Attr;

import java.util.List;
import java.util.concurrent.Callable;

//@RequestMapping(value=Mapping.RESULTS)
@Slf4j
@Controller
//@SessionAttributes(Attribute.SEARCH_RESULTS)
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(Mapping.SEARCH)
    public String mainPage(){
        return View.SEARCH;
    }

    @PostMapping(Mapping.SEARCH)
    public Callable<String> search(@RequestParam("query") String query, Model model){
        return ()->{
            List<Photo> results = searchService.search(query).join();
            model.addAttribute(Attribute.SEARCH_RESULTS, results);
            return View.SEARCH;
        };
    }
}

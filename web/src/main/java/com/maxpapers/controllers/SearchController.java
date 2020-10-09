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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Attr;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
@Controller
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

//    @GetMapping(Mapping.SEARCH)
//    public String mainPage(Model model){
////        if (!model.containsAttribute(Attribute.FIRST_COL_SEARCH_RESULTS))return "redirect:" + Mapping.HOME;
//        return View.SEARCH;
//    }

//    @PostMapping(Mapping.SEARCH)
//    public Callable<String> search(@RequestParam("query") String query, RedirectAttributes redirectAttributes){
//        return ()->{
//            Map<String, List<Photo>> results = searchService.search(query).join();
//            redirectAttributes.addFlashAttribute(Attribute.FIRST_COL_SEARCH_RESULTS,
//                    results.get(Attribute.FIRST_COL_SEARCH_RESULTS));
//            redirectAttributes.addFlashAttribute(Attribute.SECOND_COL_SEARCH_RESULTS,
//                    results.get(Attribute.SECOND_COL_SEARCH_RESULTS));
//            redirectAttributes.addAttribute("query", query);
//            return "redirect:/" + Mapping.SEARCH;
//        };
//    }

    @GetMapping(Mapping.SEARCH)
    public Callable<String> search(@RequestParam("query") String query, Model model){
        return ()->{
            Map<String, List<Photo>> results = searchService.search(query).join();
            model.addAttribute(Attribute.FIRST_COL_SEARCH_RESULTS,
                    results.get(Attribute.FIRST_COL_SEARCH_RESULTS));
            model.addAttribute(Attribute.SECOND_COL_SEARCH_RESULTS,
                    results.get(Attribute.SECOND_COL_SEARCH_RESULTS));
            return View.SEARCH;
        };
    }
}

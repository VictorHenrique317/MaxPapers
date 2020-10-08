package com.maxpapers.controllers;

import com.maxpapers.constants.Attribute;
import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import com.maxpapers.services.SearchService;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.Callable;

@Slf4j
@PropertySource("classpath:application.properties")
@Controller
public class PhotoViewController {
    private SearchService searchService;
    @Value("${related.photos.quantity}") private int relatedPhotosAmount;

    @Autowired
    public PhotoViewController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(Mapping.VIEW)
    public String view(){
        return View.PHOTO_VIEW;
    }

    @PostMapping(Mapping.VIEW)
    public Callable<String> view(@RequestParam("id") int id, RedirectAttributes redirectAttributes){
        return ()->{
            log.info("{} id is {}", Ansi.BLUE, id);
            redirectAttributes.addFlashAttribute(Attribute.PHOTO, searchService.get(id).join());
            redirectAttributes.addFlashAttribute(Attribute.RELATED_PHOTOS,
                    searchService.getRelatedPhotosTo(id, relatedPhotosAmount).join());
            redirectAttributes.addAttribute("id", id);
            return "redirect:/" + Mapping.VIEW;
        };
    }
}

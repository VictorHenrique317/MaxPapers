package com.maxpapers.controllers;

import com.maxpapers.constants.Attribute;
import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import com.maxpapers.services.PhotoViewService;
import com.maxpapers.services.SearchService;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Callable;

@Slf4j
@PropertySource("classpath:application.properties")
@Controller
public class PhotoViewController {
    @Value("${related.photos.quantity}") private int relatedPhotosAmount;
    private PhotoViewService photoViewService;
    private SearchService searchService;


    @Autowired
    public PhotoViewController(PhotoViewService photoViewService, SearchService searchService) {
        this.photoViewService = photoViewService;
        this.searchService = searchService;
    }

    @GetMapping(Mapping.PHOTO)
    public String view(){
        return View.PHOTO_VIEW;
    }

    @RequestMapping(value = Mapping.VIEW, method = {RequestMethod.POST, RequestMethod.GET})
    public Callable<String> view(@RequestParam("id") int id, Model model){
        return ()->{
            log.info("{} id is {}", Ansi.BLUE, id);
            model.addAttribute(Attribute.PHOTO, searchService.get(id).join());
            model.addAttribute(Attribute.RELATED_PHOTOS,
                    photoViewService.getRelatedPhotosTo(id, relatedPhotosAmount).join());
//            redirectAttributes.addAttribute("id", id);
            return View.PHOTO_VIEW;
//            return "redirect:/" + Mapping.PHOTO;
        };
    }

    @GetMapping(Mapping.DOWNLOAD)
    public void downloadPhoto(@RequestParam("id") int id, HttpServletResponse response){
        File file = photoViewService.getPhotoFileWithId(id);
        response.setContentType("image/jpeg");
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        try {
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

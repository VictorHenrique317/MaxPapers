package com.maxpapers.controllers;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.constants.Attribute;
import com.maxpapers.constants.Cookies;
import com.maxpapers.constants.Mapping;
import com.maxpapers.constants.View;
import com.maxpapers.services.HomeService;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Attr;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
@Controller
public class MainController {
    private HomeService homeService;

    @Autowired
    public MainController(HomeService homeService) {
        this.homeService = homeService;
    }


    //    @GetMapping(Mapping.HOME)
//    public Callable<String> mainPage(
//            @CookieValue(value = Cookies.QUERYING_BY_THEME, defaultValue = "false") String queryingByTheme,
//            Model model, HttpServletResponse response) {
//        return () -> {
//            log.debug("{} querying by theme is {}", Ansi.BLUE, queryingByTheme);
//            if (queryingByTheme.equals("true")) { // Clear cookie, keep photos
//                Cookie queryingByThemeRemove = new Cookie(Cookies.QUERYING_BY_THEME, "");
//                queryingByThemeRemove.setMaxAge(0);
//                response.addCookie(queryingByThemeRemove);
//
//            }else if (queryingByTheme.equals("false")){ // Populate attributes with new photos
//                Map<String, List<Photo>> homePagePhotos = homeService.getHomePagePhotos().join();
//                model.addAttribute(Attribute.FIRST_COL_HOME_PHOTOS, homePagePhotos.get(Attribute.FIRST_COL_HOME_PHOTOS));
//                model.addAttribute(Attribute.SECOND_COL_HOME_PHOTOS, homePagePhotos.get(Attribute.SECOND_COL_HOME_PHOTOS));
//            }
//
//            return View.HOME;
//            };
//        }

    //    @PostMapping(Mapping.HOME)
//    public Callable<String> filterByTheme(@RequestParam("theme") String theme,
//                                          HttpServletResponse response, RedirectAttributes redirectAttributes) {
//        return () -> {
//            log.debug("{} filtering by theme", Ansi.BLUE);
//            Map<String, List<Photo>> photosOfTheme = homeService.queryByTheme(Theme.valueOf(theme)).join();
//            redirectAttributes.addFlashAttribute(Attribute.FIRST_COL_HOME_PHOTOS, photosOfTheme.get(Attribute.FIRST_COL_HOME_PHOTOS));
//            redirectAttributes.addFlashAttribute(Attribute.SECOND_COL_HOME_PHOTOS, photosOfTheme.get(Attribute.SECOND_COL_HOME_PHOTOS));
//
//            Cookie queryingByTheme = new Cookie(Cookies.QUERYING_BY_THEME, "true");
//            response.addCookie(queryingByTheme);
//            return "redirect:/" + Mapping.HOME;
//        };
//    }

    @GetMapping(Mapping.HOME)
    public Callable<String> mainPage(String queryingByTheme, Model model, HttpServletResponse response) {
        return () -> {
            Map<String, List<Photo>> homePagePhotos = homeService.getHomePagePhotos().join();
            model.addAttribute(Attribute.FIRST_COL_HOME_PHOTOS, homePagePhotos.get(Attribute.FIRST_COL_HOME_PHOTOS));
            model.addAttribute(Attribute.SECOND_COL_HOME_PHOTOS, homePagePhotos.get(Attribute.SECOND_COL_HOME_PHOTOS));

            return View.HOME;
        };
    }

    @PostMapping(Mapping.HOME)
    public String goToMainPage(){
        return "redirect:/" + Mapping.HOME;
    }

    @GetMapping(Mapping.HOME_THEME)
    public Callable<String> filterByTheme(@RequestParam("theme") String theme,
                                          HttpServletResponse response, Model model) {
        return () -> {
            log.debug("{} filtering by theme", Ansi.BLUE);
            Map<String, List<Photo>> photosOfTheme = homeService.queryByTheme(Theme.valueOf(theme)).join();
            model.addAttribute(Attribute.FIRST_COL_HOME_PHOTOS, photosOfTheme.get(Attribute.FIRST_COL_HOME_PHOTOS));
            model.addAttribute(Attribute.SECOND_COL_HOME_PHOTOS, photosOfTheme.get(Attribute.SECOND_COL_HOME_PHOTOS));

            return View.HOME;
        };
    }

}

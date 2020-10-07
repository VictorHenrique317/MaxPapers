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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
@Controller
@SessionAttributes(value = {Attribute.FIRST_COL_HOME_PHOTOS, Attribute.SECOND_COL_HOME_PHOTOS})
public class MainController {
    private HomeService homeService;

    @Autowired
    public MainController(HomeService homeService) {
        this.homeService = homeService;
    }


    @GetMapping(Mapping.HOME)
    public Callable<String> mainPage(@CookieValue(value = Cookies.QUERYING_BY_THEME, defaultValue = "false")
                                                 String queryingByTheme, Model model) {
        return () -> { // TODO delete cookie when redirecting is over
            log.debug("{} querying by theme is {}", Ansi.BLUE, queryingByTheme);
            if (queryingByTheme.equals("false")) { // reload
                Map<String, List<Photo>> homePagePhotos = homeService.getHomePagePhotos().join();
                model.addAttribute(Attribute.FIRST_COL_HOME_PHOTOS, homePagePhotos.get(Attribute.FIRST_COL_HOME_PHOTOS));
                model.addAttribute(Attribute.SECOND_COL_HOME_PHOTOS, homePagePhotos.get(Attribute.SECOND_COL_HOME_PHOTOS));
            }
                return View.HOME;
            };
        }


    @PostMapping(Mapping.HOME)
    public Callable<String> filterByTheme(@RequestParam("theme") String theme, Model model,
                                          HttpServletResponse response) {
        return () -> {
            log.debug("{} filtering by theme", Ansi.BLUE);
            Map<String, List<Photo>> photosOfTheme = homeService.queryByTheme(Theme.valueOf(theme)).join();
            model.addAttribute(Attribute.FIRST_COL_HOME_PHOTOS, photosOfTheme.get(Attribute.FIRST_COL_HOME_PHOTOS));
            model.addAttribute(Attribute.SECOND_COL_HOME_PHOTOS, photosOfTheme.get(Attribute.SECOND_COL_HOME_PHOTOS));

            Cookie queryingByTheme = new Cookie(Cookies.QUERYING_BY_THEME, "true");
            response.addCookie(queryingByTheme);
            return "redirect:/" + Mapping.HOME;
        };
    }

}

package org.foodeezz.controller;

import org.foodeezz.service.MenuItemService;
import org.foodeezz.service.RestaurantCategoryService;
import org.foodeezz.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/image")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    HttpSession httpSession;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantCategoryService restaurantCategoryService;

    @Autowired
    private MenuItemService menuItemService;

    @RequestMapping("/restaurant/{id}")
    public
    @ResponseBody
    byte[] getRestaurantPhoto(@PathVariable(value = "id") int id) {
        log.debug("At home page");

        return restaurantService.getRestaurant(id).getPhoto();
    }

    @RequestMapping("/menuItem/{id}")
    public
    @ResponseBody
    byte[] getMenuItemPhoto(@PathVariable(value = "id") int id) {
        log.debug("At home page");

        return menuItemService.getMenuItem(id).getPhoto();
    }

    @RequestMapping("/category/{id}")
    public
    @ResponseBody
    byte[] getCategoryPhoto(@PathVariable(value = "id") int id) {
        log.debug("At home page");

        return restaurantCategoryService.getCategory(id).getPhoto();
    }
}

package org.foodeezz.controller.api;

import org.foodeezz.persistance.entity.*;
import org.foodeezz.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    @Autowired
    HttpSession httpSession;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    BranchService branchService;

    @Autowired
    MenuItemService menuItemService;

    @Autowired
    RestaurantCategoryService restaurantCategoryService;

    @Autowired
    RestaurantReviewService restaurantReviewService;

    @RequestMapping(value = "/{id}")
   public Restaurant getRestaurant(@PathVariable int id){
       return restaurantService.getRestaurant(id);
   }


}

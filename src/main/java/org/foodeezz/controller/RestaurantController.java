package org.foodeezz.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.foodeezz.persistance.entity.*;
import org.foodeezz.service.*;
import org.foodeezz.util.ImageUploadHelper;
import org.foodeezz.web.formbeans.RestaurantSearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    private int numberOfResultsPerPage = 3;

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


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String showList(@RequestParam("page") Integer pageNo, Model model) {
        log.debug("At Restaurants page");

        model.addAttribute("numberOfPages", restaurantService.getNumberOfAllResultPages(numberOfResultsPerPage));
        model.addAttribute("restaurants", restaurantService.getPaginatedListOfRestaurants(pageNo, numberOfResultsPerPage));

        return "restaurantsList";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String search(@ModelAttribute("restaurantSearchCriteria") RestaurantSearchCriteria searchCriteria,
                         @RequestParam("page") Integer pageNo,
                         Model model) {
        log.debug("At Restaurants Search page");

        model.addAttribute("numberOfPages", restaurantService.getNumberOfSearchResultPages(searchCriteria, numberOfResultsPerPage));
        model.addAttribute("restaurants", restaurantService.getRestaurantSearchResult(searchCriteria, pageNo, numberOfResultsPerPage));

        return "restaurantsList";
    }


    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addRestaurant(Model model) throws IOException {
        log.debug("At add Restaurant page");

        model.addAttribute("newRestaurant", new Restaurant());

        return "addRestaurant";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addRestaurant(@Valid @ModelAttribute("newRestaurant") Restaurant newRestaurant, BindingResult result, @RequestParam(value = "picture", required = false) MultipartFile image, RedirectAttributes redirectAttributes) throws IOException {
        log.debug("At Add Restaurant page");

        if (result.hasErrors()) {

            return "addRestaurant";
        } else {

            if (!ImageUploadHelper.isValidImage(image)) {
                redirectAttributes.addAttribute("error", 1);

            } else {

                if (!image.isEmpty())
                    newRestaurant.setPhoto(image.getBytes());

                restaurantService.addRestaurant(newRestaurant);

                redirectAttributes.addAttribute("success", 1);
            }
        }

        return "redirect:/restaurants/add";
    }

    @RequestMapping(value = {"/update/{restaurantId}"}, method = RequestMethod.GET)
    public String updateRestaurant(@PathVariable(value = "restaurantId") int restaurantId, Model model) throws IOException {
        log.debug("At Update Restaurant page");

        model.addAttribute("categories", restaurantCategoryService.getAllCategories());
        model.addAttribute("restaurant", restaurantService.getRestaurant(restaurantId));

        return "updateRestaurant";
    }

    @RequestMapping(value = {"/update/{restaurantId}"}, method = RequestMethod.POST)
    public String updateRestaurant(@Valid @ModelAttribute("restaurant") Restaurant updatedRestaurant, BindingResult result, @RequestParam(value = "picture", required = false) MultipartFile image, RedirectAttributes redirectAttributes) throws IOException {
        log.debug("Updating Restaurant Info");

        if (result.hasErrors()) {

            return "updateRestaurant";
        } else {

            if (!ImageUploadHelper.isValidImage(image)) {
                redirectAttributes.addAttribute("error", 1);

            } else {

                if (!image.isEmpty())
                    updatedRestaurant.setPhoto(image.getBytes());

                restaurantService.updateRestaurant(updatedRestaurant);

                redirectAttributes.addAttribute("success", 1);
            }
        }

        return "redirect:/restaurants/update/" + updatedRestaurant.getId();
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String showProfile(@PathVariable(value = "id") int id, Model model) throws IOException {
        log.debug("At Restaurant Profile");

        model.addAttribute("restaurant", restaurantService.getRestaurantWithAssociations(id));

        if (!model.containsAttribute("newRestaurantReview")) {
            model.addAttribute("newRestaurantReview", new RestaurantReview());
        }

        if (!model.containsAttribute("newRestaurantBranch")) {
            model.addAttribute("newRestaurantBranch", new Branch());
        }

        model.addAttribute("locations", getLatlongJson(id));

        return "restaurantProfile";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeRestaurant(@RequestParam(value = "restaurantId") int restaurantId, Model model) {
        log.debug("Removing Restaurant");

        restaurantService.removeRestaurant(restaurantId);

        return "redirect:/restaurants/?page=1";
    }

    @RequestMapping(value = {"/addBranch"}, method = RequestMethod.POST)
    public String addBranch(@Valid @ModelAttribute("newRestaurantBranch") Branch newBranch, BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        log.debug("Adding New Branch");

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newRestaurantBranch", result);
            redirectAttributes.addFlashAttribute("newRestaurantBranch", newBranch);
        } else {
            branchService.addBranch(newBranch);

            redirectAttributes.addAttribute("success", 1);
        }

        return "redirect:/restaurants/"
                + newBranch.getRestaurant().getId() + "#branches";
    }

    @RequestMapping(value = "/removeBranch", method = RequestMethod.POST)
    public String removeBranch(@RequestParam(value = "branchId") int branchId,
                               @RequestParam(value = "restaurantId") int restaurantId,
                               Model model) {
        log.debug("Removing Restaurant");

        branchService.removeBranch(branchId);

        return "redirect:/restaurants/"
                + restaurantId + "#branches";
    }


    private String getLatlongJson(int restaurantId) throws IOException {
        List<LatLong> restaurantLatLongs = restaurantService.getRestaurantLatLongs(restaurantId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(restaurantLatLongs);

        log.debug("JSON VALUE " + jsonString);
        return jsonString;
    }


    @ModelAttribute("categories")
    public List<RestaurantCategory> getCategories() {
        return restaurantCategoryService.getAllCategories();
    }

    @ModelAttribute("areas")
    public List<String> getAreas() {
        return restaurantService.getAllAreaNames();
    }

    @ModelAttribute("restaurantSearchCriteria")
    public RestaurantSearchCriteria getRestaurantSearchCriteria() {
        return new RestaurantSearchCriteria();
    }


}

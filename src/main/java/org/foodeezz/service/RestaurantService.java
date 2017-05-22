package org.foodeezz.service;

import org.foodeezz.persistance.dao.RestaurantDao;
import org.foodeezz.persistance.entity.Branch;
import org.foodeezz.persistance.entity.LatLong;
import org.foodeezz.persistance.entity.Restaurant;
import org.foodeezz.web.formbeans.RestaurantSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by GALIB on 4/24/2015.
 */
@Service
@Transactional
public class RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    public RestaurantService(){

    }

    public void addRestaurant(Restaurant restaurant){
        restaurantDao.addRestaurant(restaurant);
    }

    public void updateRestaurant(Restaurant updatedRestaurantInfo){
        restaurantDao.updateRestaurant(updatedRestaurantInfo);
    }

    public List<Restaurant> getRestaurantSearchResult(RestaurantSearchCriteria searchCritiria, int pageNo,int numberOfResultsPerPage){
        return restaurantDao.getRestaurantSearchResult(searchCritiria, pageNo,numberOfResultsPerPage);
    }

    public List<Restaurant> getPaginatedListOfRestaurants(int pageNo, int numberOfResultsPerPage){
        return restaurantDao.getRestaurantsList(pageNo, numberOfResultsPerPage);
    }


    public List<LatLong> getRestaurantLatLongs(int restaurantId) {
        return restaurantDao.getRestaurantLatLongs(restaurantId);
    }

    public Restaurant getRestaurant(int restaurantId){
        return restaurantDao.getRestaurant(restaurantId);
    }

    public Restaurant getRestaurantWithAssociations(int restaurantId){
        return restaurantDao.getRestaurantWithAssociations(restaurantId);
    }

    public void removeRestaurant(int restaurantId){
        restaurantDao.removeRestaurant(restaurantId);
    }

    public List<String> getAllAreaNames() {
        return restaurantDao.getAreaNames();
    }

    public int getNumberOfAllResultPages(int numberOfResultsPerPage) {
        return restaurantDao.getNumberOfAllResultPages(numberOfResultsPerPage);
    }

    public int getNumberOfSearchResultPages(RestaurantSearchCriteria searchCritiria, int numberOfResultsPerPage) {
        return restaurantDao.getNumberOfSearchResultPages(searchCritiria, numberOfResultsPerPage);
    }

}

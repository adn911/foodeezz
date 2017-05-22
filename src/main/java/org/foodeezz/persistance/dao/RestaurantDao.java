package org.foodeezz.persistance.dao;

import org.foodeezz.persistance.entity.*;
import org.foodeezz.web.formbeans.RestaurantSearchCriteria;

import java.util.List;

/**
 * Created by bakhtiar.galib on 2/8/15.
 */
public interface RestaurantDao {

    void addRestaurant(Restaurant restaurant);

    void updateRestaurant(Restaurant updatedRestaurantInfo);

    Restaurant getRestaurant(int restaurantId);

    Restaurant getRestaurantWithAssociations(int restaurantId);

    void removeRestaurant(int restaurantId);

    List<Restaurant> getRestaurantsList(int pageNo, int numberOfResultsPerPage);

    List<Restaurant> getRestaurantSearchResult(RestaurantSearchCriteria searchCritiria, int pageNo, int numberOfResultsPerPage);

    List<String> getAreaNames();

    List<LatLong> getRestaurantLatLongs(int restaurantId);

    int getNumberOfAllResultPages(int numberOfResultsPerPage);

    int getNumberOfSearchResultPages(RestaurantSearchCriteria searchCritiria, int numberOfResultsPerPage);
}

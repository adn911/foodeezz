package org.foodeezz.persistance.entity;

import javax.persistence.*;

/**
 * Created by bakhtiar.galib on 4/20/15.
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class RestaurantReview extends Review{

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private RestaurantRating rating;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantRating getRating() {
        return rating;
    }

    public void setRating(RestaurantRating rating) {
        this.rating = rating;
    }
}

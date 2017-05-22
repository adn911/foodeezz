package org.foodeezz.persistance.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by GALIB on 4/18/2015.
 */
@Entity
public class MenuItem extends Persistent{

    @NotEmpty
    @Column(nullable = false, length = 99)
    private String name;

    @NotEmpty
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Range(min = 0)
    private double price;

    @Lob
    private byte[] photo;

    @JsonManagedReference
    private MenuItemRating rating;

    @JsonManagedReference
    @OneToMany(mappedBy = "menuItem",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<MenuItemReview> reviews;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @PrePersist
    void onPrePersist(){
        rating = new MenuItemRating();
        rating.setOverall(0);
    }

    public void updateRating(MenuItemRating newMenuItemRating){

        if( reviews.size() <= 1){
            rating.setOverall(newMenuItemRating.getOverall());

        }else{
            rating.setOverall((rating.getOverall() + newMenuItemRating.getOverall()) / 2);
        }
    }

    public String getName() {
        return name;
    }

    public MenuItemRating getRating() {
        return rating;
    }

    public void setRating(MenuItemRating rating) {
        this.rating = rating;
    }

    public List<MenuItemReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MenuItemReview> reviews) {
        this.reviews = reviews;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void update(MenuItem updatedMenuItemInfo) {
        this.setName(updatedMenuItemInfo.getName());
   //     this.setCategory(updatedRestaurantInfo.getCategory());
        this.setDescription(updatedMenuItemInfo.getDescription());

        if( updatedMenuItemInfo.getPhoto() != null ){
            this.setPhoto( updatedMenuItemInfo.getPhoto() );
        }

    }
}

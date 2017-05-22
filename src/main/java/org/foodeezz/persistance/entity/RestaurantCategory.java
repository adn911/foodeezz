package org.foodeezz.persistance.entity;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by GALIB on 4/18/2015.
 */
@Entity
public class RestaurantCategory extends Persistent{

    @Column(nullable = false,length = 99)
    private String name;

    @Lob
    private byte[] photo;

    @JsonManagedReference
    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Restaurant> restaurants;

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}

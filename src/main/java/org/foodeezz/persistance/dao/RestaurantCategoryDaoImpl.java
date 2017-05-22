package org.foodeezz.persistance.dao;

import org.foodeezz.persistance.entity.RestaurantCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by GALIB on 4/25/2015.
 */
@Repository
@Transactional
public class RestaurantCategoryDaoImpl implements RestaurantCategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addCategory(RestaurantCategory restaurantCategory) {
        entityManager.persist(restaurantCategory);
    }

    @Override
    public void removeCategory(RestaurantCategory restaurantCategory) {
        entityManager.remove(restaurantCategory);
    }

    @Override
    public List<RestaurantCategory> getAllCategories() {
        return entityManager.createQuery("SELECT category FROM RestaurantCategory AS category", RestaurantCategory.class).getResultList();
    }

    @Override
    public RestaurantCategory getCategory(int categoryId) {
        return entityManager.find(RestaurantCategory.class, categoryId);
    }
}

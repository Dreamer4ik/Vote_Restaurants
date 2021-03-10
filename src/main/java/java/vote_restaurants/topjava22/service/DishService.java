package java.vote_restaurants.topjava22.service;


import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.vote_restaurants.topjava22.model.Dish;
import java.vote_restaurants.topjava22.repository.DishRepository;

import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

//create Validation util
    public Dish create(Dish dish, int userId, int restaurantId) {
        Assert.notNull(dish, "dinner must not be null");
        return checkNotFoundWithId(dishRepository.save(dish, userId, restaurantId), dish.getId());
    }
}

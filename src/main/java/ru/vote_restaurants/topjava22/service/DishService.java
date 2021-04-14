package ru.vote_restaurants.topjava22.service;


import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vote_restaurants.topjava22.model.Dish;
import ru.vote_restaurants.topjava22.repository.DishRepository;
import ru.vote_restaurants.topjava22.util.ValidationUtil;

import java.util.List;

import static ru.vote_restaurants.topjava22.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;


    public Dish create(Dish dish, int userId, int restaurantId) {
        Assert.notNull(dish, "dinner must not be null");
        return checkNotFoundWithId(dishRepository.save(dish, userId, restaurantId), dish.getId());
    }

    public void update(Dish dish, int userId, int restaurantId, int id) {
        Assert.notNull(dish, "user must be not null");
        ValidationUtil.assureIdConsistent(dish, id);
        ValidationUtil.checkNotFoundWithId(dishRepository.save(dish, userId, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        ValidationUtil.checkNotFoundWithId(dishRepository.delete(id, restaurantId), id);

    }

    public Dish getDish(int id, int userId, int restaurantId) {


        return ValidationUtil.checkNotFoundWithId(dishRepository.getDish(id, userId, restaurantId), id);
    }

    public List<Dish> getAllForRestaurant(int restaurantId) {

        return dishRepository.getAllForRestaurant(restaurantId);

    }
}

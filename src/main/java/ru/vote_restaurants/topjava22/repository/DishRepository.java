package ru.vote_restaurants.topjava22.repository;

import java.util.List;
import ru.vote_restaurants.topjava22.model.Dish;

public interface DishRepository {

    Dish save(Dish dish , int userId, int restaurantId);

    boolean delete (int id , int restaurantId);

    Dish getDish (int id, int userId, int restaurantId);

    List<Dish> getAllForRestaurant(int restaurantId);
}

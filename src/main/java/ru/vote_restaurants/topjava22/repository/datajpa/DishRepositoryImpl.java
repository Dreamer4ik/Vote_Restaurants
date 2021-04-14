package ru.vote_restaurants.topjava22.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vote_restaurants.topjava22.model.Dish;
import ru.vote_restaurants.topjava22.repository.DishRepository;
import ru.vote_restaurants.topjava22.repository.proxyRepository.ProxyDishRepository;
import ru.vote_restaurants.topjava22.repository.proxyRepository.ProxyRestaurantRepository;
import ru.vote_restaurants.topjava22.repository.proxyRepository.ProxyUserRepository;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private ProxyDishRepository proxyDishRepository;
    @Autowired
    private ProxyRestaurantRepository proxyRestaurantRepository;
    @Autowired
    private ProxyUserRepository proxyUserRepository;


    @Override
    @Transactional
    public Dish save(Dish dish, int userId, int restaurantId) {
        if (!dish.isNew() && getDish(dish.getId(), userId, restaurantId) == null) {
            return null;
        }

        dish.setRestaurant(proxyRestaurantRepository.getOne(restaurantId));
        dish.setUserId(proxyUserRepository.getOne(userId).id());

        return proxyDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return proxyDishRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Dish getDish(int id, int userId, int restaurantId) {
        return proxyDishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId().equals(restaurantId))
                .filter(dish -> dish.getUserId().equals(userId))
                .orElse(null);
    }

    @Override
    public List<Dish> getAllForRestaurant(int restaurantId) {
        return proxyDishRepository.getAll(restaurantId);
    }
}

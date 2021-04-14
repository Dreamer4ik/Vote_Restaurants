package ru.vote_restaurants.topjava22.repository;

import ru.vote_restaurants.topjava22.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getByName(String name);

}

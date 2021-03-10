package java.vote_restaurants.topjava22.repository;

import java.util.List;
import java.vote_restaurants.topjava22.model.Restaurant;

public interface RestaurantRepository {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getByName(String name);

}

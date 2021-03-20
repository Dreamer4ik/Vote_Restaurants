package java.vote_restaurants.topjava22.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.vote_restaurants.topjava22.model.Restaurant;
import java.vote_restaurants.topjava22.repository.RestaurantRepository;

import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNotFound;
import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getRestaurant(int id) {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    public List<Restaurant> getAll (){
        return restaurantRepository.getAll();
    }

    public Restaurant getByName (String name){
        Assert.notNull(name, "email must not be null");
        return checkNotFound(restaurantRepository.getByName(name), "name" + name);
    }
}

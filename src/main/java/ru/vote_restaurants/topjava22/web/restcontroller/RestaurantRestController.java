package ru.vote_restaurants.topjava22.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import ru.vote_restaurants.topjava22.model.Restaurant;
import ru.vote_restaurants.topjava22.service.RestaurantService;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL_RESTAURANTS, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantRestController.class);

    public static final String REST_URL_RESTAURANTS = "/restaurants";

    @Autowired
    private final RestaurantService restaurantService;

    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable int restaurantId) {
        LOG.info("get restaurant with id {}", restaurantId);
        return restaurantService.getRestaurant(restaurantId);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        LOG.info("getAllAdmins");
        return restaurantService.getAll();
    }
}

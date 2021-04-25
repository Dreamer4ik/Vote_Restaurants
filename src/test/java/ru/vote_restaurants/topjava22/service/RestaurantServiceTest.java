package ru.vote_restaurants.topjava22.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vote_restaurants.topjava22.model.Restaurant;
import ru.vote_restaurants.topjava22.util.exception.NotFoundException;

import static org.junit.Assert.*;
import static ru.vote_restaurants.topjava22.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class RestaurantServiceTest {
    @Autowired
    protected RestaurantService restaurantService;

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(RESTAURANT_ID_1);
        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT_1);

    }

    @Test
    public  void getRestaurantNotFound(){
        assertThrows(NotFoundException.class, () -> restaurantService.getRestaurant(NOT_FOUND));
    }

    @Test
    public void getAll() {
        RESTAURANT_MATCHER.assertMatch(restaurantService.getAll(), RESTAURANTS);
    }

    @Test
    public void getByName() {
        RESTAURANT_MATCHER.assertMatch(restaurantService.getByName(RESTAURANT_NAME_1), RESTAURANT_1);

    }

    @Test
    public void getByNameNotFound(){
        assertThrows(NotFoundException.class, () -> restaurantService.getByName(RESTAURANT_NAME_NOT_FOUND));
    }
}
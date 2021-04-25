package ru.vote_restaurants.topjava22.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vote_restaurants.topjava22.model.Dish;
import ru.vote_restaurants.topjava22.util.exception.NotFoundException;

import static org.junit.Assert.*;
import static ru.vote_restaurants.topjava22.DishesTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceTest {
    @Autowired
    private DishService dishService;

    @Test
    public void create() {
        Dish created = dishService.create(getNewDish(), USER_ID, RESTAURANT_ID_1);
        int newId = created.getId();
        Dish newDish = getNewDish();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.getDish(newId, USER_ID, RESTAURANT_ID_1), newDish);
    }

    @Test
    public void update() {
        dishService.update(getUpdated(), USER_ID, RESTAURANT_ID_1, DISHES_ID);
        DISH_MATCHER.assertMatch(dishService.getDish(DISHES_ID, USER_ID, RESTAURANT_ID_1), getUpdated());
    }

    @Test
    public void delete() {
        dishService.delete(DISHES_ID, RESTAURANT_ID_1);
        assertThrows(NotFoundException.class, () -> dishService.getDish(DISHES_ID, USER_ID, RESTAURANT_ID_1));
    }

    @Test
    public void deleteAdminNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.getDish(DISHES_ID, USER_ID, NOT_FOUND));
    }

    @Test
    public void deleteDishNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.getDish(NOT_FOUND, USER_ID, RESTAURANT_ID_1));
    }

    @Test
    public void getDish() {
        Dish actual = dishService.getDish(DISHES_ID, USER_ID, RESTAURANT_ID_1);
        DISH_MATCHER.assertMatch(actual, DISH1);

    }

    @Test
    public void getAllForRestaurant() {
        DISH_MATCHER.assertMatch(dishService.getAllForRestaurant(RESTAURANT_ID_1), DISHES_FOR_RESTAURANT_1);
    }

    @Test
    public void getAllAdminNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.getDish(NOT_FOUND, USER_ID, RESTAURANT_ID_1));
    }
}
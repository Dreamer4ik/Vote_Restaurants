package ru.vote_restaurants.topjava22;

import ru.vote_restaurants.topjava22.model.Dish;

import java.math.BigDecimal;
import java.util.List;

public class DishesTestData {
    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "restaurant");

    public static final int START_SEQ = 100000;
    public static final int NOT_FOUND = 10;
    public static final int DISHES_ID = START_SEQ + 9;
    public static final int RESTAURANT_ID_1 = START_SEQ + 7;
    public static final int USER_ID = START_SEQ;

    public static final Dish DISH1 = new Dish(DISHES_ID, "пирог", new BigDecimal("150.56"), USER_ID);
    public static final Dish DISH2 = new Dish(DISHES_ID + 1, "десерт", new BigDecimal("200.13"), USER_ID);
    public static final Dish DISH3 = new Dish(DISHES_ID + 2, "чай", new BigDecimal("100.05"), USER_ID);

    public static final List<Dish> DISHES_FOR_RESTAURANT_1 = List.of(DISH1, DISH2, DISH3);

    public static Dish getNewDish() {
        return new Dish(null, "NewDish", new BigDecimal("220.45"), USER_ID);
    }

    public static Dish getUpdated() {
        return new Dish(DISHES_ID, "UpdatedDish", new BigDecimal("250.45"), USER_ID);
    }
}
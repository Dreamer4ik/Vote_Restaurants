package ru.vote_restaurants.topjava22;

import ru.vote_restaurants.topjava22.model.Restaurant;

import java.util.List;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Restaurant.class, "voteList", "lunchMenu");


    public static final int START_SEQ = 100000;
    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT_ID_1 = START_SEQ + 7;
    public static final int RESTAURANT_ID_2 = START_SEQ + 8;
    public static final String RESTAURANT_NAME_1 = "Груша";
    public static final String RESTAURANT_NAME_NOT_FOUND = "notFound";

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID_1, "Груша");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID_2, "Кампания");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_1, RESTAURANT_2);


}
